package by.slesh.ri.cp.victoriabrel.app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import by.slesh.ri.cp.victoriabrel.app.view.service.ImageBoxesViewInterface;

public class ImageBoxesView extends JPanel implements ImageBoxesViewInterface {

	private static final long	serialVersionUID	= -3893100366850568189L;
	private JLabel				mSourceImageBox;
	private JLabel				mTargetImageBox;
	private JLabel				mRegionImageBox;

	public ImageBoxesView() {
		setLayout(new GridLayout(1, 2, 5, 5));
		mSourceImageBox = new JLabel();
		mTargetImageBox = new JLabel();
		mRegionImageBox = new JLabel();
		
		mSourceImageBox.setHorizontalAlignment(JLabel.CENTER);
		mTargetImageBox.setHorizontalAlignment(JLabel.CENTER);
		mRegionImageBox.setHorizontalAlignment(JLabel.CENTER);
		mRegionImageBox.setPreferredSize(new Dimension(200, 50));
		
		JScrollPane sourceScrollPane = new JScrollPane(mSourceImageBox);
		JScrollPane targetScrollPane = new JScrollPane(mTargetImageBox);

		targetScrollPane.getViewport().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int x = targetScrollPane.getHorizontalScrollBar().getValue();
				int y = targetScrollPane.getVerticalScrollBar().getValue();
				sourceScrollPane.getHorizontalScrollBar().setValue(x);
				sourceScrollPane.getVerticalScrollBar().setValue(y);
			}
		});

		sourceScrollPane.getViewport().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int x = sourceScrollPane.getHorizontalScrollBar().getValue();
				int y = sourceScrollPane.getVerticalScrollBar().getValue();
				targetScrollPane.getHorizontalScrollBar().setValue(x);
				targetScrollPane.getVerticalScrollBar().setValue(y);
			}
		});

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(targetScrollPane);
		panel.add(mRegionImageBox, BorderLayout.PAGE_END);
		
		add(sourceScrollPane);
		add(panel);
	}

	@Override
	public void getLocationOnImage(Point currentLocation) {
		int h = mTargetImageBox.getHeight();
		int w = mTargetImageBox.getWidth();
		int ih = mTargetImageBox.getIcon().getIconHeight();
		int iw = mTargetImageBox.getIcon().getIconWidth();

		int dx = (iw - w) / 2;
		int dy = (ih - h) / 2;

		currentLocation.translate(dx, dy);
	}

	@Override
	public void addTargetImageBoxClickListener(MouseListener l) {
		mTargetImageBox.addMouseListener(l);
	}

	@Override
	public void updateSource(BufferedImage source) {
		if (source == null) mSourceImageBox.setIcon(null);
		else mSourceImageBox.setIcon(new ImageIcon(source));
	}

	@Override
	public void updateTarget(BufferedImage target) {
		if (target == null) mTargetImageBox.setIcon(null);
		else mTargetImageBox.setIcon(new ImageIcon(target));
	}

	@Override
	public void updateRegion(BufferedImage region) {
		if (region == null) mRegionImageBox.setIcon(null);
		else mRegionImageBox.setIcon(new ImageIcon(region));
	}
}
