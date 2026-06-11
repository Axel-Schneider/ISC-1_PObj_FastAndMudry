#!/usr/bin/env fish

set RED '\033[0;31m'
set GREEN '\033[0;32m'
set NC '\033[0m' # No Color
set VU "$GREEN✔$NC"

# v2: ships both "Source Sans 3" and "Source Sans Pro" (v2 name used by the
# Typst web editor) so templates compile without font warnings on both CLI and web.
set FONTS_URL "https://files.isc-vs.ch/typst/modern-isc-fonts-v2.tar.gz"
set FONTS_ARCHIVE "modern-isc-fonts-v2.tar.gz"
set FONTS_DIR "modern-isc-fonts-v2"

echo -e "Downloading and installing fonts locally..."

set fonts_dir "$HOME/.local/share/fonts"

if not test -d "$fonts_dir"
    mkdir -p "$fonts_dir"
    echo -e "Created fonts directory $fonts_dir $VU"
else
    echo -e "Found fonts directory $fonts_dir $VU"
end

wget -q --show-progress "$FONTS_URL"

if test $status -ne 0
    echo -e "$RED"Error: Failed to download $FONTS_ARCHIVE."$NC"
    exit 1
end

echo -e "Font bundle downloaded $VU"
tar -zxf "$FONTS_ARCHIVE"

cp "$FONTS_DIR"/*.ttf "$fonts_dir/"
cp "$FONTS_DIR"/*.otf "$fonts_dir/"
echo -e "Fonts installed $VU"

echo -e "Rebuilding font cache..."
fc-cache -f
echo -e "Font cache rebuilt $VU"

rm -f "$FONTS_ARCHIVE"
rm -rf "$FONTS_DIR/"

# Verify all required fonts are visible to Typst
set missing_fonts
for font in "Source Sans Pro" "Source Sans 3" "Inria Sans" "Fira Code"
    if not typst fonts | grep -q "^$font\$"
        set -a missing_fonts "$font"
    end
end

if test (count $missing_fonts) -eq 0
    echo -e "$VU All required fonts found in Typst. Install successful!"
else
    echo -e "$RED"The following fonts were not found by Typst: $missing_fonts"$NC"
    exit 1
end
