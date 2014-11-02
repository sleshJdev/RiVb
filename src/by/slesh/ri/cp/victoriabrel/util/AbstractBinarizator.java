package by.slesh.ri.cp.victoriabrel.util;

import java.awt.image.BufferedImage;

public abstract class AbstractBinarizator extends AbstractTool {

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
