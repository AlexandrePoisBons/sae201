package sae201.ihm;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.GridLayout;
import sae201.Controleur;

import sae201.metier.*;

public class FrameLierTuyaux extends JFrame
{
	private ControleurCuves ctrl;
	private PanelLierTuyaux   panelInfo;
	private int nbTuyaux;

	public FrameLierTuyaux(ControleurCuves ctrl, int nbTuyaux)
	{
		this.ctrl = ctrl;
		this.nbTuyaux = nbTuyaux;

		this.setTitle    ( "Frame Lier Tuyaux" );
		this.setSize(500, 100);
		//this.pack();

		this.setVisible(true);

		this.panelInfo = new PanelLierTuyaux(this.ctrl, nbTuyaux);
		this.add(this.panelInfo);
	}
}
 