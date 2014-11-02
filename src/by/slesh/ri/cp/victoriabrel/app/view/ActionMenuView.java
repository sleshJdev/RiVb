package by.slesh.ri.cp.victoriabrel.app.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import by.slesh.ri.cp.victoriabrel.app.view.service.ActionViewInterface;

public class ActionMenuView extends JMenu implements ActionViewInterface {

    private static final long serialVersionUID = -1085647623449065245L;

    private JMenuItem mSleletizationMenuItem;
    private JMenuItem mBugSegmentMenuItem;
    private JMenuItem mHistogramSegmentMenuItem;

    public ActionMenuView() {
        super(MENU_ACTION);
        mSleletizationMenuItem = new JMenuItem(ACTION_SKELETON);
        mSleletizationMenuItem.setActionCommand(ACTION_SKELETON);

        mBugSegmentMenuItem = new JMenuItem(ACTION_SEGMENT_BUG);
        mBugSegmentMenuItem.setActionCommand(ACTION_SEGMENT_BUG);

        mHistogramSegmentMenuItem = new JMenuItem(ACTION_SEGMENT_HISTOGRAM);
        mHistogramSegmentMenuItem.setActionCommand(ACTION_SEGMENT_HISTOGRAM);

        add(mSleletizationMenuItem);
        add(mBugSegmentMenuItem);
        add(mHistogramSegmentMenuItem);
    }

    @Override
    public void addSkeletizationClickListener(ActionListener l) {
        mSleletizationMenuItem.addActionListener(l);
    }

    @Override
    public void addHistogramSegmentClickListener(ActionListener l) {
        mHistogramSegmentMenuItem.addActionListener(l);
    }

    @Override
    public void addBugSegmentClickListener(ActionListener l) {
        mBugSegmentMenuItem.addActionListener(l);
    }

}
