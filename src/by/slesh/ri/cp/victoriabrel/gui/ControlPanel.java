package by.slesh.ri.cp.victoriabrel.gui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = -3423383086415217145L;

	public ControlPanel(){
		initGui();
	}
	
	private void initGui(){
		setPreferredSize(new Dimension(200, 200));
		setBorder(BorderFactory.createTitledBorder("Панель управления"));
	}
}
