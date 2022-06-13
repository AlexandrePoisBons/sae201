package sae201.ihm;

import javax.swing.JFrame;
import sae201.Controleur;

public class FrameTuyaux extends JFrame
{
	private Controleur ctrl;
	private PanelTuyaux   panelInfo;

	public FrameTuyaux(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle    ( "Frame Tuyaux" );
		this.setSize(500, 100);
		//this.pack();

		this.setVisible(true);

		this.panelInfo = new PanelTuyaux(this.ctrl);
		this.add(this.panelInfo);
	}
} 