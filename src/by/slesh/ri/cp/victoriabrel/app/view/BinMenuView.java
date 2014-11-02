package by.slesh.ri.cp.victoriabrel.app.view;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import by.slesh.ri.cp.victoriabrel.app.view.service.BinViewInterface;

public class BinMenuView extends JMenu implements BinViewInterface {

    private static final long serialVersionUID = 8099761538265006455L;

    private JMenuItem mBinByStepCheckBoxMenuItem;
    private JMenuItem mBinByPercentCheckBoxMenuItem;
    private JMenuItem mBinByThresholdCheckBoxMenuItem;

    public BinMenuView() {
        super(MENU_BIN);
        mBinByStepCheckBoxMenuItem = new JMenuItem(ACTION_BIN_STEP);
        mBinByPercentCheckBoxMenuItem = new JMenuItem(ACTION_BIN_PERCENT);
        mBinByThresholdCheckBoxMenuItem = new JMenuItem(ACTION_BIN_THRESHOLD);
        addSeparator();

        ButtonGroup group = new ButtonGroup();
        group.add(mBinByStepCheckBoxMenuItem);
        group.add(mBinByPercentCheckBoxMenuItem);
        group.add(mBinByThresholdCheckBoxMenuItem);

        add(mBinByStepCheckBoxMenuItem);
        add(mBinByPercentCheckBoxMenuItem);
        add(mBinByThresholdCheckBoxMenuItem);
        addSeparator();
    }

    @Override
    public void addBinByStepClickListener(ActionListener l) {
        mBinByStepCheckBoxMenuItem.addActionListener(l);
    }

    @Override
    public void addBinByPercentClickListener(ActionListener l) {
        mBinByPercentCheckBoxMenuItem.addActionListener(l);
    }

    @Override
    public void addBinByThresholdClickListener(ActionListener l) {
        mBinByThresholdCheckBoxMenuItem.addActionListener(l);
    }

}