package by.slesh.ri.cp.victoriashpak.app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.slesh.ri.cp.victoriashpak.app.controller.Controller;
import by.slesh.ri.cp.victoriashpak.app.model.Model;
import by.slesh.ri.cp.victoriashpak.util.G;

public class MainView extends JFrame implements MainViewInterface {

    private static final long serialVersionUID = -8285836465968854073L;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;

    private JLabel mSourceImageBox;

    public MainView() {

	super(
	        "Курсовой проект. Шпак Виктория. Распознование фамилии руководителя");
	G.sMainView = this;
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	double x = (size.width - WIDTH) / 2;
	double y = (size.height - HEIGHT) / 2;
	setBounds((int) x, (int) y, WIDTH, HEIGHT);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	initGui();
	setVisible(true);
    }

    private void initGui() {

	ImageBoxesView imageBoxesView = new ImageBoxesView();
	ControlPanelView controlPanelView = new ControlPanelView();

	Model model = new Model();
	new Controller(this, controlPanelView, imageBoxesView, model);

	JPanel panel = new JPanel(new BorderLayout(5, 5));
	panel.setPreferredSize(new Dimension(500, 1));
	panel.add(controlPanelView, BorderLayout.PAGE_START);
	panel.add(imageBoxesView, BorderLayout.PAGE_END);
	
	getContentPane().add(panel, BorderLayout.LINE_END);
	
	mSourceImageBox = new JLabel();
	mSourceImageBox.setHorizontalAlignment(JLabel.CENTER);
	JScrollPane sourceScrollPane = new JScrollPane(mSourceImageBox);
	getContentPane().add(sourceScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void updateSource(BufferedImage source) {
	mSourceImageBox.setIcon(new ImageIcon(source));
    }

    @Override
    public void refresh() {
	repaint();
    }
}
