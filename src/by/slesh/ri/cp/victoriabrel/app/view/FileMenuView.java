package by.slesh.ri.cp.victoriabrel.app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import by.slesh.ri.cp.victoriabrel.app.view.service.FileViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.OpenListener;

public class FileMenuView extends JMenu implements FileViewInterface {

    private static final long serialVersionUID = 5575845158420512559L;

    private class ClickAdapte implements ActionListener {

        private final JFileChooser FILET_CHOOSER = new JFileChooser();

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (FILET_CHOOSER.showOpenDialog(FileMenuView.this) == JFileChooser.APPROVE_OPTION) {
                if (mOpenListener != null) {
                    mOpenListener.openImage(FILET_CHOOSER.getSelectedFile());
                }
            }
        }
    }

    private JMenuItem mOpenMenuItem;
    private JMenuItem mSettingsMenuItem;
    private OpenListener mOpenListener;

    public FileMenuView() {
        super(MENU_FILE);
        mOpenMenuItem = new JMenuItem(MENU_ITEM_OPEN, new ImageIcon("resources/ic_open.png"));
        mOpenMenuItem.addActionListener(new ClickAdapte());
        mSettingsMenuItem = new JMenuItem(ACTION_SETTINGS, new ImageIcon("resources/ic_properties.png"));
        add(mOpenMenuItem);
        //add(mSettingsMenuItem);
    }

    @Override
    public void addOpenClickListener(OpenListener l) {
        mOpenListener = l;
    }

    @Override
    public void addSettingClickListener(ActionListener l) {
        mSettingsMenuItem.addActionListener(l);
    }
}
