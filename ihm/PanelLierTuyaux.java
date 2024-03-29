package sae201.ihm;	

import sae201.ihm.FrameLierTuyaux;
import sae201.metier.*;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.ArrayList;



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
    private JLabel                lblSection;
    private JLabel                lblCuve1;
    private JLabel                lblCuve2; 
    private JPanel                panelInfos;   

	public PanelLierTuyaux(FrameLierTuyaux frmParent, ControleurCuves ctrl, int nbTuyaux)
	{
        this.frmParent     = frmParent;
		this.ctrl          = ctrl;
        this.nbTuyaux      = nbTuyaux;
        this.lstText       = new ArrayList<JTextField>();
        this.lstLblErreurs = new ArrayList<JLabel>();
        this.panelInfos    = new JPanel();

        this.panelInfos.setLayout(new GridLayout(this.nbTuyaux+1, 3));
        this.setLayout(new BorderLayout());
		/*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
		this.lblSection = new JLabel("Section");
        this.lblCuve1   = new JLabel("Cuve 1");
        this.lblCuve2   = new JLabel("Cuve 2");
        this.btnValider = new JButton("Valider");

        this.panelInfos.add(this.lblSection);
        this.panelInfos.add(this.lblCuve1);
        this.panelInfos.add(this.lblCuve2);

        int cpt=0;
        for (int i=0; i<this.nbTuyaux*2; i++)
        {
            this.lstText.add(new JTextField(1));            
        }
            
        cpt=0;
        for (int i=0; i<this.lstText.size(); i++)
        {
            if (i%2 == 0)
            {                
                this.panelInfos.add(new JLabel(""+this.ctrl.ensTuyau.get(cpt).getSection(), JLabel.CENTER));
                cpt++;
            }
            this.panelInfos.add(this.lstText.get(i));            
        }
        /*---------------------------------*/

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add(this.panelInfos, BorderLayout.NORTH);
        this.add(this.btnValider, BorderLayout.CENTER);  

		/*-----------------------------------*/
        /*     Activation des composants     */
        /*-----------------------------------*/
		this.btnValider.addActionListener(this);
	}

	public void actionPerformed (ActionEvent ae)
	{
        boolean erreur = false;
        //int taille = (this.nbTuyaux*2);
        for (int j=0; j<this.ctrl.ensTuyau.size()*2; j=j+2)
        {
            Cuve c1 = this.ctrl.estCuve(this.lstText.get(j).getText().charAt(0));
            Cuve c2 = this.ctrl.estCuve(this.lstText.get(j+1).getText().charAt(0));
            if (c1 == null || c2 == null)
                erreur = true;
            this.ctrl.setLien(j/2, c1, c2);
            //VERIFICATION EXISTENCE TUYAU

            this.toRemove = new ArrayList<Tuyau>();
            boolean alreadySelected = false;
            for (Tuyau t3: this.ctrl.ensTuyau)
                for (Tuyau t2: this.ctrl.ensTuyau)
                    if (t3 != t2 && t3.equals(t2)) // t3!=t2 --> compare les adresses memoire = eviter de se compareer a lui meme
                    {
                        for (Tuyau tRemove : toRemove)
                            if (tRemove.equals(t3)) // si c'est le meme compare les cuves 
                            {
                                alreadySelected = true;
                                break;
                            }			

                        if (!alreadySelected)
                        {
                            toRemove.add(this.ctrl.ensTuyau.get(this.ctrl.ensTuyau.lastIndexOf(t3)));
                            erreur = true;
                        }
                    }

            for (Tuyau tRemove : toRemove)
            {
                this.lstLblErreurs.add(new JLabel("Impossible de creer le tuyau: [ " + tRemove + " ]", JLabel.CENTER));
                this.lstLblErreurs.get(this.lstLblErreurs.size()-1).setForeground(Color.RED);
                this.ctrl.ensTuyau.remove(tRemove);
            }

        }
        if (erreur)
        {
            this.frmParent.majPanelErreur(this.lstLblErreurs);

            for (JTextField txt: this.lstText)
                txt.setText("");
        }
        if (!erreur)
        {
            new FrameFormat(this.ctrl);
            this.frmParent.dispose();
        }
    }
}