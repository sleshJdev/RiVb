package by.slesh.ri.cp.victoriabrel.app.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import by.slesh.ri.cp.victoriabrel.util.AbstractBinarizator;
import by.slesh.ri.cp.victoriabrel.util.BugSegmentator;
import by.slesh.ri.cp.victoriabrel.util.Contour;
import by.slesh.ri.cp.victoriabrel.util.HistogramSegmentator;
import by.slesh.ri.cp.victoriabrel.util.Skeletonizator;

public class Model {

    private BufferedImage mSourceImage;
    private BufferedImage mTargetImage;
    private Skeletonizator mSkeletonizator = new Skeletonizator();
    private HistogramSegmentator mHistogramSegmentator = new HistogramSegmentator();
    private BugSegmentator mBugSegmentator = new BugSegmentator();

    private AbstractBinarizator mBin;

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
        mTargetImage = mHistogramSegmentator.segment(mTargetImage);
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
