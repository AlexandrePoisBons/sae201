package sae201.ihm;

import javax.swing.JFrame;

import sae201.Controleur;

public class FrameGUI extends JFrame
{
	private Controleur ctrl;
	private PanelGUI   panelInfo;

	public FrameGUI(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle    ( "GUI Application 1" );
		this.setSize(500, 100);
		//this.pack();

		this.setVisible(true);

		this.panelInfo = new PanelGUI(this.ctrl);
		this.add(this.panelInfo);
	}
} 