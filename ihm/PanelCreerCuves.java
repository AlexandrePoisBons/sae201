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
    private JFrame                      frmParent;
	private ControleurCuves             ctrl;
    private int                         nbCuves;
    private int                         totalTxt;
    private ArrayList<JTextField>       lstTextFields; 
    private ArrayList<Cuve>             ensCuves;
    private ArrayList<Cuve>            toRemove;

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
        int taille = this.lstTextFields.size();
        for(int i=0; i < taille; i=i+4)
        {
            int Capacite    = Integer.parseInt(this.lstTextFields.get(i).getText());
            int posX        = Integer.parseInt(this.lstTextFields.get(i+1).getText());
            int posY        = Integer.parseInt(this.lstTextFields.get(i+2).getText());
            String position = this.lstTextFields.get(i+3).getText();

            this.ensCuves.add(Cuve.creerCuve(Capacite, posX, posY, position));
            
        }
        
        // TEST AFFICHAGE //
        for(Cuve c: this.ensCuves)
        {
            System.out.println(c);
            //Sysout pour tester//
        }        

        //TEST CUVE DEJA PRESENTE //
        boolean positionTaken;
        for (Cuve c: this.ensCuves)						
        {
            for (Cuve c2 : this.ensCuves)
            {
                if (c2 != c)
                {
                    if (c.getPosX() == c2.getPosX() && c.getPosY() == c.getPosY())
                    {
                        this.toRemove.add(c2);
                        break;                
                    }
                }
                
            }            
        }
        // FIN
        new FrameTuyaux(this.ctrl);
        this.ctrl.setCuves(this.ensCuves);
        this.frmParent.dispose();
    }
}