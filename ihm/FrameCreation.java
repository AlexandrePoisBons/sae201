package sae201.ihm;

import javax.swing.JFrame;

import sae201.Controleur;
import sae201.ihm.FrameGUI;

public class FrameCreation extends JFrame
{
	private FrameGUI			frm;
	private ControleurCuves     ctrl;
	private PanelCreerCuves   	panelCreerCuves;
    private int               	nbCuves;


	public FrameCreation(ControleurCuves ctrl, int nbCuves)
	{
		this.frm 	 = frm;
		this.ctrl    = ctrl;
        this.nbCuves = nbCuves;
        
		this.setTitle    ( "Creation de Cuves " );
		this.setSize(500, 200);
        //this.pack();

		this.setVisible(true);

		this.panelCreerCuves = new PanelCreerCuves(this, this.ctrl, this.nbCuves);
		this.add(this.panelCreerCuves);
	}
} 