package sae201.ihm;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.Color;
import java.awt.BorderLayout;

import sae201.Controleur;
import sae201.ihm.PanelCreerCuves;
import sae201.ihm.PanelCreerTuyau;

public class FrameSelectTuyau extends JFrame
{
	private ControleurCuves ctrl;
	private PanelCreerTuyau panelCreerTuyau;
	private JPanel			panelErreur;
    private int             nbTuyaux;   
	private JLabel			lblErreur;

	public FrameSelectTuyau(ControleurCuves ctrl, int nbTuyaux)
	{
        this.ctrl = ctrl;
        this.panelCreerTuyau = new PanelCreerTuyau(this, this.ctrl, nbTuyaux);
		this.panelErreur     = new JPanel();

		this.setTitle("Creation de tuyaux");
		this.setSize (500, 200);
		this.setLayout(new BorderLayout());

		this.lblErreur = new JLabel("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", JLabel.CENTER); // taille par defaut
		this.panelErreur.add(this.lblErreur);
		this.add(this.panelCreerTuyau, BorderLayout.NORTH);	
		this.add(this.panelErreur, 	   BorderLayout.CENTER);	


		/*--------------------------------------------------*/
		/*               Concernant la JFrame               */
		/*--------------------------------------------------*/


		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.lblErreur.setText("");

	}

	public void majErreur(String erreur)
	{
		this.lblErreur.setText(erreur+"<html>");
		this.lblErreur.setForeground(Color.RED);
		this.pack();
	}

} 