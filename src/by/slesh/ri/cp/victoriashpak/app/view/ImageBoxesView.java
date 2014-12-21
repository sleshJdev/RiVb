package by.slesh.ri.cp.victoriashpak.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageBoxesView extends JPanel implements ImageBoxesViewInterface {
    private static final long serialVersionUID = -3893100366850568189L;
    private JLabel mAreaInterestImageBox;
    private JPanel mFullNameSegmentedPanel;
    private JPanel mUnrecognizedPanel;
    private JPanel mRecognizedPanel;

    public ImageBoxesView() {

	setLayout(new BorderLayout());
	
	mAreaInterestImageBox = new JLabel();
	
	mAreaInterestImageBox.setHorizontalAlignment(JLabel.CENTER);

	mFullNameSegmentedPanel = new JPanel(new GridLayout(1, 1));
	mFullNameSegmentedPanel.setPreferredSize(new Dimension(1, 100));
	mFullNameSegmentedPanel.setBorder(BorderFactory.createLineBorder(
	        Color.RED, 1));

	mUnrecognizedPanel = new JPanel();
	mUnrecognizedPanel.setPreferredSize(new Dimension(1, 100));
	mUnrecognizedPanel.setBorder(BorderFactory.createLineBorder(
	        Color.GREEN, 1));

	mRecognizedPanel = new JPanel();
	mRecognizedPanel.setBorder(BorderFactory
	        .createLineBorder(Color.BLUE, 1));

	JPanel panel11 = new JPanel(new GridLayout(3, 1, 5, 5));
	panel11.add(new JScrollPane(mAreaInterestImageBox));
	panel11.add(new JScrollPane(mFullNameSegmentedPanel));
//	panel11.add(new JScrollPane(mUnrecognizedPanel));
	panel11.add(new JScrollPane(mRecognizedPanel));

	JPanel panel13 = new JPanel(new BorderLayout());
	panel13.add(panel11, BorderLayout.PAGE_END);

	add(panel13);
    }

    @Override
    public void updateAreaInterest(BufferedImage area) {
	mAreaInterestImageBox.setIcon(new ImageIcon(area));

    }

    @Override
    public void updateSegmentFullName(BufferedImage[] symbols) {
	mFullNameSegmentedPanel.setLayout(new GridLayout(1, symbols.length));
	mFullNameSegmentedPanel.removeAll();
	mFullNameSegmentedPanel.setPreferredSize(new Dimension(100*symbols.length, 100));
	for (BufferedImage digit : symbols) {
	    JLabel l = new JLabel(new ImageIcon(digit));
	    l.setPreferredSize(new Dimension(50, 100));
	    l.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
	    mFullNameSegmentedPanel.add(l);
	    mFullNameSegmentedPanel.revalidate();
	}
    }

    @Override
    public void updateUnrecognizeFullName(BufferedImage[] symbols) {
	mUnrecognizedPanel.setLayout(new GridLayout(1, symbols.length));
	mUnrecognizedPanel.removeAll();
	mUnrecognizedPanel.setPreferredSize(new Dimension(100*symbols.length, 100));
	for (BufferedImage digit : symbols) {
	    JLabel l = new JLabel(new ImageIcon(digit));
	    l.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
	    mUnrecognizedPanel.add(l);
	    mUnrecognizedPanel.revalidate();
	}
    }

    @Override
    public void updateRecognize(BufferedImage[] symbols) {
	mRecognizedPanel.setLayout(new GridLayout(1, symbols.length));
	mRecognizedPanel.removeAll();
	for (BufferedImage digit : symbols) {
	    JLabel l = new JLabel(new ImageIcon(digit));
	    l.setPreferredSize(new Dimension(50, 100));
	    mRecognizedPanel.add(l);
	    mRecognizedPanel.revalidate();
	}
    }
}
