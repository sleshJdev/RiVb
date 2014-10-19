package by.slesh.ri.cp.victoriabrel.binarizators;

import java.awt.image.BufferedImage;

public class ThresholdBinarizator extends AbstractBinarizator {
	public static int sThreshold;

	@Override
	public BinaryResultBundle binarization(BufferedImage src) {
		BinaryResultBundle bundle = new BinaryResultBundle();
		int[] rgb = prepareBundleAndGetRgbPixels(src, bundle);
		bundle.threshold = sThreshold;
		repaint(bundle, rgb);
		return bundle;
	}
}
