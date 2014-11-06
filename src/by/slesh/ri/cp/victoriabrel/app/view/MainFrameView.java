package by.slesh.ri.cp.victoriabrel.app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import by.slesh.ri.cp.victoriabrel.app.view.service.ActionViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.BinViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.FileViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.ImageBoxesViewInterface;
import by.slesh.ri.cp.victoriabrel.app.view.service.MainViewInterface;

public class MainFrameView extends JFrame implements MainViewInterface {

    private static final long serialVersionUID = -5050395057549630032L;
    private static final int HEIGHT = 600;
    private static final int WIDTH = 1000;

    public MainFrameView() {
//        super("|Курсовой проект|Распознование имени преподователя|Виктория Брель|");
        super("Привет Викуля!!!");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - WIDTH) / 2);
        int y = (int) ((screenSize.getHeight() - HEIGHT) / 2);
        setBounds(x, y, WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initGui();
        setVisible(true);

    }

    private BinMenuView mBinMenu;
    private FileMenuView mFileMenu;
    private ActionMenuView mActionMenu;
    private ImageBoxesView mImageBoxes;

    private void initGui() {
        try {
            setIconImage(ImageIO.read(new File("resources/ic_app.png")));
        } catch (IOException ioe) {
            showMessage(ioe.getMessage());
        }
        mImageBoxes = new ImageBoxesView();
        add(mImageBoxes, BorderLayout.CENTER);

        mFileMenu = new FileMenuView();
        mActionMenu = new ActionMenuView();
        mBinMenu = new BinMenuView();

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mFileMenu);
        menuBar.add(mActionMenu);
        menuBar.add(mBinMenu);
        setJMenuBar(menuBar);
    }

    @Override
    public ActionViewInterface getActionViewInterfaceImpl() {
        return mActionMenu;
    }

    @Override
    public BinViewInterface getBinViewInterfaceImpl() {
        return mBinMenu;
    }

    @Override
    public FileViewInterface getFileViewInterfaceImpl() {
        return mFileMenu;
    }

    @Override
    public ImageBoxesViewInterface getImageBoxesViewInterfaceImpl() {
        return mImageBoxes;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void showBinSettings() {
        SettingsFrameDialog panel = SettingsFrameDialog.getInstance();
        if (!panel.isVisible()) {
            panel.setVisible(true);
        }

    }
}
