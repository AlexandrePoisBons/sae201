package sae201.ihm;

import sae201.Controleur;
import sae201.metier.*;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;


public class FrameTuyaux extends JFrame
{
	private ControleurCuves ctrl;
	private PanelTuyaux     panelInfo;
	private JPanel 			panelErreur;
	private JLabel 			lblErreur;

	public FrameTuyaux(ControleurCuves ctrl)
	{
		this.ctrl      = ctrl;

		this.setTitle("Frame Tuyaux");
		this.setSize (500, 200);

		this.panelInfo   = new PanelTuyaux(this, this.ctrl);
		this.panelErreur = new JPanel();
		this.lblErreur   = new JLabel();

		this.panelErreur.add(this.lblErreur);

		this.add(this.panelInfo,   BorderLayout.NORTH);
		this.add(this.panelErreur, BorderLayout.CENTER);

	//	this.setExtendedState(JFrame.MAXIMIZED_BOTH );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

    public void maj(ArrayList<Tuyau> lstTuyaux) // A CHANGER CREER PANEL A PART//
    {
        this.panelInfo = new PanelTuyaux(this, this.ctrl);
		
        this.panelInfo.setLayout(new GridLayout(lstTuyaux.size()+1, 2));

        this.panelInfo.add(new JLabel("Cuve Origine", JLabel.CENTER));
        this.panelInfo.add(new JLabel("Cuve Destination", JLabel.CENTER));
        
        for(int j=0; j< lstTuyaux.size(); j++)
        {
            this.add(new JTextField());
            this.add(new JTextField());
        }

        this.add(this.panelInfo);
    }

	public void majErreur(int nbMax)
	{
		this.lblErreur.setText("Il y a" + this.ctrl.getNbCuves() + "dans votre reseau, vous ne pouvez ainsi pas creer
								plus de "+ nbMax +" tuyaux");
		this.lblErreur.setForeground(Color.RED);
	}
} 