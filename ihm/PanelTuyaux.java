package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;

import sae201.Controleur;
import sae201.ihm.FrameTuyaux;

public class PanelTuyaux extends JPanel implements ActionListener
{
	private FrameTuyaux		 frmParent;
	private ControleurCuves  ctrl;
	private JLabel			 lblInfo;
	private JTextField  	 txtNbCuves;
	private JButton 		 btnValider;

	public PanelTuyaux(FrameTuyaux frmParent, ControleurCuves ctrl)
	{
		this.ctrl = ctrl;
		this.frmParent = frmParent;

		/*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/

		this.lblInfo 	= new JLabel("Combien de Tuyaux souhaitez vous créer ? ");
		this.txtNbCuves = new JTextField(2);
		this.btnValider = new JButton("Valider");


		/*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/

		this.add(this.lblInfo);
		this.add(this.txtNbCuves);
		this.add(this.btnValider);


		/*-----------------------------------*/
        /*     Activation des composants     */
        /*-----------------------------------*/
		
		this.btnValider.addActionListener(this);
		
	}

	public void actionPerformed (ActionEvent ae)
	{
		if (this.txtNbCuves.getText().matches("[0-9]+"))
		{
			int nbTuyaux = Integer.parseInt(this.txtNbCuves.getText());
			int nbCuves  = this.ctrl.getNbCuves();
			int nbMax 	 = nbCuves*(nbCuves-1)/2;

			if (nbTuyaux <= nbMax)
			{
				new FrameSelectTuyau(this.ctrl, nbTuyaux);
				this.frmParent.dispose();
			}

			else
			{
				this.txtNbCuves.setText("");
				this.frmParent.majErreur("Il y a " + this.ctrl.getNbCuves() + " cuve(s) dans votre reseau, vous ne pouvez ainsi pas creer plus de "+ nbMax +" tuyaux");
			}
		}

		else
		{
			this.txtNbCuves.setText("");	
			this.frmParent.majErreur("Le nombre de tuyaux doit etre entier");
		}
		
		
	}
}