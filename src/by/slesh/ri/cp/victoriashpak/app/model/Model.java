package by.slesh.ri.cp.victoriashpak.app.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import by.slesh.ri.cp.victoriashpak.util.Constants;
import by.slesh.ri.cp.victoriashpak.util.ContourWorker;
import by.slesh.ri.cp.victoriashpak.util.Recognizer;
import by.slesh.ri.cp.victoriashpak.util.Resizer;
import by.slesh.ri.cp.victoriashpak.util.Rotator;
import by.slesh.ri.cp.victoriashpak.util.Tool;
import by.slesh.ri.cp.victoriashpak.util.bin.AbstractBinarizator;
import by.slesh.ri.cp.victoriashpak.util.bin.Binarizator;
import by.slesh.ri.cp.victoriashpak.util.morph.ClearMorph;
import by.slesh.ri.cp.victoriashpak.util.morph.ErodeMorph;
import by.slesh.ri.cp.victoriashpak.util.morph.LineDestroyer;
import by.slesh.ri.cp.victoriashpak.util.morph.SimpleSkeletonizator;
import by.slesh.ri.cp.victoriashpak.util.morph.Skeletonizator;
import by.slesh.ri.cp.victoriashpak.util.segment.BugSegmentator;
import by.slesh.ri.cp.victoriashpak.util.segment.Contour;
import by.slesh.ri.cp.victoriashpak.util.segment.HistogramSegmentator;
import by.slesh.ri.cp.victoriashpak.util.segment.RelationSegmentator;

public class Model {
    private BufferedImage mSource;
    private BufferedImage mOrigin;

    private static final int TARGET_WIDTH = 2552;
    private static final int TARGET_HEIGHT = 3510;

    public void setSource(BufferedImage source) {
	mSource = source;
	int h = mSource.getHeight();
	int w = mSource.getWidth();

	if (w < TARGET_WIDTH)
	    mSource = Resizer.scaleUp(mSource, TARGET_WIDTH, h, true);
	if (w > TARGET_WIDTH)
	    mSource = Resizer.scaleLow(mSource, TARGET_WIDTH, h, true);

	w = TARGET_WIDTH;

	if (h < TARGET_HEIGHT)
	    mSource = Resizer.scaleUp(mSource, w, TARGET_HEIGHT, true);
	if (h > TARGET_HEIGHT)
	    mSource = Resizer.scaleLow(mSource, w, TARGET_HEIGHT, true);
    
	mOrigin = Tool.copyOf(mSource);
    }

    public BufferedImage getSource() {
	return mSource;
    }

    public void binarization(AbstractBinarizator binarizator) {
	mSource = binarizator.bin(mOrigin);
	mSource = ClearMorph.clean(mSource, 5);
	mSource = Rotator.rotate(mSource);
	mSource = Tool.trim(mSource, 100, 100, 100, 100);
	mSource = Binarizator.binarization(mSource);
    }

    public void skeletonization() {
	Skeletonizator skeletonizator = new Skeletonizator();
	mSource = skeletonizator.skeletonization(mSource);
    }

    public void histogramSegment() {
	HistogramSegmentator histogramSegmentator = new HistogramSegmentator();
	histogramSegmentator.segment(mSource, 20, 100, true);
	xLeft = histogramSegmentator.getLeftBorder();
	yTop = histogramSegmentator.getTopBorder();
	yBottom = histogramSegmentator.getBottomBorder();
    }

    private int xLeft;
    private int yTop;
    private int yBottom;

    public BufferedImage trim() {
	int w = mSource.getWidth();
	mSource = Tool.cut(mSource, xLeft + 1, yTop + 1, w, yBottom - 1);
	return mSource;
    }

    private int mYOfPLeter;

    private static final int WH = 50;
    private static final int WW = 35;

    public void findSymbols() {
	int h = mSource.getHeight();
	int w = mSource.getWidth();
	int[] pixels = mSource.getRGB(0, 0, w, h, null, 0, w);
	Tool.makeEmptyBorders(mSource);
	Graphics g = mSource.getGraphics();
	g.setColor(Color.GREEN);
	boolean isWork = true;
	for (int y = WH; isWork; ++y) {
	    for (int x = 0; x < WW && isWork; ++x) {
		if (pixels[w * y + x] == Tool._1) {
		    if (y >= h - WH) isWork = false;
		    int y1 = y - 5;
		    BufferedImage subImage = mSource.getSubimage(0, y1, WW, WH);
		    double np = countBlackPixels(subImage);
		    if (np / WW / WH > 0.3) {
//			subImage = SimpleSkeletonizator.skeleton(subImage);
			Tool.makeEmptyBorders(subImage);
			ContourWorker cw = new ContourWorker(subImage);
			cw.drawResultOn(subImage);
			Point[] centers = cw.getCentersOfContours();
			int cs = Tool.countSingletons(subImage);
//			if (centers.length == 1 && cs >= 1) {
			if (centers.length == 1) {
			    if (centers[0].y < WH / 2) {
				try {
				    BufferedImage arrow = ImageIO
					    .read(new File("arrow.png"));
				    int arrowH = arrow.getHeight();
				    g.drawImage(arrow, WW, y1 - arrowH, null);
				} catch (IOException e) {
				    e.printStackTrace();
				}
				mYOfPLeter = y1;
			    }
			} else {
			    g.drawRect(0, y1, WW, WH);
			}
			y += WH;
		    }
		}
	    }
	}

    }

