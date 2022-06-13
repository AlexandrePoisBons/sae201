package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

import sae201.Controleur;
import sae201.metier.*;
import java.awt.Color;

public class PanelCuves extends JPanel //implements ActionListener
{
	private ControleurCuves       ctrl;
    private ArrayList<Cuve> ensCuves;
    private ArrayList<Tuyau> ensTuyaux;

	private ArrayList<JLabel> lstLblCuves;
	private ArrayList<JLabel> lstLblTuyaux;

	private JLabel		lblInfo;

	public PanelCuves( ControleurCuves ctrl, ArrayList<Cuve> ensCuves, ArrayList<Tuyau> ensTuyaux)
	{
		this.ctrl      = ctrl;
        this.ensCuves  = ensCuves;
        this.ensTuyaux = ensTuyaux;
		this.lstLblCuves = new ArrayList<JLabel>();
		this.lstLblTuyaux = new ArrayList<JLabel>();

		//Creation des composants//
		/*
		for (Cuve c: this.ensCuves)
		{
			this.lstLblCuves.add(new JLabel(""+c));
		}
		
		this.lstLblTuyaux.add(new JLabel("Tuyaux:"));

		for (Tuyau t: this.ensTuyaux)
		{
			this.lstLblTuyaux.add(new JLabel(""+t));
		}


		// Activation des composants //
		

		//Positionnement des composants//
		for (JLabel lbl : this.lstLblCuves)
		{
			this.add(lbl);
		}

		for (JLabel lbl : this.lstLblTuyaux)
		{
			this.add(lbl);
		}

		*/

	}

	public void paint(Graphics g)
    {
        super.paint(g);




        for (Tuyau t : this.ensTuyaux)
        {
            g.setColor(Color.GRAY);

            g.drawLine(t.getCuveOrig().getPosX(), t.getCuveOrig().getPosY(), t.getCuveDest().getPosX() , t.getCuveDest().getPosY()); 

        }
		
		for ( Cuve c : this.ensCuves)
        {
			c.remplir(200.0); // TEST
            g.setColor(c.getCouleur());
            g.fillOval(c.getPosX(), c.getPosY(), (int) (c.getContenu()/5), (int) (c.getContenu()/5));
        }

    }

}