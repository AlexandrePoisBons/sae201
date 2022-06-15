package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.lang.Math;
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
        if (nbCuves < 26)
            this.nbCuves       = nbCuves;
        else
            this.nbCuves = 26;
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
        int cptErreur = 0;
        ArrayList<Integer> arPosX = new ArrayList<Integer>();
        ArrayList<Integer> arPosY = new ArrayList<Integer>();
        int cptPos = 0;
        int cptCuve1 = 0;

        for(int i=0; i < taille; i=i+4)
        {
            this.toRemove = new ArrayList<Cuve>();

            String saisieCapacite = this.lstTextFields.get(i).getText();
            String saisiePosX     = this.lstTextFields.get(i+1).getText();
            String saisiePosY    = this.lstTextFields.get(i+2).getText();

            if (saisieCapacite.matches("[0-9]+") && saisiePosX.matches("[0-9]+") && saisiePosY.matches("[0-9]+"))
            {
                int capacite    = Integer.parseInt(saisieCapacite);
                int posX        = Integer.parseInt(saisiePosX);
                int posY        = Integer.parseInt(saisiePosY);
                String position = this.lstTextFields.get(i+3).getText();
                arPosX.add(posX);   //retiens la position dans un ensemble
                arPosY.add(posY);
            }


            if (Capacite < 200 || Capacite > 1000)
            {
                this.lstTextFields.get(i).setText("");
                this.lstTextFields.get(i+1).setText("");
                this.lstTextFields.get(i+2).setText("");
                this.lstTextFields.get(i+3).setText("");
                erreur = true;
            }

            else
            {
                erreur = true;
                this.lstTextFields.get(i).setEditable(false);
                this.lstTextFields.get(i+1).setEditable(false);
                this.lstTextFields.get(i+2).setEditable(false);
                this.lstTextFields.get(i+3).setEditable(false);


                
                
                if(i != 0)
                {

                    for (int cpt =0; cpt<arPosX.size(); cpt++)
                    {
                        if (posX == arPosX.get(cpt) && posY == arPosY.get(cpt))
                        {
                            cptPos++;   //compara la position avec les autres deja stockes
                        }

                    erreur = true;
                    
                }    

                if(cptPos>=2)   //car la position de l'objet en lui meme est egalemeet stocke et qu'il va donc le comparer
                {
                    System.out.println("ça marche ici");
                    this.lstTextFields.get(i).setText("");
                    this.lstTextFields.get(i+1).setText("");
                    this.lstTextFields.get(i+2).setText("");
                    this.lstTextFields.get(i+3).setText("");

                    
                    this.lstTextFields.get(i).setEditable(true);
                    this.lstTextFields.get(i+1).setEditable(true);
                    this.lstTextFields.get(i+2).setEditable(true);
                    this.lstTextFields.get(i+3).setEditable(true);
                    erreur = true;
                }
                else
                {
                    erreur = false;
                }
            

                    /*int distanceX = Math.abs( c2.getPosX() - posX ); // ecart horizontal entre centre de C et de C2
                    int distanceY = Math.abs( c2.getPosY() - posY ); // ecart vertical   entre centre de C et de C2
                    int ecartMin  = (c2.getCapacite()/5) + (Capacite/5);

                    if ( (Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) < Math.pow(ecartMin, 2))
                    {
                        this.lstTextFields.get(i).setText("");
                        this.lstTextFields.get(i+1).setText("");
                        this.lstTextFields.get(i+2).setText("");
                        this.lstTextFields.get(i+3).setText("");

                        
                        this.lstTextFields.get(i).setEditable(true);
                        this.lstTextFields.get(i+1).setEditable(true);
                        this.lstTextFields.get(i+2).setEditable(true);
                        this.lstTextFields.get(i+3).setEditable(true);
                        erreur = true;
                    }*/

                //si pas d'erreur cree cuve
                //mais comme ca ne cree pas la premiere cuve je fais une condition qui ne marche qu'une fois pour ne pas avoir plein de fois la cuve 1
                if (!erreur)
                {
                    if (cptCuve1 == 0)
                    {
                        this.ensCuves.add(Cuve.creerCuve(Integer.parseInt(this.lstTextFields.get(0).getText()), Integer.parseInt(this.lstTextFields.get(1).getText()), Integer.parseInt(this.lstTextFields.get(2).getText()), this.lstTextFields.get(3).getText()));
                        cptCuve1 = 10;
                    }
                    this.ensCuves.add(Cuve.creerCuve(Capacite, posX, posY, position));
                }
            }
            
            /*if (erreur)
            {
                cptErreur++;
            }
            }

            if (cptErreur != 0)
            {
                erreur = true;
            }*/
                
            /*for(int i=0; i < this.ensCuves.size()*4; i=i+4)
            {
                for(Cuve c : this.ensCuves)
                {
                    for (Cuve c2 : this.ensCuves)
                    {
                        int distanceX = Math.abs( c2.getPosX() - c.getPosX() ); // ecart horizontal entre centre de C et de C2
                        int distanceY = Math.abs( c2.getPosY() - c.getPosY() ); // ecart vertical   entre centre de C et de C2
                        int ecartMin  = (c2.getCapacite()/5) + (c.getCapacite()/5);

                
                        if (c.getPosX() == c2.getPosX() && c.getPosY() == c2.getPosY())
                        {
                            this.lstTextFields.get(i).setText("");
                            this.lstTextFields.get(i+1).setText("");
                            this.lstTextFields.get(i+2).setText("");
                            this.lstTextFields.get(i+3).setText("");

                            
                            this.lstTextFields.get(i).setEditable(true);
                            this.lstTextFields.get(i).setEditable(true);
                            this.lstTextFields.get(i).setEditable(true);
                            this.lstTextFields.get(i).setEditable(true);
                            erreur = true;

                            this.ensCuves.remove(c);
                            this.ensCuves.remove(c2);
                            this.lstLblErreurs.add(new JLabel("Position ("+ c2.getPosX() +", "+c2.getPosY()+") deja prise"));
                            //break;                

                        }

                        if ( (Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) < Math.pow(ecartMin, 2))
                        {

                            this.lstTextFields.get(i).setText("");
                            this.lstTextFields.get(i+1).setText("");
                            this.lstTextFields.get(i+2).setText("");
                            this.lstTextFields.get(i+3).setText("");

                            
                            this.lstTextFields.get(i).setEditable(true);
                            this.lstTextFields.get(i).setEditable(true);
                            this.lstTextFields.get(i).setEditable(true);
                            this.lstTextFields.get(i).setEditable(true);
                            erreur = true;
                            this.ensCuves.remove(c);
                            this.ensCuves.remove(c2);

                            this.lstLblErreurs.add(
                                new JLabel("Les cuves " + 
                                    c.getId()  + "("+c.getPosX()+", "+ c.getPosY()+")" + " et "+ 
                                    c2.getId() + "("+c2.getPosX()+", "+ c2.getPosY()+") se chevauchent"));
                            erreur = true; 
                            //break;
                        }
                    }
                }
            }*/


            if (erreur)
            {
                /*for (int cpt=0; cpt<this.lstTextFields.size()/4; cpt++) // si il y a une erreur remettre les liens "à 0"
                {
                   lstTextFields.remove(cpt);
                }
                this.frmParent.majPanelErreur(this.lstLblErreurs);*/

                    
                }            
            }
            /*
            for (Cuve c: toRemove)
            {
                this.lstLblErreurs.add(new JLabel("Impossible de creer la cuve: [ " + c + " ]", JLabel.CENTER));
                this.lstLblErreurs.get(this.lstLblErreurs.size()-1).setForeground(Color.RED);
                this.ensCuves.remove(this.ensCuves.lastIndexOf(c));
            }
            */          
        }  
        
        if (erreur)
        {
            /*
            for (int cpt=0; cpt<this.lstTextFields.size()/4; cpt++) // si il y a une erreur remettre les liens "à 0"
            {
               lstTextFields.remove(cpt);
            }
            */
            this.ensCuves.clear();
            this.frmParent.majPanelErreur(this.lstLblErreurs);

            for (JTextField txt: this.lstTextFields)
            {
                new FrameTuyaux(this.ctrl);
                this.ctrl.setCuves(this.ensCuves);
                this.frmParent.dispose();              
                
            } 

                txt.setText("");
            }
        }

        // FIN
        if (!erreur)
        {
            new FrameTuyaux(this.ctrl);
            this.ctrl.setCuves(this.ensCuves);
            this.frmParent.dispose();              
            
        }
  
            // TEST AFFICHAGE //
            for(Cuve c: this.ensCuves)
            {
                System.out.println(c);
                //Sysout pour tester//
            }       
        }
    }
}