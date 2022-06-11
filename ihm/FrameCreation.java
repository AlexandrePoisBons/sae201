package sae201.ihm;

import javax.swing.JFrame;

import sae201.Controleur;

public class FrameCreation extends JFrame
{
	private Controleur        ctrl;
	private PanelCreerCuves   panelCreerCuves;
    private int               nbCuves;    

	public FrameCreation(Controleur ctrl, int nbCuves)
	{
		this.ctrl    = ctrl;
        this.nbCuves = nbCuves;
        
		this.setTitle    ( "Creation de Cuves" );
        this.setSize(500, 100);
        //this.pack();

		this.setVisible(true);

		this.panelCreerCuves = new PanelCreerCuves(this.ctrl, this.nbCuves);
		this.add(this.panelCreerCuves);
	}
} 