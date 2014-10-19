package by.slesh.ri.cp.victoriabrel.binarizators;

import java.awt.image.BufferedImage;

public class Method40PercentsBinarizator extends AbstractBinarizator {
	public static int sPercents = 40;

	@Override
	public BinaryResultBundle binarization(BufferedImage src) {
		BinaryResultBundle bundle = new BinaryResultBundle();
		int[] rgb = prepareBundleAndGetRgbPixels(src, bundle);
		bundle.threshold = findThresholdForMethod40Percents(bundle);
		repaint(bundle, rgb);
		return bundle;
	}

	private int findThresholdForMethod40Percents(BinaryResultBundle bundle) {
		double size = bundle.width * bundle.height;
		double collector = 0;
		double limit = sPercents / 100.0;
		for (int brighness = 0; brighness < 256; ++brighness) {
			collector += bundle.repeatsPixels[brighness];
			double ratio = collector / size;
			if (ratio > limit) {
				return --brighness;
			}
		}
		return 0;
	}
}
