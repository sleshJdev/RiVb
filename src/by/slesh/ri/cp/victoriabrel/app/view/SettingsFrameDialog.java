package by.slesh.ri.cp.victoriabrel.app.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoundedRangeModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import by.slesh.ri.cp.victoriabrel.services.Command;
import by.slesh.ri.cp.victoriabrel.util.HistogramSegmentator;
import by.slesh.ri.cp.victoriabrel.util.PercentsBinarizator;
import by.slesh.ri.cp.victoriabrel.util.StepBinaryzator;
import by.slesh.ri.cp.victoriabrel.util.ThresholdBinarizator;

public class SettingsFrameDialog extends JFrame {

    private final class BoundedChangeListener implements ChangeListener {

        Command mCommand;

        private BoundedChangeListener(Command command) {
            mCommand = command;
        }

        public void stateChanged(ChangeEvent changeEvent) {
            Object source = changeEvent.getSource();
            if (source instanceof BoundedRangeModel) {
                BoundedRangeModel model = (BoundedRangeModel) source;
                if (!model.getValueIsAdjusting()) {
                    int value = model.getValue();
                    mCommand.execute(value);
                }
            } else {
                System.out.println("Something changed: " + source);
            }
        }
    }

    private static final long serialVersionUID = 5165862660719450891L;
    private final int WIDTH = 500;
    private final int HEIGHT = 200;
    private static SettingsFrameDialog mInstance;

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

    private void initGui() {
        setBounds(0, 0, WIDTH, HEIGHT);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        JScrollBar scrollBar = null;

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Шаг"));
        JTextField tf1 = new JTextField(3);
        tf1.setText(Integer.toString(StepBinaryzator.sOffset));
        tf1.setEditable(false);
        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, StepBinaryzator.sOffset, 1, 0, 150);
        scrollBar.setPreferredSize(new Dimension(150, 20));
        scrollBar.getModel().addChangeListener(new BoundedChangeListener(new Command() {

            @Override
            public void execute(int value) {
                tf1.setText(Integer.toString(value));
                StepBinaryzator.sOffset = value;
            }
        }));
        panel1.add(scrollBar);
        panel1.add(tf1);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Процент"));
        JTextField tf2 = new JTextField(3);
        tf2.setText(Integer.toString(PercentsBinarizator.sPercents));
        tf2.setEditable(false);
        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, PercentsBinarizator.sPercents, 1, 0, 100);
        scrollBar.setPreferredSize(new Dimension(150, 20));
        scrollBar.getModel().addChangeListener(new BoundedChangeListener(new Command() {

            @Override
            public void execute(int value) {
                tf2.setText(Integer.toString(value));
                PercentsBinarizator.sPercents = value;
            }
        }));
        panel2.add(scrollBar);
        panel2.add(tf2);

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Порог"));
        JTextField tf3 = new JTextField(3);
        tf3.setText(Integer.toString(ThresholdBinarizator.sThreshold));
        tf3.setEditable(false);
        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, ThresholdBinarizator.sThreshold, 1, 0, 256);
        scrollBar.setPreferredSize(new Dimension(150, 20));
        scrollBar.getModel().addChangeListener(new BoundedChangeListener(new Command() {

            @Override
            public void execute(int value) {
                tf3.setText(Integer.toString(value));
                ThresholdBinarizator.sThreshold = value;
            }
        }));
        panel3.add(scrollBar);
        panel3.add(tf3);

//        JPanel panel4 = new JPanel();
//        panel4.add(new JLabel("Порог для сегментации"));
//        JTextField tf4 = new JTextField(3);
//        tf4.setText(Integer.toString(HistogramSegmentator.sThreshold));
//        tf4.setEditable(false);
//        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, HistogramSegmentator.sThreshold, 1, 0, 500);
//        scrollBar.setPreferredSize(new Dimension(150, 20));
//        scrollBar.getModel().addChangeListener(new BoundedChangeListener(new Command() {
//
//            @Override
//            public void execute(int value) {
//                tf4.setText(Integer.toString(value));
//                HistogramSegmentator.sThreshold = value;
//            }
//        }));
//        panel4.add(scrollBar);
//        panel4.add(tf4);

        setLayout(new GridLayout(4, 1));

        add(panel1);
        add(panel2);
        add(panel3);
//        add(panel4);

        pack();
    }
}
