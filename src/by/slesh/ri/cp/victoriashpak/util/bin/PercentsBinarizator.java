package by.slesh.ri.cp.victoriashpak.util.bin;

import java.awt.image.BufferedImage;

public class PercentsBinarizator extends AbstractBinarizator {
    public static int sPercents = 30;

    @Override
    public BufferedImage bin(BufferedImage source) {
	return super.binarization(source, findThreshold(source));
    }

    /*
     * Find threshold value for method 40 percents
     */
    private int findThreshold(BufferedImage source) {
	double limit = sPercents / 100.0;
	double size = source.getWidth() * source.getHeight();
	int[] repeats = countBrightnessRepeats(source);
	double collector = 0;
	for (int brighness = 0; brighness < 256; ++brighness) {
	    collector += repeats[brighness];
	    double ratio = collector / size;
	    if (ratio > limit) {
		return --brighness;
	    }
	}
	return 0;
    }
}
