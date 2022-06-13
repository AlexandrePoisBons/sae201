package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import sae201.Controleur;
import sae201.metier.*;

public class PanelCreerTuyau extends JPanel implements ActionListener
{
	private Controleur            ctrl;
    private int                   nbCuves;
    private int                   totalTxt;
    private ArrayList<JTextField> lstTextFields; 

    private JPanel                  panelDonnes;
    private JLabel                  lblSection;

    private JButton                 btnCreer;


	public PanelCreerTuyau( Controleur ctrl)
	{
        this.setLayout(new BorderLayout());
		this.ctrl    = ctrl;
        this.nbCuves = nbCuves;
        this.totalTxt   = (this.nbCuves*4);        
        this.lstTextFields = new ArrayList<JTextField>();

        // creation des composants //
        this.panelDonnes = new JPanel();
        this.panelDonnes.setLayout(new GridLayout(this.nbCuves+1, 1));

        this.lblSection = new JLabel("Section", JLabel.CENTER);

        this.btnCreer = new JButton("Creer");

		for(int i=0; i < this.totalTxt; i++)
        {
            this.lstTextFields.add(new JTextField());
        }


        // activation des composants//
        this.btnCreer.addActionListener(this);

        // positionnement des composants //
        this.panelDonnes.add(this.lblSection);

        for(JTextField txt: this.lstTextFields)
        {
            this.panelDonnes.add(txt);
        }

        this.add(this.panelDonnes, BorderLayout.CENTER);
        this.add(this.btnCreer, BorderLayout.SOUTH);
		
	}

	public void actionPerformed (ActionEvent ae)
	{
        int taille = this.lstTextFields.size();
        for(int i=0; i < taille; i++)
        {
            int section    = Integer.parseInt(this.lstTextFields.get(i).getText());

            System.out.println(Tuyau.creerTuyau(section));
            //Sysout pour tester//
        }
    }
}