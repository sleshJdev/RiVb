package by.slesh.ri.cp.victoriabrel.app.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import by.slesh.ri.cp.victoriabrel.util.AbstractBinarizator;
import by.slesh.ri.cp.victoriabrel.util.AbstractMorph;
import by.slesh.ri.cp.victoriabrel.util.AbstractTool;
import by.slesh.ri.cp.victoriabrel.util.BugSegmentator;
import by.slesh.ri.cp.victoriabrel.util.Contour;
import by.slesh.ri.cp.victoriabrel.util.DilateMorph;
import by.slesh.ri.cp.victoriabrel.util.ErodeMorph;
import by.slesh.ri.cp.victoriabrel.util.HistogramSegmentator;
import by.slesh.ri.cp.victoriabrel.util.Skeletonizator;
import by.slesh.ri.cp.victoriabrel.util.ThresholdBinarizator;

public class Model {

	private BufferedImage			mSourceImage;
	private BufferedImage			mTargetImage;
	private BufferedImage			mRegionImage;
	private Skeletonizator			mSkeletonizator			= new Skeletonizator();
	private HistogramSegmentator	mHistogramSegmentator	= new HistogramSegmentator();
	private BugSegmentator			mBugSegmentator			= new BugSegmentator();
	private AbstractMorph			mErodeMorph				= new ErodeMorph();
	private AbstractMorph			mDilateMorph			= new DilateMorph();

	private AbstractBinarizator		mBin;

	public void process() {
		mTargetImage = trim(mTargetImage);
		mBin = new ThresholdBinarizator();
		mTargetImage = mBin.bin(mTargetImage);
		mHistogramSegmentator.segment(mTargetImage, false);
		mTargetImage = cut(mTargetImage, 
				mHistogramSegmentator.getLeftBorder(), 
				mHistogramSegmentator.getTopBorder(),
				mHistogramSegmentator.getRightBorder(), 
				mHistogramSegmentator.getBottonBorder());
		Point entry = findEntryPoint(mTargetImage);
		mTargetImage = cut(mTargetImage, 0, entry.y - 45, mTargetImage.getWidth(), entry.y);
		entryChar = findCharacterEntryPoint(mTargetImage);
		for (int k = 0; k < 10; ++k) {
			mark(entryChar, mTargetImage);
			entryChar = moveToNextCharacter(mTargetImage, entryChar);
		}
		Rectangle rect = findRegionBorders(mTargetImage);
		mRegionImage = cut(mTargetImage, 
				rect.x - 5, 
				rect.y - 5 , 
				rect.x + rect.width + 5, 
				rect.y + rect.height + 5);
		clearRegion(mRegionImage);		
	}

	public void clearRegion(BufferedImage region) {
		int h = region.getHeight();
		int w = region.getWidth();
		int[] pixels = region.getRGB(0, 0, w, h, null, w, w);
		for (int y = 0; y <= h; ++y) {
			for (int x = 0; x < w; ++x) {
				if (pixels[w * y + x] != Color.GREEN.getRGB()) {
					pixels[w * y + x] = AbstractTool._0;
				}
			}
		}
		region.setRGB(0, 0, w, h, pixels, w, w);
	}

