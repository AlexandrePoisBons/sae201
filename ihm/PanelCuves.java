package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import sae201.Controleur;
import sae201.metier.*;

public class PanelCuves extends JPanel //implements ActionListener
{
	private ControleurCuves       ctrl;
    private ArrayList<Cuve> ensCuves;
    private ArrayList<Tuyau> ensTuyaux;

	private JLabel		lblInfo;

	public PanelCuves( ControleurCuves ctrl, ArrayList<Cuve> ensCuves, ArrayList<Tuyau> ensTuyaux)
	{
		this.ctrl      = ctrl;
        this.ensCuves  = ensCuves;
        this.ensTuyaux = ensTuyaux;

		//Creation des composants//
		this.lblInfo 	= new JLabel("Label de test");

		// Activation des composants //
		

		//Positionnement des composants//
		this.add(this.lblInfo);
	}

}