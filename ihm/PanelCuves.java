package sae201.ihm;	

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Dimension;

import sae201.Controleur;
import sae201.metier.*;
import java.awt.Color;

public class PanelCuves extends JPanel //implements ActionListener
{
	private ControleurCuves     ctrl;
    private ArrayList<Cuve> 	ensCuves;
    private ArrayList<Tuyau> 	ensTuyaux;

	private ArrayList<JLabel> lstLblCuves;
	private ArrayList<JLabel> lstLblTuyaux;

	private JLabel		lblInfo;

	public PanelCuves( ControleurCuves ctrl, ArrayList<Cuve> ensCuves, ArrayList<Tuyau> ensTuyaux)
	{
		this.ctrl     	 	= ctrl;
        this.ensCuves  		= ensCuves;
        this.ensTuyaux 		= ensTuyaux;
		this.lstLblCuves 	= new ArrayList<JLabel>();
		this.lstLblTuyaux 	= new ArrayList<JLabel>();
		this.lstLblCuves 	= new ArrayList<JLabel>();

		//Creation des composants//
		
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
			this.lstLblCuves.add(new JLabel(c.getId()+"\n"+c.getContenu()+"/"+c.getCapacite()));
			Dimension dimLbl = this.lstLblCuves.get(0).getPreferredSize();
			// Dernier label de la liste //
			JLabel lblActuel = this.lstLblCuves.get(this.lstLblCuves.size()-1);
			switch(c.getPosition())
			{
				case "Haut": 	lblActuel.setBounds(c.getPosX(), c.getPosY()-(int) (c.getContenu()/5)-30, dimLbl.width, dimLbl.height); break;
				case "Bas":		lblActuel.setBounds(c.getPosX(), c.getPosY()-(int) (c.getContenu()/5)+30, dimLbl.width, dimLbl.height); break;
				case "Gauche":	lblActuel.setBounds(c.getPosX()-(int) (c.getContenu()/5)-30, c.getPosY(), dimLbl.width, dimLbl.height); break;
				case "Droite":	lblActuel.setBounds(c.getPosX()-(int) (c.getContenu()/5)-30, c.getPosY(), dimLbl.width, dimLbl.height); break;
			}
			this.add(lblActuel);
			c.remplir(200.0); // TEST
            g.setColor(c.getCouleur());
            g.fillOval(c.getPosX()-(int) (c.getContenu()/10), c.getPosY()-(int) (c.getContenu()/10), (int) (c.getContenu()/5), (int) (c.getContenu()/5));
        }
 
    }

}