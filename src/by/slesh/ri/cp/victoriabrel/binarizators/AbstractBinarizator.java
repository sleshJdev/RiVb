package by.slesh.ri.cp.victoriabrel.binarizators;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class AbstractBinarizator {
	private static int sImageType = BufferedImage.TYPE_3BYTE_BGR;

	protected int[] prepareBundleAndGetRgbPixels(BufferedImage src, BinaryResultBundle bundle) {
		int w = src.getWidth();
		int h = src.getHeight();
		int[] rgbPixels = src.getRGB(0, 0, w, h, null, w, w);
		bundle.width = w;
		bundle.height = h;
		bundle.repeatsPixels = countRepeatsPixels(rgbPixelsToGray(rgbPixels));
		bundle.target = new BufferedImage(w, h, AbstractBinarizator.sImageType);
		return rgbPixels;
	}

	public abstract BinaryResultBundle binarization(BufferedImage src);

	protected void repaint(BinaryResultBundle bundle, int[] rgbPixels) {
		Graphics holder = bundle.target.getGraphics();
		int index = 0;
		for (int y = 0; y < bundle.height; ++y) {
			for (int x = 0; x < bundle.width; ++x) {
				if (rgbToGray(rgbPixels[index++]) > bundle.threshold) {
					holder.setColor(Color.WHITE);
				} else {
					holder.setColor(Color.BLACK);
				}
				holder.drawLine(x, y, x, y);
			}
		}
	}

	protected int[] rgbPixelsToGray(int[] rgbPixels) {
		int[] grayPixels = new int[rgbPixels.length];
		for (int index = 0; index < rgbPixels.length; ++index) {
			grayPixels[index] = rgbToGray(rgbPixels[index]);
		}
		return grayPixels;
	}

	protected int[] countRepeatsPixels(int[] grayPixels) {
		int[] repeatsPixels = new int[256];
		for (int index = 0; index < grayPixels.length; ++index) {
			++repeatsPixels[grayPixels[index]];
		}
		return repeatsPixels;
	}

	protected int rgbToGray(Color pixel) {
		int red = pixel.getRed();
		int green = pixel.getGreen();
		int blue = pixel.getBlue();
		return (red + green + blue) / 3;
	}

	protected int rgbToGray(int rgb) {
		Color pixel = new Color(rgb);
		return rgbToGray(pixel);
	}
}