	public Rectangle findRegionBorders(BufferedImage source) {
		int h = source.getHeight();
		int w = source.getWidth();
		int[] pixels = source.getRGB(0, 0, w, h, null, w, w);
		int xMin = Integer.MAX_VALUE;
		int xMax = Integer.MIN_VALUE;
		int yMin = Integer.MAX_VALUE;
		int yMax = Integer.MIN_VALUE;
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				if (pixels[w * y + x] == Color.GREEN.getRGB()) {
					xMin = Math.min(xMin, x);
					xMax = Math.max(xMax, x);
					yMin = Math.min(yMin, y);
					yMax = Math.max(yMax, y);
				}
			}
		}
		return new Rectangle(new Point(xMin, yMin), new Dimension(xMax - xMin, yMax - yMin));
	}

	public void mark(Point origin, BufferedImage source) {

		h = source.getHeight();
		w = source.getWidth();
		pixels = source.getRGB(0, 0, w, h, null, w, w);
		mark(origin.x, origin.y);
		source.setRGB(0, 0, w, h, pixels, w, w);
	}

	int[]	pixels;
	int		h, w;
	int		yMin	= Integer.MAX_VALUE;
	int		yMax	= Integer.MIN_VALUE;
	Point	entryChar;

	public void mark(int x, int y) {

		if (x < 0 || x >= w || y < 0 || y >= h) { return; }

		if (pixels[w * y + x] == AbstractTool._1) {
			pixels[w * y + x] = Color.GREEN.getRGB();
			if (x < entryChar.x) {
				entryChar.x = x;
			}
			yMin = Math.min(yMin, y);
			yMax = Math.max(yMax, y);
		} else {
			return;
		}

		mark(x - 1, y - 1);
		mark(x, y - 1);
		mark(x + 1, y - 1);

		mark(x - 1, y);
		mark(x + 1, y);

		mark(x - 1, y + 1);
		mark(x, y + 1);
		mark(x + 1, y + 1);
	}

	public Point moveToNextCharacter(BufferedImage source, Point origin) {
		int h = source.getHeight();
		int w = source.getWidth();
		int[] pixels = source.getRGB(0, 0, w, h, null, w, w);
		for (int x = origin.x; x >= 0; --x) {
			if (pixels[w * origin.y + x] == AbstractTool._1) { return new Point(x, origin.y); }
		}
		return new Point();
	}

	/*
	 * Find character Move right to left, top to bottom
	 */
	public Point findCharacterEntryPoint(BufferedImage source) {
		int h = source.getHeight();
		int w = source.getWidth();
		int[] pixels = source.getRGB(0, 0, w, h, null, w, w);
		for (int x = w; x >= 0; --x) {
			for (int y = 0; y < h; ++y) {
				if (pixels[y * w + x] == AbstractTool._1) { return new Point(x, y); }
			}
		}
		return new Point();
	}

	/*
	 * Move bottom to top
	 */
	public Point findEntryPoint(BufferedImage source) {
		int h = source.getHeight();
		int w = source.getWidth();
		int[] pixels = source.getRGB(0, 0, w, h, null, w, w);
		for (int y = h - 1; y >= 0; --y) {
			if (pixels[y * w + w / 2] == AbstractTool._1) { return new Point(w / 2, y); }
		}
		return new Point();
	}

	public BufferedImage cut(BufferedImage source, int x1, int y1, int x2, int y2) {
		BufferedImage target = new BufferedImage(x2 - x1, y2 - y1, AbstractTool.IMAGE_TYPE);
		Graphics g = target.getGraphics();
		g.drawImage(source, 0, 0, x2 - x1, y2 - y1, x1, y1, x2, y2, null);
		return target;
	}

	public BufferedImage trim(BufferedImage source) {
		return trim(source, 40, 40, 40, 40);
	}

	public BufferedImage trim(BufferedImage source, int indentTop, int indentRight, int indentBottom, int indentLeft) {
		int x1 = indentLeft;
		int y1 = indentTop;
		int x2 = source.getWidth() - indentRight;
		int y2 = source.getHeight() - indentBottom;
		return cut(source, x1, y1, x2, y2);
	}

	public void binarization() {
		binarization(mBin);
	}

	public void binarization(AbstractBinarizator bin) {
		mBin = bin;
		mTargetImage = mBin.bin(mSourceImage);
	}

	public void skeletonization() {
		mTargetImage = mSkeletonizator.skeletonization(mTargetImage);
	}

	public void histogramSegment() {
		mTargetImage = mHistogramSegmentator.segment(mTargetImage, false);
	}

	public BufferedImage getmSourceImage() {
		return mSourceImage;
	}

	public void setmSourceImage(BufferedImage mSourceFrame) {
		this.mSourceImage = mSourceFrame;
	}

	public BufferedImage getmTargetImage() {
		return mTargetImage;
	}

	public void setmTargetImage(BufferedImage mTargetFrame) {
		this.mTargetImage = mTargetFrame;
	}

	public BufferedImage getmRegionImage() {
		return mRegionImage;
	}

	public void bugSegment() {
		Contour contour = mBugSegmentator.segment(mTargetImage);
		mTargetImage = contour.sub(mTargetImage);
	}

	public void setStartPointForBugSegmentator(Point point) {
		mBugSegmentator.setmFrom(point);
		Graphics g = mTargetImage.getGraphics();
		g.setColor(Color.BLUE);
		g.fillOval(point.x - 5, point.y - 5, 10, 10);
	}
}
