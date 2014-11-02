package by.slesh.ri.cp.victoriabrel.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class AbstractTool {

    protected static final int IMAGE_TYPE = BufferedImage.TYPE_4BYTE_ABGR;

    public static final int _0 = Color.WHITE.getRGB();
    public static final int _1 = Color.BLACK.getRGB();

    protected static BufferedImage rgbToImage(int[] rgb, int w, int h) {
        BufferedImage target = new BufferedImage(w, h, IMAGE_TYPE);
        target.setRGB(0, 0, w, h, rgb, w, w);
        return target;
    }

    protected static int readBrightness(int rgb) {
        return (rgb >> 0) & 0xff;
    }

    protected static int brightnessToRgb(int value) {
        return brightnessToRgb(255, value, value, value);
    }

    protected static int brightnessToRgb(double a, double r, double g, double b) {
        return brightnessToRgb((int) a, (int) r, (int) g, (int) b);
    }

    protected static int brightnessToRgb(int a, int r, int g, int b) {
        return ((a & 0xff) << 24) | ((r & 0xff) << 16) | ((g & 0xff) << 8) | ((b & 0xff) << 0);
    }

    protected static int rgbToBrightness(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb >> 0) & 0xff;
        rgb = (r * 77 + g * 151 + b * 28) >> 8;
        return rgb;
    }

    public static int[] countBrightnessRepeats(BufferedImage source) {
        int[] rgb = source.getRGB(0, 0, source.getWidth(), source.getHeight(), null, source.getWidth(),
                source.getWidth());
        int[] repeats = new int[256];
        for (int index = 0; index < rgb.length; ++index) {
            ++repeats[rgbToBrightness(rgb[index])];
        }
        return repeats;
    }
}
