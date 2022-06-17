package sae201.ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.util.ArrayList;
import sae201.ihm.FrameGUI;

public class FrameCreation extends JFrame
{
	private FrameGUI			frm;
	private ControleurCuves     ctrl;
	private PanelCreerCuves   	panelCreerCuves;
	private JPanel 				panelErreur;
	private JLabel				lblErreur;
    private int               	nbCuves;


	public FrameCreation(ControleurCuves ctrl, int nbCuves)
	{
		this.frm 	 = frm;
		this.ctrl    = ctrl;
        this.nbCuves = nbCuves;
        
		this.setTitle("Creation de Cuves ");
		this.setLayout(new BorderLayout());
		this.setSize (500, 200);

		/*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
		this.panelCreerCuves = new PanelCreerCuves(this, this.ctrl, this.nbCuves);
		this.panelErreur     = new JPanel();
		this.lblErreur       = new JLabel();

		/*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
		this.add(this.panelCreerCuves, BorderLayout.NORTH);

		this.panelErreur.add(this.lblErreur);
		this.add(this.panelErreur, BorderLayout.CENTER);
		

		/*--------------------------------------------------*/
		/*               Concernant la JFrame               */
		/*--------------------------------------------------*/
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		/*---------------------------------------------------*/
	}

	public void majPanelErreur(String erreur)
	{
		this.lblErreur.setText(erreur);
		this.lblErreur.setForeground(Color.RED);
	}
} 