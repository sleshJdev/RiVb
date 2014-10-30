package by.slesh.ri.cp.victoriabrel.binarizators;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RatioBinaryzator extends AbstractBinarizator {

	@Override
	public BinaryResultBundle binarization(BufferedImage src) {
		BinaryResultBundle bundle = new BinaryResultBundle();
		int[] rgb = prepareBundleAndGetRgbPixels(src, bundle);
		bundle.threshold = -1;
		repaint(bundle, rgb);
		return bundle;
	}

	@Override
	protected void repaint(BinaryResultBundle bundle, int[] rgbPixels) {
		Graphics holder = bundle.target.getGraphics();
		int index = 0;
		for (int y = 0; y < bundle.height; ++y) {
			for (int x = 0; x < bundle.width; ++x) {
				Color pixel = new Color(rgbPixels[index++]);
				double ratio = (double) pixel.getRed() / pixel.getGreen();
				System.out.println(ratio);
				if (1.05 < ratio && ratio < 4.0) {
					holder.setColor(Color.BLACK);
				} else {
					holder.setColor(Color.WHITE);
				}
				holder.drawLine(x, y, x, y);
			}
		}
	}
}
