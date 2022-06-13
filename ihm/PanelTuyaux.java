package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;

import sae201.Controleur;
import sae201.ihm.FrameSelectTuyau;

public class PanelTuyaux extends JPanel implements ActionListener
{
	private Controleur  ctrl;
	private JLabel		lblInfo;
	private JTextField  txtNbCuves;
	private JButton 	btnValider;

	public PanelTuyaux( Controleur ctrl)
	{
		this.ctrl = ctrl;

		//Creation des composants//
		this.lblInfo 	= new JLabel("Combien de Tuyaux souhaitez vous créer ? ");
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
		new FrameSelectTuyau(this.ctrl, Integer.parseInt(this.txtNbCuves.getText()));
	}
}