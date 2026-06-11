#import "@preview/isc-hei-report:0.8.1" : *

= Musique et gestion du son

Un jeu de course sans son, ça ne donne pas grand chose. Nous avons donc ajouté de la musique et des bruitages, et toute cette partie est centralisée dans un seul objet, AudioManager. C'est lui qui possède les lecteurs de musique et les effets sonores, et le reste du code se contente de lui demander de jouer quelque chose, sans savoir comment ça marche derrière.

== La musique

Chaque morceau du jeu est représenté par un objet du trait scellé MusicTrack : il y en a un pour le menu, le quiz, le garage, les cinématiques, et un par biome pour la course (forêt, désert et neige). Quand un écran démarre, il demande simplement à AudioManager de jouer son morceau. Pendant la course, c'est le biome du jour qui fournit sa propre musique, donc l'ambiance sonore change en même temps que le décor.

La méthode playTrack s'occupe de la transition : elle arrête le morceau en cours et lance le nouveau en boucle. Si on lui demande le morceau qui est déjà en train de jouer, elle ne fait rien du tout, ce qui évite que la musique soit relancer à zéro quand on revient sur le même écran. Les lecteurs sont gardés dans une map et ne sont créés qu'à la première utilisation d'un morceau, comme ça on ne charge pas tous les fichiers audio au lancement du jeu. Il y a aussi un petit cas particulier : la cinématique finale ne change pas de musique et garde celle du dernier biome, ce qu'on trouvait plus sympa pour la fin du jeu.

== Les bruitages

Le bruitage le plus important est celui du moteur. C'est un son joué en boucle dont la hauteur (le pitch) est recalculée a chaque frame en fonction de la vitesse de la voiture : plus on va vite, plus le moteur monte dans les tours. Le ratio entre la vitesse actuelle et la vitesse maximale est converti en un pitch compris entre une valeur basse et une valeur haute, ce qui donne l'impression d'accélérer alors qu'on joue toujours le même fichier son.

#figure(code()[
```scala
var ratio = Math.abs(speed) / maxSpeed
if (ratio > 1f) ratio = 1f

var pitch = AUDIO.SFX.ENGINE_LOW_PITCH + ratio * (AUDIO.SFX.ENGINE_TOP_PITCH - AUDIO.SFX.ENGINE_LOW_PITCH)
if (pitch < 0.5f) pitch = 0.5f
if (pitch > 2f) pitch = 2f

engine.modifyPitch(pitch, engineId)
```
], caption: [Calcul du pitch du moteur en fonction de la vitesse])

Notre petit plaisir du projet, c'est le backfire. Quand le joueur relâche les gaz à haute vitesse, le pot d'échappement lâche une série de petit pops, comme sur une vraie voiture de course. Le monde du jeu surveille la vitesse : si elle a dépassé un certain seuil puis commence à redescendre, il déclenche le backfire. AudioManager joue alors entre 4 et 5 pops, avec un délai et un pitch tirés aléatoirement pour chaque pop, comme ça la séquence n'est jamais exactement la même et ça sonne beaucoup moins artificiel.

Les derniers effets sont liés aux problèmes de la voiture : un son d'explosion quand un pneu lâche et un bruit de crash quand on percute un obstacle. Comme pour la musique, ces sons sont chargé seulement à la première utilisation. Et quand on ferme le jeu, la fenêtre principale demande à AudioManager de libérer toutes les ressources audio proprement.
