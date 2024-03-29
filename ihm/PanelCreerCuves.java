package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.lang.Math;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import sae201.metier.*;

public class PanelCreerCuves extends JPanel implements ActionListener
{
    private FrameCreation               frmParent;
	private ControleurCuves             ctrl;
    private int                         nbCuves;
    private int                         totalTxt;
    private ArrayList<JTextField>       lstTextFields;
    private String                      stringErreurs; // --> contient toute les errreurs;
    private ArrayList<Cuve>             ensCuvesValides;
    private ArrayList<Cuve>             toRemove;

    private JPanel                      panelDonnes;

    private JButton                     btnCreer;


	public PanelCreerCuves(FrameCreation frmParent, ControleurCuves ctrl, int nbCuves)
	{
        this.setLayout(new BorderLayout());
        this.frmParent     = frmParent;
		this.ctrl          = ctrl;
        if (nbCuves < 26)
            this.nbCuves = nbCuves;
        else
            this.nbCuves = 26;

        this.totalTxt        = (this.nbCuves*4);        
        this.lstTextFields   = new ArrayList<JTextField>();
        this.stringErreurs   = "";
        this.ensCuvesValides = new ArrayList<Cuve>();
        this.toRemove        = new ArrayList<Cuve>();

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
        this.stringErreurs = "<html>";

        int capacite    = 0;
        int posX        = 0;     
        int posY        = 0;      
        String position = "";        

        boolean erreur      = false;
        boolean erreurLigne = false;
        boolean cuveOk      = true;
        
        int taille = this.lstTextFields.size();
        for(int i=0; i < taille; i=i+4) // pour chaque ligne 
        {
            erreurLigne = false;

            if (this.lstTextFields.get(i).isEditable()==true ) // marche que pour les cuves pas créées
            {
                // 1- Verifier que c'est bien des entiers
                String saisieCapacite = this.lstTextFields.get(i).getText();
                String saisiePosX     = this.lstTextFields.get(i+1).getText();
                String saisiePosY     = this.lstTextFields.get(i+2).getText();

                if (saisieCapacite.matches("[0-9]+") && saisiePosX.matches("[0-9]+") && saisiePosY.matches("[0-9]+"))
                {
                    capacite    = Integer.parseInt(saisieCapacite);
                    posX        = Integer.parseInt(saisiePosX);
                    posY        = Integer.parseInt(saisiePosY);
                    position    = this.lstTextFields.get(i+3).getText();
                }
                else
                {
                    erreurLigne = true;
                    this.stringErreurs+="La capacite et les coordonnes de la cuves doivent etre entières !<br>";
                }

                // 2 - Verifier que la capacite est valide sinon remet les champs vides
                if ( !erreurLigne && capacite < 200 || capacite > 1000 )
                {
                    this.stringErreurs += "Capacite < 200 ou > 1000 !<br>";
                    erreurLigne = true;
                }
                
                if (posX+(capacite/5) <= 0 || posY+(capacite/5) <= 0 || posX == 0 ||posY == 0 )
                {
                    erreurLigne  = true;
                    cuveOk =  false;
                    this.stringErreurs += "La cuve est trop proche du bord veuillez la decaler<br>";
                }            
               
                boolean positionTaken = false;
                Cuve temp = Cuve.creerCuve(capacite, posX, posY, position);
                // eviter de faire la boucle pour rien
                if (!erreurLigne)
                {
                     // 3 - Verifier que la cuve n'est pas presente                
                    for (Cuve c2 : this.ensCuvesValides)
                    {
                        if (temp == null)
                        {
                            erreurLigne = true;
                            this.stringErreurs += "Impossible de creer la cuve verifiez vos valeurs ! <br>";
                            break;
                        }

                        if (c2 != temp)
                        {
                            int distanceX = Math.abs( c2.getPosX() - temp.getPosX() ); // ecart horizontal entre centre de C et de C2
                            int distanceY = Math.abs( c2.getPosY() - temp.getPosY() ); // ecart vertical   entre centre de C et de C2
                            int ecartMin  = (c2.getCapacite()/5) + (temp.getCapacite()/5);
                            
                            // verif chevauchement
                            if ( (Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) < Math.pow(ecartMin, 2))
                            {
                                erreurLigne = true; 
                                positionTaken = true;
                                
                                
                                if (temp.getPosX() == c2.getPosX() && temp.getPosY() == c2.getPosY())
                                {
                                    erreurLigne = true;
                                    positionTaken = true;
                                    this.stringErreurs += "Position ("+ c2.getPosX() +", "+c2.getPosY()+") deja prise ! <br>";
                                    //break;                
                                }
                                else
                                {
                                    this.stringErreurs+= "Les cuves " +   temp.getId()  + "("+temp.getPosX()+", "+ temp.getPosY()+")" + " et "+ 
                                                        c2.getId() + "("+c2.getPosX()+", "+ c2.getPosY()+") se chevauchent !<br>";
                                }
                            }
                            
                        }                    
                    }
                } 

                if (!erreurLigne && !positionTaken)
                {
                    this.ensCuvesValides.add(temp);;
    
                    this.lstTextFields.get(i).setEditable(false);
                    this.lstTextFields.get(i+1).setEditable(false);
                    this.lstTextFields.get(i+2).setEditable(false);
                    this.lstTextFields.get(i+3).setEditable(false);
                } 
                if ( (erreurLigne && positionTaken) || !cuveOk )
                    Cuve.decrement();          
            }

            // pour chaque ligne si il n'y a pas d'erreur setEditable false
            // si le textField est editable la cuve na pas ete cree
           
            if (erreurLigne)
                erreur = true;  

            // TEST POUR MONTRER QUE LES CUVES ONT BIEN ETE CREES
            System.out.println("iteration "+i);
            for(Cuve c: this.ensCuvesValides)
            {
                System.out.println(c);
            } 
        
        }  
        
        if (erreur)
        {
            this.stringErreurs+="</html>";
            this.frmParent.majPanelErreur(this.stringErreurs);
        }

        if (!erreur)
        {
            new FrameTuyaux(this.ctrl);
            this.ctrl.setCuves(this.ensCuvesValides);
            this.frmParent.dispose();              
            
        }     
    }
}