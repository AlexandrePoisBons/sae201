package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import sae201.Controleur;
import sae201.metier.*;

public class PanelCreerCuves extends JPanel implements ActionListener
{
    private FrameCreation               frmParent;
	private ControleurCuves             ctrl;
    private int                         nbCuves;
    private int                         totalTxt;
    private ArrayList<JTextField>       lstTextFields;
    private ArrayList<JLabel>           lstLblErreurs; 
    private ArrayList<Cuve>             ensCuves;
    private ArrayList<Cuve>             toRemove;

    private JPanel                      panelDonnes;

    private JButton                     btnCreer;


	public PanelCreerCuves(FrameCreation frmParent, ControleurCuves ctrl, int nbCuves)
	{
        this.setLayout(new BorderLayout());
        this.frmParent     = frmParent;
		this.ctrl          = ctrl;
        this.nbCuves       = nbCuves;
        this.totalTxt      = (this.nbCuves*4);        
        this.lstTextFields = new ArrayList<JTextField>();
        this.lstLblErreurs = new ArrayList<JLabel>();
        this.ensCuves      = new ArrayList<Cuve>();
        this.toRemove      = new ArrayList<Cuve>();

        // creation des composants //
        this.panelDonnes = new JPanel();
        this.panelDonnes.setLayout(new GridLayout(this.nbCuves+1, 4));

        JLabel lblCapacite = new JLabel("Capacite", JLabel.CENTER);
        JLabel lblPosX     = new JLabel("Pos X",    JLabel.CENTER);
        JLabel lblPosY     = new JLabel("Pos Y",    JLabel.CENTER);
        JLabel lblPosition = new JLabel("Position", JLabel.CENTER);

        this.btnCreer = new JButton("Creer");

		for(int i=0; i < this.totalTxt; i++)
        {
            this.lstTextFields.add(new JTextField());
        }


        // activation des composants//
        this.btnCreer.addActionListener(this);

        // positionnement des composants //
        this.panelDonnes.add(lblCapacite);
        this.panelDonnes.add(lblPosX);
        this.panelDonnes.add(lblPosY);
        this.panelDonnes.add(lblPosition);

        for(JTextField txt: this.lstTextFields)
        {
            this.panelDonnes.add(txt);
        }

        this.add(this.panelDonnes, BorderLayout.CENTER);
        this.add(this.btnCreer, BorderLayout.SOUTH);
		
	}

	public void actionPerformed (ActionEvent ae)
	{
        boolean erreur = false;
        int taille = this.lstTextFields.size();
        for(int i=0; i < taille; i=i+4)
        {
            int Capacite    = Integer.parseInt(this.lstTextFields.get(i).getText());
            int posX        = Integer.parseInt(this.lstTextFields.get(i+1).getText());
            int posY        = Integer.parseInt(this.lstTextFields.get(i+2).getText());
            String position = this.lstTextFields.get(i+3).getText();

            this.ensCuves.add(Cuve.creerCuve(Capacite, posX, posY, position));

            //TEST CUVE DEJA PRESENTE //
            for (Cuve c: this.ensCuves)						
            {
                for (Cuve c2 : this.ensCuves)
                {
                    if (c2 != c)
                    {
                        if (c.getPosX() == c2.getPosX() && c.getPosY() == c.getPosY())
                        {
                            erreur = true;
                            this.toRemove.add(c2);
                            break;                
                        }
                    }
                    
                }            
            }
            for (Cuve c: toRemove)
            {
                this.lstLblErreurs.add(new JLabel("Impossible de creer la cuve: [ " + c + " ]", JLabel.CENTER));
                this.lstLblErreurs.get(this.lstLblErreurs.size()-1).setForeground(Color.RED);
                this.ensCuves.remove(this.ensCuves.lastIndexOf(c));
            }

            if (erreur)
            {
                for (int cpt=0; cpt<this.lstTextFields.size()/4; cpt++) // si il y a une erreur remettre les liens "à 0"
                {
                   lstTextFields.remove(cpt);
                }
                this.frmParent.majPanelErreur(this.lstLblErreurs);
    
                for (JTextField txt: this.lstTextFields)
                {
                    txt.setText("");
                }
            }

            // FIN
            if (!erreur)
            {
                if (this.ensCuves.size() == 0)
                {
                    this.ctrl.generer();
                }
                else 
                {
                    new FrameTuyaux(this.ctrl);
                    this.ctrl.setCuves(this.ensCuves);
                    this.frmParent.dispose();
                }
                
                
            }
                
        }      
  
        // TEST AFFICHAGE //
        for(Cuve c: this.ensCuves)
        {
            System.out.println(c);
            //Sysout pour tester//
        }        
    }
}