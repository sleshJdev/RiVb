package by.slesh.ri.cp.victoriashpak.app.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanelView extends JPanel implements ControlViewInterface {

    private static final long serialVersionUID = -2454572918534173516L;

    private JButton mBinarizationButton;
    private JButton mHistogramSegmentButton;
    private JButton mOpenFileButton;
    private JButton mTrimButton;
    private JButton mExtractAreaInterestButton;
    private JButton mNeuralNetworkUseButton;
    private JButton mExtractGroupNumberButton;
    private JButton mSegmentGroupNumberButton;
    private JButton mRecognizeNumberButton;

    public ControlPanelView() {
	setBorder(BorderFactory.createTitledBorder("œ¿Õ≈À‹ ”œ–¿¬À≈Õ»ﬂ"));

	mOpenFileButton = new JButton("1 >> Œ“ –€“‹ ¡À¿Õ ");
	mOpenFileButton.setBackground(new Color(20, 30, 20));
	mOpenFileButton.setForeground(Color.WHITE);
	mOpenFileButton.setActionCommand(ACTION_FILE_OPEN);

	mBinarizationButton = createButton("2 >> ¡»Õ¿–»«Œ¬¿“‹", ACTION_BINARIZATION,
	        false);
	mBinarizationButton.setBackground(new Color(40, 60, 40));
	mBinarizationButton.setForeground(Color.WHITE);

	/* Segment controls */
	mHistogramSegmentButton = createButton("3 >> Œ√–¿Õ»◊»“‹",
	        ACTION_SEGMENT_HISTOGRAM, false);
	mHistogramSegmentButton.setBackground(new Color(60, 90, 60));
	mHistogramSegmentButton.setForeground(Color.WHITE);

	mTrimButton = createButton("4 >> Œ¡–≈«¿“‹", ACTION_TRIM, false);
	mTrimButton.setBackground(new Color(80, 120, 80));
	mTrimButton.setForeground(Color.WHITE);

	mExtractAreaInterestButton = createButton("5 >> ¬€ƒ≈À»“‹ Œ¡À¿—“‹ — \"‘»Œ\"",
	        ACTION_EXTRACT_AREA_INTEREST, false);
	mExtractAreaInterestButton.setBackground(new Color(100, 150, 100));
	mExtractAreaInterestButton.setForeground(Color.BLACK);

	mNeuralNetworkUseButton = createButton("6 >> Õ¿…“» ¡” ¬” \"–\"",
	        ACTION_FIND_SYMBOL, false);
	mNeuralNetworkUseButton.setBackground(new Color(120, 180, 120));
	mNeuralNetworkUseButton.setForeground(Color.BLACK);

	mExtractGroupNumberButton = createButton("7 >> »«¬À≈◊‹ \"‘»Œ\"",
	        ACTION_EXTRACT_FULLNAME, false);
	mExtractGroupNumberButton.setBackground(new Color(140, 210, 140));
	mExtractGroupNumberButton.setForeground(Color.BLACK);

	mSegmentGroupNumberButton = createButton("8 >> —≈√Ã≈Õ“»–Œ¬¿“‹ \"‘»Œ\"",
	        ACTION_SEGMENT_FULLNAME, false);
	mSegmentGroupNumberButton.setBackground(new Color(160, 240, 160));
	mSegmentGroupNumberButton.setForeground(Color.BLACK);

	mRecognizeNumberButton = createButton("9 >> –¿—œŒ«Õ¿“‹", ACTION_RECOGNIZE,
	        false);
	mRecognizeNumberButton.setBackground(new Color(240, 180, 240));
	mRecognizeNumberButton.setForeground(Color.BLACK);

	BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	setLayout(bl);

	JPanel panel = null;

	panel = new JPanel(new GridLayout(4, 1, 5, 5));
	panel.add(mOpenFileButton);
	panel.add(mBinarizationButton);
	panel.add(mHistogramSegmentButton);
	panel.add(mTrimButton);
	add(panel);

	panel = new JPanel(new GridLayout(2, 2, 5, 5));
	panel.add(mNeuralNetworkUseButton);
	panel.add(mExtractAreaInterestButton);
	panel.add(mExtractGroupNumberButton);
	panel.add(mSegmentGroupNumberButton);
	add(panel);

	panel = new JPanel(new GridLayout(1, 1, 5, 5));
	panel.add(mRecognizeNumberButton);
	add(panel);

	enableComponents(this, true);
    }

    private JButton createButton(String caption, String actionCommand,
	    boolean isEnable) {

	JButton button = new JButton(caption);
	button.setActionCommand(actionCommand);
	button.setEnabled(isEnable);
	return button;
    }

    public void enableComponents(Container container, boolean enable) {

	Component[] components = container.getComponents();
	for (Component component : components) {
	    component.setEnabled(enable);
	    if (component instanceof Container) {
		enableComponents((Container) component, enable);
	    }
	}
    }

    @Override
    public void enableControls() {
	enableComponents(this, true);
    }

    @Override
    public void addOpenFileClickListener(ActionListener l) {
	mOpenFileButton.addActionListener(l);
    }

    @Override
    public void addBinarizateClickListener(ActionListener l) {
	mBinarizationButton.addActionListener(l);
    }

    @Override
    public void addHistogramSegmentClickListener(ActionListener l) {
	mHistogramSegmentButton.addActionListener(l);
    }

    @Override
    public void addExtractAreaInterestClickListener(ActionListener l) {
	mExtractAreaInterestButton.addActionListener(l);
    }

    @Override
    public void addTrimClickListener(ActionListener l) {
	mTrimButton.addActionListener(l);
    }

    @Override
    public void addFindSymbolClickListener(ActionListener l) {
	mNeuralNetworkUseButton.addActionListener(l);
    }

    @Override
    public void addExtractFullNameClickListener(ActionListener l) {
	mExtractGroupNumberButton.addActionListener(l);
    }

    @Override
    public void addSegmentFullNameClickListener(ActionListener l) {
	mSegmentGroupNumberButton.addActionListener(l);
    }

    @Override
    public void addRecognizeNumberClickListener(ActionListener l) {
	mRecognizeNumberButton.addActionListener(l);
    }
}
