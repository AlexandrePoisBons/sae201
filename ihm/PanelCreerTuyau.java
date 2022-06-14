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
	private ControleurCuves       ctrl;
    private JFrame                frmParent;
    private int                   nbTuyaux;
    private ArrayList<JTextField> lstTextFields; 
    private ArrayList<Tuyau>      lstTuyaux;

    private JPanel                  panelDonnes;
    private JLabel                  lblSection;
    private JButton                 btnCreer;


	public PanelCreerTuyau(JFrame frmParent, ControleurCuves ctrl, int nbTuyaux)
	{
        this.setLayout(new BorderLayout());
		this.ctrl          = ctrl;
        this.frmParent     = frmParent;
        this.nbTuyaux      = nbTuyaux;
        this.lstTextFields = new ArrayList<JTextField>();
        this.lstTuyaux     = new ArrayList<Tuyau>();

        // creation des composants //
        this.panelDonnes = new JPanel();
        this.panelDonnes.setLayout(new GridLayout(this.nbTuyaux+1, 1));

        this.lblSection = new JLabel("Section", JLabel.CENTER);

        this.btnCreer = new JButton("Creer");

		for(int i=0; i < this.nbTuyaux; i++)
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
        for(int i=0; i < this.nbTuyaux; i++)
        {
            int section    = Integer.parseInt(this.lstTextFields.get(i).getText());
            this.lstTuyaux.add(Tuyau.creerTuyau(section));
            System.out.println(this.lstTuyaux.get(i));
            //Sysout pour tester//
        }
        //this.parent.maj(this.lstTuyaux);
        new FrameLierTuyaux(this.ctrl, this.nbTuyaux);
        this.ctrl.setTuyau(this.lstTuyaux);
        this.frmParent.dispose();
    }

}

