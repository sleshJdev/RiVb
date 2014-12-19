package by.slesh.ri.cp.victoriashpak.util.morph;

import java.awt.image.BufferedImage;

import by.slesh.ri.cp.victoriashpak.util.Constants;

public class ClearMorph implements Constants {

    public static BufferedImage clean(BufferedImage source, int n) {

	int h = source.getHeight();
	int w = source.getWidth();
	int[] pixels = source.getRGB(0, 0, w, h, null, w, w);
	int[] copy = pixels.clone();
	for (int y = 1; y < h - 1; ++y) {
	    for (int x = 1; x < w - 1; ++x) {
		if (countNeighbour(pixels, w, x, y) < n) {
		    copy[w * y + x] = _0;
		}
	    }
	}
	source.setRGB(0, 0, w, h, copy, w, w);
	return source;
    }

    private static int countNeighbour(int[] pixels, int offset, int x, int y) {
	int quantity = 0;
	if (pixels[y * offset + x + 1] == _1) ++quantity;
	if (pixels[y * offset + x - 1] == _1) ++quantity;

	if (pixels[(y - 1) * offset + x] == _1) ++quantity;
	if (pixels[(y + 1) * offset + x] == _1) ++quantity;

	if (pixels[(y - 1) * offset + x + 1] == _1) ++quantity;
	if (pixels[(y - 1) * offset + x - 1] == _1) ++quantity;

	if (pixels[(y + 1) * offset + x + 1] == _1) ++quantity;
	if (pixels[(y + 1) * offset + x - 1] == _1) ++quantity;

	return quantity;
    }
}
