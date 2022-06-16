package sae201.ihm;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import sae201.Controleur;

public class FrameGUI extends JFrame
{
	private ControleurCuves ctrl;
	private PanelGUI        panelInfo;
	private JPanel			panelErreur;
	private JLabel			lblErreur;

	public FrameGUI(ControleurCuves ctrl)
	{
		this.ctrl = ctrl;
		

		this.setTitle("Choix du nombre de cuves");
		this.setSize (500, 200);
		this.setLayout(new BorderLayout());


		/*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
		
		this.panelInfo = new PanelGUI(this, this.ctrl);

		
		/*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/

		this.add(this.panelInfo, BorderLayout.NORTH);

		this.panelErreur = new JPanel();
		this.lblErreur   = new JLabel ("AAAAAAAAAAAAAAAA"); //initialiser une taille par defaut pour le this.pack()
		this.panelErreur.add(lblErreur);
		this.add(this.panelErreur, BorderLayout.CENTER);	

		this.setResizable(false);
		this.pack();
		this.lblErreur.setText("");


		/*--------------------------------------------------*/
		/*               Concernant la JFrame               */
		/*--------------------------------------------------*/	

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); //enlever le text qui a permit l'initialisation

	}

	public void majErreur(String erreur)
	{
		this.lblErreur.setText(erreur);
		this.lblErreur.setForeground(Color.RED);		
	}
} 