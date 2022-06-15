package sae201.ihm;	

import sae201.Controleur;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;

public class PanelGUI extends JPanel implements ActionListener
{
	private FrameGUI 		 frmParent;
	private ControleurCuves  ctrl;
	private JLabel			 lblInfo;
	private JTextField  	 txtNbCuves;
	private JButton 		 btnValider;

	public PanelGUI( FrameGUI frmParent, ControleurCuves ctrl)
	{
		this.ctrl      = ctrl;
		this.frmParent = frmParent;

		/*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
		this.lblInfo 	= new JLabel("Combien de Cuves souhaitez vous créer ? ");
		this.txtNbCuves = new JTextField(2);
		this.btnValider = new JButton("Valider");
		/*---------------------------------*/

		/*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
		this.add(this.lblInfo);
		this.add(this.txtNbCuves);
		this.add(this.btnValider);	
		/*-------------------------------*/

		/*-----------------------------------*/
        /*     Activation des composants     */
        /*-----------------------------------*/
		this.btnValider.addActionListener(this);
		
	}

	public void actionPerformed (ActionEvent ae)
	{
		String saisie = this.txtNbCuves.getText();
		if (saisie.matches("[0-9]+"))
		{
			int nbCuves = Integer.parseInt(saisie);
			if (nbCuves > 0 && nbCuves < 27 )
			{
				new FrameCreation(this.ctrl, nbCuves);
				this.frmParent.dispose();
			}
			else
			{
				this.txtNbCuves.setText("");
				this.frmParent.majErreur("Vous ne pouvez pas creer 0 ou plus de 26 cuves");
			}
		}
		else
		{
			this.txtNbCuves.setText("");
			this.frmParent.majErreur("Veuillez saisir un nombre entier de cuves");
		}
		
		
	}
}