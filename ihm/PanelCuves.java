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

		this.setLayout(null);
		double remplir = 50.0;
		for (Cuve c  :this.ensCuves)
		{
			c.remplir(remplir+=250); // TEST

			this.lstLblCuves.add(new JLabel("<html>"+c.getId()+"<br>"+c.getContenu()+"/"+c.getCapacite()+"</html>", JLabel.CENTER));
			Dimension dimLbl = this.lstLblCuves.get(0).getPreferredSize();
			//Dernier label de la liste //
			JLabel lblActuel = this.lstLblCuves.get(this.lstLblCuves.size()-1);
			
			switch(c.getPosition())
			{
				case "Haut": 	lblActuel.setBounds(c.getPosX()-(c.getCapacite()/10), c.getPosY()-(int) (c.getCapacite()/5)-10,     dimLbl.width, dimLbl.height); break;
				case "Bas":		lblActuel.setBounds(c.getPosX()-(c.getCapacite()/10), c.getPosY()+(int) (c.getCapacite()/10)+5,    dimLbl.width, dimLbl.height); break;
				case "Gauche":	lblActuel.setBounds(c.getPosX()-(int) (c.getCapacite()/5)-35, c.getPosY()-5, 					   dimLbl.width, dimLbl.height); break;
				case "Droite":	lblActuel.setBounds(c.getPosX()+(int) (c.getCapacite()/5)-05, c.getPosY()-5, 					   dimLbl.width, dimLbl.height); break;
			}
			
			this.add(lblActuel);
			
		}
		

		//Creation des composants//
		
	}

	public void paint(Graphics g)
    {
        super.paint(g);
		Graphics2D g2D = (Graphics2D) g;

		if (this.ensTuyaux.size() != 0)
		{
			for (Tuyau t : this.ensTuyaux)
			{	
				//System.out.println("panelCuve ->" +t);		
				g.setColor(Color.GRAY);
				g2D.setStroke(new BasicStroke(t.getSection()/2));
				g2D.drawLine(t.getCuveOrig().getPosX(), t.getCuveOrig().getPosY(), t.getCuveDest().getPosX() , t.getCuveDest().getPosY()); 

			}
		}

		for ( Cuve c : this.ensCuves)
        {		
			//System.out.println("panel cuves --> " + c);	
			g.setColor(c.getCouleur());
            g.fillOval(c.getPosX()-(int) (c.getCapacite()/10), c.getPosY()-(int) (c.getCapacite()/10), (int) (c.getCapacite()/5), (int) (c.getCapacite()/5));
			g.setColor(Color.BLACK);
            g.drawOval(c.getPosX()-(int) (c.getCapacite()/10), c.getPosY()-(int) (c.getCapacite()/10), (int) (c.getCapacite()/5), (int) (c.getCapacite()/5));
        }

    }

}