package by.slesh.ri.cp.victoriashpak.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import by.slesh.ri.cp.victoriashpak.util.G;

public class ControlPanelView extends JPanel implements ControlViewInterface {

    private static final long serialVersionUID = -2454572918534173516L;

    private JScrollBar mPercentScrollBar;
    private JScrollBar mThresholdScrollBar;

    private JTextField mPercentValueTextField;
    private JTextField mThresholdValueTextField;

    private JButton mPercentBinarizationButton;
    private JButton mThresholdBinarizationButton;
    private JButton mHistogramSegmentButton;
    private JButton mOpenFileButton;
    private JButton mTrimButton;
    private JButton mExtractAreaInterestButton;
    private JButton mFindPLetterButton;
    private JButton mExtractFullNameButton;
    private JButton mSegmentFullNameButton;
    private JButton mRecognizeFullNameButton;

    public ControlPanelView() {
	setBorder(BorderFactory.createTitledBorder("œ¿Õ≈À‹ ”œ–¿¬À≈Õ»ﬂ"));

	mOpenFileButton = new JButton("1 >> Œ“ –€“‹ ¡À¿Õ ");
	mOpenFileButton.setBackground(new Color(20, 30, 20));
	mOpenFileButton.setForeground(Color.WHITE);
	mOpenFileButton.setActionCommand(ACTION_FILE_OPEN);

	mPercentBinarizationButton = createButton("2 >> œŒ œ–Œ÷≈Õ“”(1..100)",
	        ACTION_BIN_PERCENT, false);
	mPercentBinarizationButton.setBackground(new Color(40, 60, 150));
	mPercentBinarizationButton.setForeground(Color.WHITE);
	mPercentValueTextField = new JTextField(
	        Integer.toString(G.INIT_BIN_PERCENT), 4);
	mPercentValueTextField.setHorizontalAlignment(SwingConstants.CENTER);
	mPercentScrollBar = new JScrollBar(JScrollBar.HORIZONTAL,
	        G.INIT_BIN_PERCENT, 1, 0, 100);
	mPercentScrollBar.setEnabled(false);

	mThresholdBinarizationButton = createButton("2 >> œŒ œŒ–Œ√”(1..255)",
	        ACTION_BIN_THRESHOLD, false);
	mThresholdBinarizationButton.setBackground(new Color(150, 60, 40));
	mThresholdBinarizationButton.setForeground(Color.WHITE);
	mThresholdValueTextField = new JTextField(
	        Integer.toString(G.INIT_BIN_THRESHOLD), 4);
	mThresholdValueTextField.setHorizontalAlignment(SwingConstants.CENTER);
	mThresholdScrollBar = new JScrollBar(JScrollBar.HORIZONTAL,
	        G.INIT_BIN_THRESHOLD, 1, 0, 255);
	mThresholdScrollBar.setEnabled(false);

	/* Segment controls */
	mHistogramSegmentButton = createButton("3 >> Œ√–¿Õ»◊»“‹",
	        ACTION_SEGMENT_HISTOGRAM, false);
	mHistogramSegmentButton.setBackground(new Color(60, 90, 60));
	mHistogramSegmentButton.setForeground(Color.WHITE);

	mTrimButton = createButton("4 >> Œ¡–≈«¿“‹", ACTION_TRIM, false);
	mTrimButton.setBackground(new Color(80, 120, 80));
	mTrimButton.setForeground(Color.WHITE);

	mExtractAreaInterestButton = createButton(
	        "5 >> ¬€ƒ≈À»“‹ Œ¡À¿—“‹ — \"‘»Œ\"",
	        ACTION_EXTRACT_AREA_INTEREST, false);
	mExtractAreaInterestButton.setBackground(new Color(100, 150, 100));
	mExtractAreaInterestButton.setForeground(Color.BLACK);

	mFindPLetterButton = createButton("6 >> Õ¿…“» ¡” ¬” \"–\"",
	        ACTION_FIND_SYMBOL, false);
	mFindPLetterButton.setBackground(new Color(120, 180, 120));
	mFindPLetterButton.setForeground(Color.BLACK);

	mExtractFullNameButton = createButton("7 >> »«¬À≈◊‹ \"‘»Œ\"",
	        ACTION_EXTRACT_FULLNAME, false);
	mExtractFullNameButton.setBackground(new Color(140, 210, 140));
	mExtractFullNameButton.setForeground(Color.BLACK);

	mSegmentFullNameButton = createButton("8 >> —≈√Ã≈Õ“»–Œ¬¿“‹ \"‘»Œ\"",
	        ACTION_SEGMENT_FULLNAME, false);
	mSegmentFullNameButton.setBackground(new Color(160, 240, 160));
	mSegmentFullNameButton.setForeground(Color.BLACK);

	mRecognizeFullNameButton = createButton("9 >> –¿—œŒ«Õ¿“‹",
	        ACTION_RECOGNIZE, false);
	mRecognizeFullNameButton.setBackground(new Color(240, 180, 240));
	mRecognizeFullNameButton.setForeground(Color.BLACK);

	BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	setLayout(bl);

	JPanel panel = null;
	JPanel tempPanel = null;
	JPanel panelTitle = null;

	panelTitle = new JPanel(new GridLayout(1, 2, 5, 5));
	panelTitle.setBorder(BorderFactory.createTitledBorder("¡»Õ¿–»«¿÷»ﬂ"));
	panel = new JPanel(new GridLayout(2, 1, 5, 5));
	panel.add(mPercentBinarizationButton);
	tempPanel = new JPanel(new BorderLayout(5, 5));
	tempPanel.add(mPercentScrollBar, BorderLayout.CENTER);
	tempPanel.add(mPercentValueTextField, BorderLayout.LINE_END);
	panel.add(tempPanel);
	panelTitle.add(panel);
	panel = new JPanel(new GridLayout(2, 1, 5, 5));
	panel.add(mThresholdBinarizationButton);
	tempPanel = new JPanel(new BorderLayout(5, 5));
	tempPanel.add(mThresholdScrollBar, BorderLayout.CENTER);
	tempPanel.add(mThresholdValueTextField, BorderLayout.LINE_END);
	panel.add(tempPanel);
	panelTitle.add(panel);

	panel = new JPanel(new GridLayout(5, 1, 5, 5));
	bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
	panel.setLayout(bl);
	tempPanel = new JPanel(new GridLayout(1, 2, 5, 5));
	tempPanel.add(mOpenFileButton);
	panel.add(tempPanel);
	panel.add(new JPanel());
	panel.add(panelTitle);
	panel.add(new JPanel());
	tempPanel = new JPanel(new GridLayout(1, 2, 5, 5));
	tempPanel.add(mHistogramSegmentButton);
	tempPanel.add(mTrimButton);
	panel.add(tempPanel);
	add(panel);

	panel = new JPanel(new GridLayout(2, 2, 5, 5));
	panel.add(mFindPLetterButton);
	panel.add(mExtractAreaInterestButton);
	panel.add(mExtractFullNameButton);
	panel.add(mSegmentFullNameButton);
	add(panel);

	panel = new JPanel(new GridLayout(1, 1, 5, 5));
	panel.add(mRecognizeFullNameButton);
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
    public void addBinPercentClickListener(ActionListener l) {
	mPercentBinarizationButton.addActionListener(l);
    }

    @Override
    public void addBinThresholdClickListener(ActionListener l) {
	mThresholdBinarizationButton.addActionListener(l);
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
	mFindPLetterButton.addActionListener(l);
    }

    @Override
    public void addExtractFullNameClickListener(ActionListener l) {
	mExtractFullNameButton.addActionListener(l);
    }

    @Override
    public void addSegmentFullNameClickListener(ActionListener l) {
	mSegmentFullNameButton.addActionListener(l);
    }

    @Override
    public void addRecognizeNumberClickListener(ActionListener l) {
	mRecognizeFullNameButton.addActionListener(l);
    }

    @Override
    public void addPercentScrollListener(AdjustmentListener l) {
	mPercentScrollBar.addAdjustmentListener(l);
    }

    @Override
    public void addThresholdScrollListener(AdjustmentListener l) {
	mThresholdScrollBar.addAdjustmentListener(l);
    }

    @Override
    public void updatePercentValue(int newValue) {
	mPercentValueTextField.setText(Integer.toString(newValue));
    }

    @Override
    public void updateThresholdValue(int newValue) {
	mThresholdValueTextField.setText(Integer.toString(newValue));
    }
}
