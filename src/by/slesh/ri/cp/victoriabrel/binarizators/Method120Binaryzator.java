package by.slesh.ri.cp.victoriabrel.binarizators;

import java.awt.image.BufferedImage;

public class Method120Binaryzator extends AbstractBinarizator {
	private static final int INIT_THRESHOLD = 120;
	public static int        sOffset        = 13; // 13..15

	@Override
	public BinaryResultBundle binarization(BufferedImage src) {
		BinaryResultBundle bundle = new BinaryResultBundle();
		int[] rgb = prepareBundleAndGetRgbPixels(src, bundle);
		bundle.threshold = findThresholdForMethod120(rgb);
		repaint(bundle, rgb);
		return bundle;
	}

	private int findThresholdForMethod120(int[] repeatsPixels) {
		int threshold = INIT_THRESHOLD;
		while (threshold-- > 0) {
			if (repeatsPixels[threshold--] > 0) {
				threshold += Method120Binaryzator.sOffset;
				return threshold;
			}
		}
		return INIT_THRESHOLD;
	}
}
