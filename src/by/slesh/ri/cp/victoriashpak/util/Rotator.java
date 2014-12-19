/**
 * 
 */
package by.slesh.ri.cp.victoriashpak.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

import by.slesh.ri.cp.victoriashpak.util.segment.HistogramSegmentator;

/**
 * @author slesh
 *
 */
public class Rotator {
    public static BufferedImage rotate(BufferedImage source) {
	int h = source.getHeight();
	int w = source.getWidth();
	int[] pixels = source.getRGB(0, 0, w, h, null, 0, w);

	HistogramSegmentator segmentator = new HistogramSegmentator();
	segmentator.segment(source, 20, 100, true);

	int x1 = 600;
	int y1 = h - 20;
	for (; y1 > 0; --y1) {
	    boolean isBreak = true;
	    for (int x = x1; x < x1 + 50; ++x) {
		if (pixels[w * y1 + x] == Tool._0) isBreak = false;
	    }
	    if (isBreak) break;
	    pixels[w * y1 + x1] = Color.RED.getRGB();
	}

	int x2 = x1 + 300;
	int y2 = h - 20;
	for (; y2 > 0; --y2) {
	    boolean isBreak = true;
	    for (int x = x2; x < x2 + 50; ++x) {
		if (pixels[w * y2 + x] == Tool._0) isBreak = false;
	    }
	    if (isBreak) break;
	    pixels[w * y2 + x2] = Color.RED.getRGB();
	}

	if (y2 - y1 == 0) return source;

	double angle = Math.atan2(y2 - y1, (x2 - x1));
	System.out.println("Rotate angle " + angle);
	source.setRGB(0, 0, w, h, pixels, 0, w);
	return Tool.rotate(source, -angle);
	// return source;
    }
}
