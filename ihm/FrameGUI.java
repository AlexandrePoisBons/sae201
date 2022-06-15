package sae201.ihm;

import javax.swing.JFrame;

import sae201.Controleur;

public class FrameGUI extends JFrame
{
	private ControleurCuves ctrl;
	private PanelGUI        panelInfo;

	public FrameGUI(ControleurCuves ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("GUI Application 1");
		this.setSize (500, 200);

		this.panelInfo = new PanelGUI(this, this.ctrl);

		this.add(this.panelInfo);

		/*--------------------------------------------------*/
		/*               Concernant la JFrame               */
		/*--------------------------------------------------*/
		this.setExtendedState(JFrame.MAXIMIZED_BOTH );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		/*---------------------------------------------------*/
	}
} 