package by.slesh.ri.cp.victoriabrel.app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.slesh.ri.cp.victoriabrel.app.model.Model;
import by.slesh.ri.cp.victoriabrel.app.view.service.BinViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.ImageBoxesViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.MainViewInterface;
import by.slesh.ri.cp.victoriabrel.util.PercentsBinarizator;
import by.slesh.ri.cp.victoriabrel.util.StepBinaryzator;
import by.slesh.ri.cp.victoriabrel.util.ThresholdBinarizator;

public class BinController implements ActionListener {

    private Model mModel;
    private BinViewInterface mBinView;
    private ImageBoxesViewInterface mImageBoxesView;

    public BinController(MainViewInterface view, Model model) {
        mModel = model;
        mImageBoxesView = view.getImageBoxesViewInterfaceImpl();
        mBinView = view.getBinViewInterfaceImpl();
        mBinView.addBinByPercentClickListener(this);
        mBinView.addBinByStepClickListener(this);
        mBinView.addBinByThresholdClickListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
        case BinViewInterface.ACTION_BIN_STEP:
            mModel.binarization(new StepBinaryzator());
            break;
        case BinViewInterface.ACTION_BIN_PERCENT:
            mModel.binarization(new PercentsBinarizator());
            break;
        case BinViewInterface.ACTION_BIN_THRESHOLD:
            mModel.binarization(new ThresholdBinarizator());
            break;
        }
        mImageBoxesView.updateTarget(mModel.getmTargetImage());
    }
}