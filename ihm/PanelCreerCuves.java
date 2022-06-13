package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import sae201.Controleur;
import sae201.metier.*;

public class PanelCreerCuves extends JPanel implements ActionListener
{
	private Controleur            ctrl;
    private int                   nbCuves;
    private int                   totalTxt;
    private ArrayList<JTextField> lstTextFields; 

    private JPanel                  panelDonnes;
    private JLabel                  lblCapacite;
    private JLabel                  lblPosX;
    private JLabel                  lblPosY;
    private JLabel                  lblPosition;

    private JButton                 btnCreer;


	public PanelCreerCuves( Controleur ctrl, int nbCuves)
	{
        this.setLayout(new BorderLayout());
		this.ctrl    = ctrl;
        this.nbCuves = nbCuves;
        this.totalTxt   = (this.nbCuves*4);        
        this.lstTextFields = new ArrayList<JTextField>();

        // creation des composants //
        this.panelDonnes = new JPanel();
        this.panelDonnes.setLayout(new GridLayout(this.nbCuves+1, 4));

        this.lblCapacite = new JLabel("Capacite", JLabel.CENTER);
        this.lblPosX     = new JLabel("Pos X",    JLabel.CENTER);
        this.lblPosY     = new JLabel("Pos Y",    JLabel.CENTER);
        this.lblPosition = new JLabel("Position", JLabel.CENTER);

        this.btnCreer = new JButton("Creer");

		for(int i=0; i < this.totalTxt; i++)
        {
            this.lstTextFields.add(new JTextField());
        }


        // activation des composants//
        this.btnCreer.addActionListener(this);

        // positionnement des composants //
        this.panelDonnes.add(this.lblCapacite);
        this.panelDonnes.add(this.lblPosX);
        this.panelDonnes.add(this.lblPosY);
        this.panelDonnes.add(this.lblPosition);

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
        for(int i=0; i < taille; i=i+4)
        {
            int Capacite    = Integer.parseInt(this.lstTextFields.get(i).getText());
            int posX        = Integer.parseInt(this.lstTextFields.get(i+1).getText());
            int posY        = Integer.parseInt(this.lstTextFields.get(i+2).getText());
            String position = this.lstTextFields.get(i+3).getText();

            System.out.println(Cuve.creerCuve(Capacite, posX, posY, position));
            //Sysout pour tester//
        }

        new FrameTuyaux(this.ctrl);
    }
}