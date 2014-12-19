/**
 * 
 */
package by.slesh.ri.cp.victoriashpak.app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import by.slesh.ri.cp.victoriashpak.app.model.Model;
import by.slesh.ri.cp.victoriashpak.app.view.ControlViewInterface;
import by.slesh.ri.cp.victoriashpak.app.view.ImageBoxesViewInterface;
import by.slesh.ri.cp.victoriashpak.app.view.MainViewInterface;
import by.slesh.ri.cp.victoriashpak.util.G;

/**
 * @author slesh
 *
 */
public class Controller implements ActionListener {
    private MainViewInterface mMainFrameView;
    private ControlViewInterface mControlPanelView;
    private ImageBoxesViewInterface mImageBoxesView;
    private Model mModel;

    public Controller(MainViewInterface mainViewInterface, ControlViewInterface controlViewInterface,
	    ImageBoxesViewInterface imageBoxesViewInterface, Model model) {

	mMainFrameView = mainViewInterface;
	mImageBoxesView = imageBoxesViewInterface;
	mControlPanelView = controlViewInterface;
	mModel = model;
	
	mControlPanelView.addFindSymbolClickListener(this);
	mControlPanelView.addBinarizateClickListener(this);
	mControlPanelView.addOpenFileClickListener(this);
	mControlPanelView.addHistogramSegmentClickListener(this);
	mControlPanelView.addTrimClickListener(this);
	mControlPanelView.addExtractAreaInterestClickListener(this);
	mControlPanelView.addExtractFullNameClickListener(this);
	mControlPanelView.addSegmentFullNameClickListener(this);
	mControlPanelView.addRecognizeNumberClickListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	switch (arg0.getActionCommand()) {
	case ControlViewInterface.ACTION_FILE_OPEN:
	    BufferedImage source = openFile();
	    if (source == null) return;
	    mModel.setSource(source);
	    mMainFrameView.updateSource(mModel.getSource());
	    break;
	case ControlViewInterface.ACTION_BINARIZATION:
	    mModel.binarization();
	    mMainFrameView.updateSource(mModel.getSource());
	    break;
	case ControlViewInterface.ACTION_SEGMENT_HISTOGRAM:
	    mModel.histogramSegment();
	    mMainFrameView.updateSource(mModel.getSource());
	    break;
	case ControlViewInterface.ACTION_TRIM:
	    mModel.trim();
	    mMainFrameView.updateSource(mModel.getSource());
	    break;
	case ControlViewInterface.ACTION_FIND_SYMBOL:
	    mModel.findSymbols();
	    mMainFrameView.updateSource(mModel.getSource());
	    break;
	case ControlViewInterface.ACTION_EXTRACT_AREA_INTEREST:
	    mModel.extractAreaInterest();
	    mMainFrameView.updateSource(mModel.getSource());
	    break;
	case ControlViewInterface.ACTION_EXTRACT_FULLNAME:
	    mModel.extractFullName();
	    mImageBoxesView.updateAreaInterest(mModel.getSource());
	    break;
	case ControlViewInterface.ACTION_SEGMENT_FULLNAME:
	    mImageBoxesView.updateSegmentFullName(mModel.segmentGroupNumber());
	    mImageBoxesView.updateUnrecognizeFullName(mModel
		    .skeletonizationSegmentSymbols());
	    break;
	case ControlViewInterface.ACTION_RECOGNIZE:
	    mImageBoxesView.updateRecognize(mModel.recognize());
	    break;
	}
	G.sMainView.refresh();
    }

    private static final JFileChooser FC = new JFileChooser(".");

    public static BufferedImage openFile() {
	FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
	BufferedImage loadedImage = null;
	int returnValue = FC.showDialog(null, "Этот файл");
	if (returnValue == JFileChooser.APPROVE_OPTION) {
	    File file = FC.getSelectedFile();
	    try {
		loadedImage = ImageIO.read(file);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return loadedImage;
    }
}
