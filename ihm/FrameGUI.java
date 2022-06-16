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
		

		this.setTitle("GUI Application 1");
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
		this.lblErreur   = new JLabel ("");
		this.panelErreur.add(lblErreur);
		this.add(this.panelErreur, BorderLayout.CENTER);	


		/*--------------------------------------------------*/
		/*               Concernant la JFrame               */
		/*--------------------------------------------------*/	

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	public void majErreur(String erreur)
	{
		this.lblErreur.setText(erreur);
		this.lblErreur.setForeground(Color.RED);		
	}
} 