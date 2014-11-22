package by.slesh.ri.cp.victoriabrel.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class HistogramSegmentator extends AbstractTool {

	private int[]			histogramX;
	private int[]			histogramY;

	private int				thresholdbyY	= 20;
	private int				thresholdbyX	= 2;

	private List<Integer>	linesX			= new ArrayList<Integer>();
	private List<Integer>	linesY			= new ArrayList<Integer>();

	private int				w;
	private int				h;

	public int getThreshold() {

		return thresholdbyY;
	}

	public void setThreshold(int threshold) {
		this.thresholdbyY = threshold;
	}

	public int getXByLineNumber(int lineNumber) {
		if (lineNumber < 0 || lineNumber > linesX.size()) return -1;
		return linesX.get(lineNumber);
	}

	public int getYByLineNumber(int lineNumber) {

		if (lineNumber < 0 || lineNumber > linesY.size()) return -1;
		return linesY.get(lineNumber);
	}

	BufferedImage	source;

	public BufferedImage segment(BufferedImage source, boolean isDrawMarkers) {

		linesX.clear();
		linesY.clear();

		this.source = source;

		h = source.getHeight();
		w = source.getWidth();
		countHistogram(source.getRGB(0, 0, w, h, null, w, w), h, w);
		Graphics g = source.getGraphics();
		
		g.setColor(Color.RED);
		if (histogramX == null || histogramY == null) return null;
		for (int x = 1; x < histogramX.length; ++x) {
			int jump = Math.abs(histogramX[x] - histogramX[x - 1]);
			if (jump > thresholdbyX) {
				if (isDrawMarkers) g.drawLine(x, 0, x, h);
				linesX.add(x);
			}
		}
		
		g.setColor(Color.GREEN);
		for (int y = 1; y < histogramY.length; ++y) {
			int jump = Math.abs(histogramY[y] - histogramY[y - 1]);
			if (jump > thresholdbyY) {
				if (isDrawMarkers) g.drawLine(0, y, w, y);
				linesY.add(y);
			}
		}

		return source;
	}

	public void drawBorders(BufferedImage source){
		int a = getLeftBorder();
		int b = getRightBorder();
		int c = getTopBorder();
		int d = getBottonBorder();
		Graphics g = source.getGraphics();
		g.setColor(Color.GREEN);
		g.drawLine(a, c, b, c);
		g.drawLine(a, d, b, d);
		g.drawLine(a, c, a, d);
		g.drawLine(b, c, b, d);
	}
	
	public int getLeftBorder() {
		if (linesX.size() == 0) return 0;
		for (int k = 1; k < linesX.size(); ++k) {
			int x = linesX.get(k);
			if (x > 10) return x;
		}
		return 0;
	}

	public int getRightBorder() {
		if (linesX.size() == 0) return 0;
		for (int k = linesX.size() - 1; k >= 1; --k) {
			int x = linesX.get(k);
			if (x < w - 10) return x;
		}
		return 0;
	}

	public int getBottonBorder() {
		if (linesY.size() == 0) return 0;
		for (int k = linesY.size() - 1; k >= 1; --k) {
			int y = linesY.get(k);
			if (y < h - 10) return y;
		}
		return 0;
	}

	public int getTopBorder() {
		if (linesY.size() == 0) return 0;
		for (int k = 0; k < linesY.size(); ++k) {
			int y = linesY.get(k);
			if (y > 10) return y;
		}
		return 0;
	}

	private void countHistogram(int[] rgb, int h, int w) {
		histogramX = new int[w];
		histogramY = new int[h];
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				if (rgb[y * w + x] == _1) {
					++histogramX[x];
					++histogramY[y];
				}
			}
		}
	}
}
