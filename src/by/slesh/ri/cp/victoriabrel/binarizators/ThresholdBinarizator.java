package by.slesh.ri.cp.victoriabrel.binarizators;

import java.awt.image.BufferedImage;

public class ThresholdBinarizator extends AbstractBinarizator implements ThresholdChangeValueListener {
	private int mThreshold;

	@Override
	public BinaryResultBundle binarization(BufferedImage src) {
		BinaryResultBundle bundle = new BinaryResultBundle();
		int[] rgb = prepareBundleAndGetRgbPixels(src, bundle);
		bundle.threshold = mThreshold;
		repaint(bundle, rgb);
		return bundle;
	}

	@Override
	public void setNewThresholdValue(int threshold) {
		mThreshold = threshold;

	}

}
