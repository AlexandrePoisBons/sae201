package sae201.ihm;

import javax.swing.JFrame;

import sae201.Controleur;

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