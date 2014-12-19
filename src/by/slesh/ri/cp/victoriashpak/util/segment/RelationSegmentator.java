/**
 * 
 */
package by.slesh.ri.cp.victoriashpak.util.segment;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import by.slesh.ri.cp.victoriashpak.util.Symbol;
import by.slesh.ri.cp.victoriashpak.util.Tool;

/**
 * @author slesh
 *
 */
public class RelationSegmentator {
    private int mWidth;
    private int mHeight;
    private int[] mPixelData;
    private List<Symbol> mSymbols = new ArrayList<Symbol>();

    public RelationSegmentator(BufferedImage source) {
	mWidth = source.getWidth();
	mHeight = source.getHeight();
	mPixelData = source.getRGB(0, 0, mWidth, mHeight, null, 0, mWidth);
    }
    
    private Symbol mCurrentSymbol;
    private int mQuantityPixels;

    public BufferedImage[] segment() {
	for (int x = 0; x < mWidth; ++x) {
	    int index = mWidth * (mHeight / 2) + x;
	    if (mPixelData[index] == Tool._1) {
		mCurrentSymbol = new Symbol(createColor());
		mQuantityPixels = 0;
		mark(index);
		System.out.println("qqqqqqqq="+mQuantityPixels);
		if (mQuantityPixels > 20) mSymbols.add(mCurrentSymbol);
	    }
	}
	BufferedImage[] digits = new BufferedImage[mSymbols.size()];
	for (int k = 0; k < digits.length; ++k) {
	    digits[k] = mSymbols.get(k).toImage();
	}
	return digits;
    }

    private void mark(int index) {
	if (index < 0 || index >= mPixelData.length) return;
	if (mPixelData[index] == Tool._0) return;
	if (mPixelData[index] == mCurrentSymbol.getColor().getRGB()) return;
	for (Symbol digit : mSymbols) {
	    if (mPixelData[index] == digit.getColor().getRGB()) return;
	}
	mPixelData[index] = mCurrentSymbol.getColor().getRGB();
	mCurrentSymbol.addPoint(new Point(index % mWidth, index / mWidth));
	++mQuantityPixels;

	mark(index - mWidth);
	mark(index + mWidth);

	mark(index - 1);
	mark(index + 1);

	mark(index - mWidth - 1);
	mark(index - mWidth + 1);

	mark(index + mWidth - 1);
	mark(index + mWidth + 1);
    }

    private static final Random GENERATOR = new Random();

    private static final Color createColor() {
	int r = GENERATOR.nextInt(200);
	int g = GENERATOR.nextInt(200);
	int b = GENERATOR.nextInt(200);
	return new Color(r, g, b);
    }
}
