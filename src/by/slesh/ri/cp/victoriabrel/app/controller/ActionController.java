package by.slesh.ri.cp.victoriabrel.app.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import by.slesh.ri.cp.victoriabrel.app.model.Model;
import by.slesh.ri.cp.victoriabrel.app.view.service.ActionViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.ImageBoxesViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.MainViewInterface;

public class ActionController implements ActionListener {

    private class MouseClickAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent arg0) {
            Point location = new Point(arg0.getPoint());
            mImageBoxesView.getLocationOnImage(location);
            System.out.println("1=" + arg0.getPoint());
            System.out.println("2=" + location);
            mModel.setStartPointForBugSegmentator(location);
            mImageBoxesView.updateTarget(mModel.getmTargetImage());
        }
    }

    private Model mModel;
    private ActionViewInterface mActionView;
    private ImageBoxesViewInterface mImageBoxesView;

    public ActionController(MainViewInterface view, Model model) {
        mModel = model;
        mImageBoxesView = view.getImageBoxesViewInterfaceImpl();
        mImageBoxesView.addTargetImageBoxClickListener(new MouseClickAdapter());
        mActionView = view.getActionViewInterfaceImpl();
        mActionView.addSkeletizationClickListener(this);
        mActionView.addBugSegmentClickListener(this);
        mActionView.addHistogramSegmentClickListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case ActionViewInterface.ACTION_SKELETON:
            mModel.skeletonization();
            mImageBoxesView.updateTarget(mModel.getmTargetImage());
            break;
        case ActionViewInterface.ACTION_SEGMENT_HISTOGRAM:
            mModel.binarization();
            mModel.histogramSegment();
            mImageBoxesView.updateTarget(mModel.getmTargetImage());
            break;
        case ActionViewInterface.ACTION_SEGMENT_BUG:
            mModel.bugSegment();
            mImageBoxesView.updateTarget(mModel.getmTargetImage());
            break;
        }
    }
}
