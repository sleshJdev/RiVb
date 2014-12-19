package by.slesh.ri.cp.victoriashpak.util.bin;

import java.awt.image.BufferedImage;

import by.slesh.ri.cp.victoriashpak.util.G;

public abstract class AbstractBinarizator extends G {

    public abstract BufferedImage bin(BufferedImage source);

    public BufferedImage binarization(BufferedImage source, int threshold) {
	int w = source.getWidth();
	int h = source.getHeight();
	int[] rgb = source.getRGB(0, 0, w, h, null, w, w);
	for (int y = 0; y < h; ++y) {
	    for (int x = 0; x < w; ++x) {
		if (rgbToBrightness(rgb[w * y + x]) > threshold) {
		    rgb[w * y + x] = _0;
		} else {
		    rgb[w * y + x] = _1;
		}
	    }
	}
	return rgbToImage(rgb, w, h);
    }
}
