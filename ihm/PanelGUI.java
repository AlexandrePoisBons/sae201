package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;

import sae201.Controleur;

public class PanelGUI extends JPanel implements ActionListener
{
	private FrameGUI 		 frmParent;
	private ControleurCuves  ctrl;
	private JLabel			 lblInfo;
	private JTextField  	 txtNbCuves;
	private JButton 		 btnValider;

	public PanelGUI( FrameGUI frmParent, ControleurCuves ctrl)
	{
		this.ctrl = ctrl;
		this.frmParent = frmParent;

		//Creation des composants//
		this.lblInfo 	= new JLabel("Combien de Cuves souhaitez vous créer ? ");
		this.txtNbCuves = new JTextField(2);
		this.btnValider = new JButton("Valider");

		// Activation des composants //
		this.btnValider.addActionListener(this);

		//Positionnement des composants//
		this.add(this.lblInfo);
		this.add(this.txtNbCuves);
		this.add(this.btnValider);	
		
	}

	public void actionPerformed (ActionEvent ae)
	{
		new FrameCreation(this.ctrl, Integer.parseInt(this.txtNbCuves.getText()));
		this.frmParent.dispose();
	}
}