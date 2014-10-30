package by.slesh.ri.cp.victoriabrel.gui;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.slesh.ri.cp.victoriabrel.binarizators.AbstractBinarizator;
import by.slesh.ri.cp.victoriabrel.binarizators.BinaryResultBundle;
import by.slesh.ri.cp.victoriabrel.ipt.Skeletonizator;

public class SurfacePanel extends JPanel {
	private static final long serialVersionUID = -3893100366850568189L;
	private JLabel            mSourceBox;
	private JLabel            mTargetBox;

	private BufferedImage     mSource;
	private BufferedImage     mTarget;

	public SurfacePanel() {
		initGui();
	}

	public void setImage(BufferedImage image) {
		mSource = image;
		mSourceBox.setIcon(new ImageIcon(mSource));
	}

	public void skeletonize(){
	    Skeletonizator s = new Skeletonizator();
	    s.skeletonization(mTarget);
	    mTargetBox.setIcon(new ImageIcon(mTarget));
	}
	
	public void binarization(AbstractBinarizator binarizator) {
		if (mSource == null) {
			return;
		}
		BinaryResultBundle data = binarizator.binarization(mSource);
		mTarget = data.target;
		mTargetBox.setIcon(new ImageIcon(mTarget));
		repaintSpectrogram(data);
	}

	private void repaintSpectrogram(BinaryResultBundle data) {
		SpectrogramFrameDialog dialog = SpectrogramFrameDialog.getInstance();
		dialog.setVisible(false);
		dialog.setVisible(true);
		dialog.setData(data);
	}

	private void initGui() {
		setLayout(new GridLayout(1, 2, 5, 5));
		mSourceBox = new JLabel();
		mTargetBox = new JLabel();

		mSourceBox.setHorizontalAlignment(JLabel.CENTER);
		// mSourceBox.setVerticalAlignment(JLabel.CENTER);

		mTargetBox.setHorizontalAlignment(JLabel.CENTER);
		// mTargetBox.setVerticalAlignment(JLabel.CENTER);

		add(new JScrollPane(mSourceBox));
		add(new JScrollPane(mTargetBox));
	}
}
