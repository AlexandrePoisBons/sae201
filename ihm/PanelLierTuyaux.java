package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import sae201.Controleur;
import sae201.ihm.FrameLierTuyaux;
import sae201.metier.*;

public class PanelLierTuyaux extends JPanel implements ActionListener
{
    private FrameLierTuyaux       frmParent;
	private ControleurCuves       ctrl;
	private JLabel			      lblInfo;
	private JTextField  	      txtNbCuves;
	private JButton 		      btnValider;
    private int                   nbTuyaux;
    private ArrayList<JTextField> lstText;
    private ArrayList<Tuyau>      toRemove;
    private ArrayList<JLabel>     lstLblErreurs;

    private JPanel                panelErreurs;
    

	public PanelLierTuyaux(FrameLierTuyaux frmParent, ControleurCuves ctrl, int nbTuyaux)
	{
        this.frmParent     = frmParent;
		this.ctrl          = ctrl;
        this.nbTuyaux      = nbTuyaux;
        this.lstText       = new ArrayList<JTextField>();
        this.lstLblErreurs = new ArrayList<JLabel>();

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
        boolean erreur = false;
        //int taille = (this.nbTuyaux*2);
        for (int j=0; j<this.ctrl.ensTuyau.size()*2; j=j+2)
        {
            Cuve c1 = this.ctrl.estCuve(this.lstText.get(j).getText().charAt(0));
            Cuve c2 = this.ctrl.estCuve(this.lstText.get(j+1).getText().charAt(0));
            this.ctrl.setLien(j/2, c1, c2);
            //VERIFICATION EXISTENCE TUYAU


            this.toRemove = new ArrayList<Tuyau>();
            boolean alreadySelected = false;
            for (Tuyau t3: this.ctrl.ensTuyau)
            {
                for (Tuyau t2: this.ctrl.ensTuyau)
                {
                    if (t3 != t2 && t3.equals(t2)) // t3!=t2 --> compare les adresses memoire = eviter de se compareer a lui meme
                    {
                        for (Tuyau tRemove : toRemove)
                        {
                            if (tRemove.equals(t3)) // si c'est le meme compare les cuves 
                            {
                                alreadySelected = true;
                                break;
                            }							
                        }
                        if (!alreadySelected)
                            toRemove.add(this.ctrl.ensTuyau.get(this.ctrl.ensTuyau.lastIndexOf(t3)));
                            erreur = true;
                    }
                }
            }
            for (Tuyau tRemove : toRemove)
            {
                this.lstLblErreurs.add(new JLabel("Impossible de creer le tuyau: [ " + tRemove + " ]", JLabel.CENTER));
                this.lstLblErreurs.get(this.lstLblErreurs.size()-1).setForeground(Color.RED);
                this.ctrl.ensTuyau.remove(tRemove);
            }


            //TEST AFFICHAGE
            //System.out.println(this.ctrl.ensTuyau.get(j/2));
        }
        if (erreur)
        {
            for (Tuyau t: this.ctrl.ensTuyau) // si il y a une erreur remettre les liens "à 0"
            {
                //t.setLien(null, null);
            }
            this.frmParent.majPanelErreur(this.lstLblErreurs);

            for (JTextField txt: this.lstText)
            {
                txt.setText("");
            }
        }
        if (!erreur)
        {
            new FrameFormat(this.ctrl);
            this.frmParent.dispose();
        }
        
		//this.ctrl.generer();
	}

    /*
 * Cuve[] cuveALier= new Cuve[2];
		for (int j=0; j< nbTuyaux; j++)
		{
			System.out.print("Quelles cuves voulez vous relier ?\n");
			String stringCuve1 = sc.next();
			String stringCuve2 = sc.next();
			for (Cuve c :ensCuves)
			{
				if (c.getId() == stringCuve1.charAt(0)) 
					cuveALier[0] =  c;
				if (c.getId() == stringCuve2.charAt(0))
					cuveALier[1] = c;
			}

			
			//ensTuyau.get(ensTuyau.lastIndexOf(t)).setLien(cuveALier[0], cuveALier[1]); --> lier directement

			// Verifier si le tuyau n'existe pas deja // Erreur ici ou Tuyau.equals
			ensTuyau.get(j).setLien(cuveALier[0], cuveALier[1]);

			
						
 */
}