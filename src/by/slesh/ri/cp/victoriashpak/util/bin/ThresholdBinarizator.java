package by.slesh.ri.cp.victoriashpak.util.bin;

import java.awt.image.BufferedImage;

public class ThresholdBinarizator extends AbstractBinarizator {
    public static int sThreshold = 220;

    @Override
    public BufferedImage bin(BufferedImage source) {
	return super.binarization(source, sThreshold);
    }
}
