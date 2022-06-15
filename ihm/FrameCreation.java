package sae201.ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.util.ArrayList;
import sae201.Controleur;
import sae201.ihm.FrameGUI;

public class FrameCreation extends JFrame
{
	private FrameGUI			frm;
	private ControleurCuves     ctrl;
	private PanelCreerCuves   	panelCreerCuves;
	private JPanel 				panelErreur;
    private int               	nbCuves;


	public FrameCreation(ControleurCuves ctrl, int nbCuves)
	{
		this.frm 	 = frm;
		this.ctrl    = ctrl;
        this.nbCuves = nbCuves;
        
		this.setTitle("Creation de Cuves ");
		this.setLayout(new GridLayout(2, 1));
		this.setSize (500, 200);

		/*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
		this.panelCreerCuves = new PanelCreerCuves(this, this.ctrl, this.nbCuves);
		this.panelErreur     = new JPanel();

		/*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
		this.add(this.panelCreerCuves);
		this.add(this.panelErreur);

		/*--------------------------------------------------*/
		/*               Concernant la JFrame               */
		/*--------------------------------------------------*/
	//	this.setExtendedState(JFrame.MAXIMIZED_BOTH );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setResizable(false);
		this.setVisible(true);
		/*---------------------------------------------------*/
	}

	public void majPanelErreur(ArrayList<JLabel> lstLblErreur)
	{
		for(JLabel lbl: lstLblErreur)
			this.panelErreur.add(lbl);
	}
} 