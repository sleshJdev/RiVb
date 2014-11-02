package by.slesh.ri.cp.victoriabrel.util;

import java.awt.image.BufferedImage;

public class StepBinaryzator extends AbstractBinarizator {

    private static final int INIT_THRESHOLD = 120;
    public static int sOffset = 120; // 13..15

    @Override
    public BufferedImage bin(BufferedImage source) {
        return super.binarization(source, findThreshold(source));
    }

    /*
     * Find threshold value for method 120
     */
    private int findThreshold(BufferedImage source) {
        int threshold = INIT_THRESHOLD;
        int[] repeats = countBrightnessRepeats(source);
        while (threshold-- > 0) {
            if (repeats[threshold--] > 0) {
                threshold += StepBinaryzator.sOffset;
                return threshold;
            }
        }
        return INIT_THRESHOLD;
    }
}
