package by.slesh.ri.cp.victoriabrel.app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import by.slesh.ri.cp.victoriabrel.app.model.Model;
import by.slesh.ri.cp.victoriabrel.app.view.service.FileViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.ImageBoxesViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.MainViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.OpenListener;

public class FileController implements OpenListener, ActionListener {

	private Model					mModel;
	private MainViewInterface		mView;
	private FileViewInterface		mFileView;
	private ImageBoxesViewInterface	mImageBoxesView;

	public FileController(MainViewInterface view, Model model) {
		mModel = model;
		mView = view;
		mImageBoxesView = view.getImageBoxesViewInterfaceImpl();
		mFileView = view.getFileViewInterfaceImpl();
		mFileView.addOpenClickListener(this);
		mFileView.addSettingClickListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case FileViewInterface.ACTION_SETTINGS:
			mView.showBinSettings();
			break;
		}
	}

	@Override
	public void openImage(File file) {
		try {
			BufferedImage image = ImageIO.read(file);
			mModel.setmSourceImage(image);
			mModel.setmTargetImage(image);
			mModel.process();
			mImageBoxesView.updateSource(mModel.getmSourceImage());
			mImageBoxesView.updateTarget(mModel.getmTargetImage());
			mImageBoxesView.updateRegion(mModel.getmRegionImage());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
