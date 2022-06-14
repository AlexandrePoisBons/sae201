package sae201.ihm;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.BorderLayout;
import sae201.Controleur;

import sae201.metier.*;

public class FrameLierTuyaux extends JFrame
{
	private ControleurCuves ctrl;
	private PanelLierTuyaux panelInfo;
	private JPanel 			panelErreur;
	private int 			nbTuyaux;

	public FrameLierTuyaux(ControleurCuves ctrl, int nbTuyaux)
	{
		this.ctrl = ctrl;
		this.nbTuyaux = nbTuyaux;

		this.setTitle    ( "Frame Lier Tuyaux" );
		this.setSize(500, 200);
		this.setLayout(new BorderLayout());
		//this.pack();

		this.setVisible(true);
		this.panelInfo 	 = new PanelLierTuyaux(this, this.ctrl, nbTuyaux);
		this.panelErreur = new JPanel();	
		this.add(this.panelInfo, BorderLayout.NORTH);
		this.add(this.panelErreur, BorderLayout.SOUTH);
	}

	public void majPanelErreur(ArrayList<JLabel> lstLblErreur)
	{
		for(JLabel lbl: lstLblErreur)
		{
			this.panelErreur.add(lbl);
		}
	}
}
 