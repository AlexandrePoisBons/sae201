package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.ArrayList;

import sae201.Controleur;
import sae201.metier.*;

public class PanelLierTuyaux extends JPanel implements ActionListener
{
	private ControleurCuves       ctrl;
	private JLabel			      lblInfo;
	private JTextField  	      txtNbCuves;
	private JButton 		      btnValider;
    private int                   nbTuyaux;
    private ArrayList<JTextField> lstText;

	public PanelLierTuyaux( ControleurCuves ctrl, int nbTuyaux)
	{
		this.ctrl     = ctrl;
        this.nbTuyaux = nbTuyaux;
        this.lstText  = new ArrayList<JTextField>();

        this.setLayout(new GridLayout(this.nbTuyaux+1, 2));

		//Creation des composants//
		this.btnValider = new JButton("Valider");

        for (int i=0; i<this.nbTuyaux*2; i++)
        {
            this.lstText.add(new JTextField(1));
        }

        for (JTextField t: this.lstText)
        {
            this.add(t);
        }

		// Activation des composants //
		this.btnValider.addActionListener(this);

		//Positionnement des composants//
		this.add(this.btnValider);	
		
	}

	public void actionPerformed (ActionEvent ae)
	{
        for (int j=0; j<this.nbTuyaux; j=j+2)
        {
            Cuve c1 = this.ctrl.estCuve(this.lstText.get(j).getText().charAt(0));
            Cuve c2 = this.ctrl.estCuve(this.lstText.get(j+1).getText().charAt(0));
            this.ctrl.setLien(j, c1, c2);
        }
		this.ctrl.generer();
	}
}