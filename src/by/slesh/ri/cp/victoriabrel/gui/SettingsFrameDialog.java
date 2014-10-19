package by.slesh.ri.cp.victoriabrel.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import by.slesh.ri.cp.victoriabrel.binarizators.ThresholdChangeValueListener;

public class SettingsFrameDialog extends JFrame {
	private final class BoundedChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent changeEvent) {
			Object source = changeEvent.getSource();
			if (source instanceof BoundedRangeModel) {
				BoundedRangeModel model = (BoundedRangeModel) source;
				if (!model.getValueIsAdjusting()) {
					int value = model.getValue();
					mListener.setNewThresholdValue(value);
					mThreshold.setText(Integer.toString(value));
				}
			} else {
				System.out.println("Something changed: " + source);
			}
		}
	}

	private static final long                   serialVersionUID = 5165862660719450891L;
	private final int                           WIDTH            = 300;
	private final int                           HEIGHT           = 100;
	private static SettingsFrameDialog          mInstance;
	private static ThresholdChangeValueListener mListener;
	private JTextField                          mThreshold;

	private SettingsFrameDialog() {
		super("Настройки");
		initGui();
	}

	public static SettingsFrameDialog getInstance() {
		if (mInstance == null) {
			mInstance = new SettingsFrameDialog();
		}
		return mInstance;
	}

	public static void setThresholdChangeValueListener(ThresholdChangeValueListener listener) {
		mListener = listener;
	}

	private void initGui() {
		setBounds(0, 0, WIDTH, HEIGHT);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar.setMinimum(0);
		scrollBar.setMaximum(255);
		scrollBar.getModel().addChangeListener(new BoundedChangeListener());

		mThreshold = new JTextField(3);
		mThreshold.setEditable(false);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// panel.setBorder(BorderFactory.createLineBorder(Color.red));
		panel.add(new JLabel("Порог"));
		panel.add(scrollBar);
		panel.add(mThreshold);
		add(panel);
	}
}