    public void extractAreaInterest() {
	ErodeMorph.morph(mSource, Constants.SIMPLE_KERNEL);
	int y0 = mYOfPLeter + WH / 2;
	int yTop = 0;
	int yLow = Integer.MIN_VALUE;

	int xLeft = WH;
	int xRight = WH;

	int h = mSource.getHeight();
	int w = mSource.getWidth();
	int[] pixels = mSource.getRGB(0, 0, w, h, null, 0, w);

	Graphics g = mSource.getGraphics();
	g.setColor(Color.RED);

	BugSegmentator bugSegmentator = new BugSegmentator();

	for (int k = 0; k < 11; ++k) {
	    xLeft = xRight;
	    for (int x = xLeft; x < w; ++x) {
		if (pixels[y0 * w + x] == Tool._1) {
		    xLeft = x - 1;
		    break;
		}
	    }
	    bugSegmentator.setFrom(new Point(xLeft, y0));
	    Contour contour = bugSegmentator.segment(mSource);
	    contour.drawOnImage(mSource);
	    xRight = contour.getMaxX();
	    if (contour.getMaxY() > yLow) yLow = contour.getMaxY();
	    int counter = 0;
	    for (int x = xRight; x < w; ++x) {
		if (pixels[w * y0 + x] == Constants._0) ++counter;
		else break;
	    }
	    if (counter > 20) break;
	}

	for (int x = xRight; x < w; ++x) {
	    boolean isTopBorderFound = false;
	    for (int y = y0; y > 0; --y)
		if (pixels[w * y + x] == Constants._1) {
		    isTopBorderFound = true;
		    yTop = y;
		    break;
		}

	    boolean isLowBotderFound = false;
	    for (int y = y0; y <= yLow; ++y)
		if (pixels[w * y + x] == Constants._1) {
		    isLowBotderFound = true;
		    yLow = y;
		    break;
		}

	    if (isTopBorderFound && isLowBotderFound) {
		xLeft = x + 1;
		break;
	    }
	}

	g.drawRect(xLeft, yTop, 860, yLow - yTop);
	yTop += 5;
	yLow -= 5;

	++xLeft;
	mArea = new Rectangle(xLeft, yTop, 860, yLow - yTop);
    }

    private Rectangle mArea;

    public void extractFullName() {
	mSource = Tool.cut(mSource, mArea.x, mArea.y, mArea.x + mArea.width,
	        mArea.y + mArea.height);
	mSource = LineDestroyer.destroyHorizontal(mSource, 1, 0.6);
	mSource = LineDestroyer.destroyVertical(mSource, 1, 0.8);
	mSource = Tool.centrain(mSource);
	mSource = Tool.centrain(mSource);
    }

    private BufferedImage[] mSymbols;

    public BufferedImage[] segmentFullName() {
	mSource = Tool.centrain(mSource);
	RelationSegmentator rs = new RelationSegmentator(mSource);
	mSymbols = rs.segment();
	for (BufferedImage symbol : mSymbols) {
	    Tool.drawBlackIfNotWhite(symbol);
        }
	return mSymbols;
    }

    public BufferedImage[] skeletonizationSegmentSymbols() {
	BufferedImage[] digits = new BufferedImage[mSymbols.length];
	for (int t = 0; t < mSymbols.length; ++t) {
	    BufferedImage digit = Tool.trim(mSymbols[t], 0, 0, 0, 0);
	    int h = digit.getHeight();
	    int w = digit.getWidth();
	    int[] pixels = digit.getRGB(0, 0, w, h, null, 0, w);
	    for (int k = 0; k < pixels.length; ++k) {
		if (pixels[k] != Tool._0) pixels[k] = Tool._1;
	    }
	    digit.setRGB(0, 0, w, h, pixels, 0, w);
	    digit = SimpleSkeletonizator.skeleton(digit);
	    digit = Tool.centrain1(digit);
	    digits[t] = digit;
	}
	mSymbols = digits;
	return mSymbols;
    }

    public BufferedImage[] recognize() {
	return Recognizer.recognize(mSymbols);
    }

    private int countBlackPixels(BufferedImage image) {
	int quantity = 0;
	for (int y = 0; y < image.getHeight(); ++y) {
	    for (int x = 0; x < image.getWidth(); ++x) {
		if (image.getRGB(x, y) == Tool._1) ++quantity;
	    }
	}
	return quantity;
    }
}