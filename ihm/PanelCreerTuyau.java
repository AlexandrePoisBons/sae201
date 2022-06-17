package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import sae201.Controleur;
import sae201.ihm.FrameSelectTuyau;
import sae201.metier.*;

public class PanelCreerTuyau extends JPanel implements ActionListener
{
	private ControleurCuves         ctrl;
    private ArrayList<JTextField>   lstTextFields; 
    private ArrayList<Tuyau>        lstTuyaux;
    private FrameSelectTuyau        frmParent;
    private int                     nbTuyaux;
    private JPanel                  panelDonnes;
    private JLabel                  lblSection;
    private JButton                 btnCreer;
    private String                  stringErreur;


	public PanelCreerTuyau(FrameSelectTuyau frmParent, ControleurCuves ctrl, int nbTuyaux)
	{
        this.ctrl          = ctrl;
        this.frmParent     = frmParent;
        this.nbTuyaux      = nbTuyaux;
        this.lstTextFields = new ArrayList<JTextField>();
        this.lstTuyaux     = new ArrayList<Tuyau>();
        this.stringErreur  = "";

        this.setLayout(new BorderLayout());
		

        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/

        this.panelDonnes = new JPanel();
        this.panelDonnes.setLayout(new GridLayout(this.nbTuyaux+1, 1));

        this.lblSection = new JLabel("Section", JLabel.CENTER);

        this.btnCreer = new JButton("Creer");

		for(int cpt = 0; cpt < this.nbTuyaux; cpt++)
            this.lstTextFields.add(new JTextField(JTextField.CENTER));


        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/

        this.panelDonnes.add(this.lblSection);

        for(JTextField txt: this.lstTextFields)
            this.panelDonnes.add(txt, CENTER_ALIGNMENT);

        this.add(this.panelDonnes, BorderLayout.CENTER);
        this.add(this.btnCreer, BorderLayout.SOUTH);


        /*-----------------------------------*/
        /*     Activation des composants     */
        /*-----------------------------------*/
        
        this.btnCreer.addActionListener(this);
	}

	public void actionPerformed (ActionEvent ae)
	{
        boolean erreurLigne = false;
        boolean erreur      = false;
        this.stringErreur = "<html>";

        int taille = this.lstTextFields.size();
        for(int cpt = 0; cpt < this.nbTuyaux; cpt++)
        {
            erreurLigne = false;

            if (this.lstTextFields.get(cpt).isEditable()==true)
            {
                String saisie = this.lstTextFields.get(cpt).getText();
                if ( saisie.matches("[0-9]+") )
                {
                    int section = Integer.parseInt(saisie);
                    if (section > 1 && section < 11)
                    {
                        this.lstTuyaux.add(Tuyau.creerTuyau(section));
                        this.lstTextFields.get(cpt).setEditable(false);
                    }
                    else
                    {
                        erreurLigne = true;
                        this.stringErreur += "La section doit etre comprise entre [ 2; 10 ]<br>";
                    }
                }
                else
                {
                    erreurLigne = true;
                    this.stringErreur += "La section doit etre un nombre entier !<br>";
                }

            }
            if (erreurLigne)
                erreur = true;
        }

        if (erreur)
        {
            this.frmParent.majErreur(stringErreur);
        }

        if (!erreur)
        {
            this.ctrl.setTuyau(this.lstTuyaux);
            new FrameLierTuyaux(this.ctrl, this.nbTuyaux);           
            this.frmParent.dispose();
        }

    }

}

