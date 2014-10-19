package by.slesh.ri.cp.victoriabrel.binarizators;

import java.awt.image.BufferedImage;

public class Method40PercentsBinarizator extends AbstractBinarizator {
	private static final int INIT_PERCENTS = 40;

	@Override
	public BinaryResultBundle binarization(BufferedImage src) {
		BinaryResultBundle bundle = new BinaryResultBundle();
		int[] rgb = prepareBundleAndGetRgbPixels(src, bundle);
		bundle.threshold = findThresholdForMethod40Percents(bundle);
		repaint(bundle, rgb);
		return bundle;
	}

	private int findThresholdForMethod40Percents(BinaryResultBundle bundle) {
		int size = bundle.width * bundle.height;
		int constrain = size * INIT_PERCENTS / 100;
		int collector = 0;
		for (int brighness = 0; brighness < 256; ++brighness) {
			collector += bundle.repeatsPixels[brighness];
			if (collector / size > constrain) {
				return --brighness;
			}
		}
		return 0;
	}

}
