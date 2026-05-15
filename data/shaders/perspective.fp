// in from the java side
uniform sampler2D backbuffer;
uniform vec2 resolution;
uniform float time;
uniform int enabled;
uniform vec3  cameraPosition;
uniform vec3  cameraAxis;
uniform float cameraAngle;
uniform float screenPlanDistance;

//"in" varyings from our vertex shader
varying vec4 vColor;
varying vec2 vTexCoord;


mat3 rotationMatrix(vec3 axis, float angle)
{
    axis = normalize(axis);
    float s = sin(angle);
    float c = cos(angle);
    float oc = 1.0 - c;
    
    return mat3(oc * axis.x * axis.x + c,           oc * axis.x * axis.y - axis.z * s,  oc * axis.z * axis.x + axis.y * s,
                oc * axis.x * axis.y + axis.z * s,  oc * axis.y * axis.y + c,           oc * axis.y * axis.z - axis.x * s,
                oc * axis.z * axis.x - axis.y * s,  oc * axis.y * axis.z + axis.x * s,  oc * axis.z * axis.z + c);
}

void main() {
	if(enabled == 1){
		//Calculate the vector coming out from the camera to the pixel of the screen in the 3D space.
		vec3 pixelRay;
		pixelRay.xz = (gl_FragCoord.xy / resolution.xy)*vec2(1.0, -1.0) + vec2(-0.5, 0.5);
		pixelRay.y  = screenPlanDistance;
		pixelRay   *= rotationMatrix(cameraAxis,cameraAngle);

        if (pixelRay.z > 0.0) {
            vec2 hitPosition = cameraPosition.xy + pixelRay.xy * (cameraPosition.z / pixelRay.z) + vec2(0.5, 0);

            vec4 me = texture2D(backbuffer, hitPosition);
            gl_FragColor = me;

        }
	} else {
		gl_FragColor = texture2D(backbuffer, gl_FragCoord.xy / resolution.xy);
	}
}

