package IHM;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class FrameGUI extends JFrame
{
	private Controleur ctrl;
	private PanelGUI   panelGUI;

	public FrameGUI(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle    ( "GUI Application 1" );
		this.setResizable(false);

		this.setVisible(true);
	}
} 