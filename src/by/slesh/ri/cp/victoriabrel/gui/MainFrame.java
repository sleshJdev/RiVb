package by.slesh.ri.cp.victoriabrel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import by.slesh.ri.cp.victoriabrel.binarizators.Method120Binaryzator;
import by.slesh.ri.cp.victoriabrel.binarizators.Method40PercentsBinarizator;
import by.slesh.ri.cp.victoriabrel.binarizators.ThresholdBinarizator;

public class MainFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = -5050395057549630032L;
    private static final int HEIGHT = 600;
    private static final int WIDTH = 1000;

    private static final String MENU_ITEM_OPEN = "Œ“ –€“‹";
    private static final String MENU_FILE = "‘¿…À";

    private static final String MENU_PROPERTIES = "ƒ≈…—“¬»ﬂ";
    private static final String MENU_ITEM_SKELETON = "— ≈À≈“ŒÕ»«¿÷»ﬂ";

    private static final String MENU_BIN = "¡»Õ¿–»«¿÷»ﬂ";
    private static final String MENU_ITEM_BINARIZATION_120 = "Ã≈“Œƒ 120";
    private static final String MENU_ITEM_BINARIZATION_40 = "Ã≈“Œƒ 40%";
    private static final String MENU_ITEM_BINARIZATION_THRESHOLD = "œŒ œŒ–Œ√”";
    private static final String MENU_ITEM_SETTINGS = "Õ¿…—“–Œ… »";

    private SurfacePanel mSurfacePanel;

    private Method120Binaryzator mMethod120Binaryzator = new Method120Binaryzator();
    private Method40PercentsBinarizator mMethod40PercentsBinarizator = new Method40PercentsBinarizator();
    private ThresholdBinarizator mThresholdBinarizator = new ThresholdBinarizator();

    public MainFrame() {
	super("| ÛÒÓ‚ÓÈ ÔÓÂÍÚ|–‡ÒÔÓÁÌÓ‚‡ÌËÂ ËÏÂÌË ÔÂÔÓ‰Ó‚‡ÚÂÎˇ|¬ËÍÚÓËˇ ¡ÂÎ¸|");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int x = (int) ((screenSize.getWidth() - WIDTH) / 2);
	int y = (int) ((screenSize.getHeight() - HEIGHT) / 2);
	setBounds(x, y, WIDTH, HEIGHT);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	initGui();

    }

    public static void main(String[] args) {
	MainFrame mainFrame = new MainFrame();
	mainFrame.setVisible(true);
    }

    private void initGui() {
	try {
	    setIconImage(ImageIO.read(new File("resources/ic_app.png")));
	} catch (IOException ioe) {
	    showMessage(ioe.getMessage());
	}
	mSurfacePanel = new SurfacePanel();
	add(mSurfacePanel, BorderLayout.CENTER);

	JMenuBar menuBar = new JMenuBar();

	JMenu menu = new JMenu(MENU_FILE);
	JMenuItem menuItem = new JMenuItem(MENU_ITEM_OPEN, new ImageIcon("resources/ic_open.png"));
	menuItem.addActionListener(this);
	menu.add(menuItem);
	menuBar.add(menu);

	menu = new JMenu(MENU_PROPERTIES);
	menuItem = new JMenuItem(MENU_ITEM_SKELETON);
	menuItem.addActionListener(this);
	menu.add(menuItem);
	menuBar.add(menu);

	menu = new JMenu(MENU_BIN);
	menuItem = new JMenuItem(MENU_ITEM_BINARIZATION_120);
	menuItem.addActionListener(this);
	menu.add(menuItem);
	menuItem = new JMenuItem(MENU_ITEM_BINARIZATION_40);
	menuItem.addActionListener(this);
	menu.add(menuItem);
	menuItem = new JMenuItem(MENU_ITEM_BINARIZATION_THRESHOLD);
	menuItem.addActionListener(this);
	menu.add(menuItem);
	menu.addSeparator();
	menuItem = new JMenuItem(MENU_ITEM_SETTINGS, new ImageIcon("resources/ic_properties.png"));
	menuItem.addActionListener(this);
	menu.add(menuItem);
	menuBar.add(menu);

	setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	switch (e.getActionCommand()) {
	case MENU_ITEM_OPEN:
	    open();
	    break;
	case MENU_ITEM_SETTINGS:
	    adjust();
	    break;
	case MENU_ITEM_BINARIZATION_120:
	    mSurfacePanel.binarization(mMethod120Binaryzator);
	    break;
	case MENU_ITEM_BINARIZATION_40:
	    mSurfacePanel.binarization(mMethod40PercentsBinarizator);
	    break;
	case MENU_ITEM_BINARIZATION_THRESHOLD:
	    mSurfacePanel.binarization(mThresholdBinarizator);
	    break;
	case MENU_ITEM_SKELETON:
	    mSurfacePanel.skeletonize();
	    break;
	}
    }

    private void open() {
	JFileChooser fc = new JFileChooser();
	int returnValue = fc.showDialog(this, MENU_ITEM_OPEN);
	if (returnValue == JFileChooser.APPROVE_OPTION) {
	    File file = fc.getSelectedFile();
	    BufferedImage image = null;
	    try {
		image = ImageIO.read(file);
		if (image != null) {
		    mSurfacePanel.setImage(image);
		}
	    } catch (IOException ioe) {
		showMessage(ioe.getMessage());
	    }
	}
    }

    private void adjust() {
	SettingsFrameDialog panel = SettingsFrameDialog.getInstance();
	if (!panel.isVisible()) {
	    panel.setVisible(true);
	}
    }

    private void showMessage(String message) {
	JOptionPane.showMessageDialog(this, message);
    }
}
