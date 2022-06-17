package sae201.ihm;	

import javax.swing.JPanel;
import java.util.Collections;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.Dimension;

import sae201.metier.*;
import java.awt.Color;

public class PanelCuves extends JPanel implements Scrollable
{
	private ControleurCuves   ctrl;
    private ArrayList<Cuve>   ensCuves;
    private ArrayList<Tuyau>  ensTuyaux;
	private ArrayList<JLabel> lstLblCuves;
	private ArrayList<JLabel> lstLblTuyaux;
	private JLabel		      lblInfo;

	public PanelCuves( ControleurCuves ctrl, ArrayList<Cuve> ensCuves, ArrayList<Tuyau> ensTuyaux)
	{
		this.ctrl     	  = ctrl;
        this.ensCuves  	  = ensCuves;
        this.ensTuyaux 	  = ensTuyaux;
		this.lstLblCuves  = new ArrayList<JLabel>();
		this.lstLblTuyaux = new ArrayList<JLabel>();
		this.lstLblCuves  = new ArrayList<JLabel>();

		this.setLayout(null);
		for (Cuve c  :this.ensCuves)
		{
			if (c != null)
			{
				this.lstLblCuves.add(new JLabel("<html>"+c.getId()+"<br>"+"00"+String.format("%4.2f", c.getContenu())+"/"+c.getCapacite()+"</html>", JLabel.CENTER));
				Dimension dimLbl = this.lstLblCuves.get(0).getPreferredSize();
				//Dernier label de la liste //
				JLabel lblActuel = this.lstLblCuves.get(this.ensCuves.indexOf(c));
				
				switch(c.getPosition())
				{
					case "Haut": 	lblActuel.setBounds(c.getPosX()-(c.getCapacite()/10), c.getPosY()-(int) (c.getCapacite()/5)-5,     dimLbl.width, dimLbl.height); break;
					case "Bas":		lblActuel.setBounds(c.getPosX()-(c.getCapacite()/10), c.getPosY()+(int) (c.getCapacite()/10)+5,    dimLbl.width, dimLbl.height); break;
					case "Gauche":	lblActuel.setBounds(c.getPosX()-(int) (c.getCapacite()/5)-35, c.getPosY()-10, 					   dimLbl.width, dimLbl.height); break;
					case "Droite":	lblActuel.setBounds(c.getPosX()+(int) (c.getCapacite()/5)-05, c.getPosY()-10, 					   dimLbl.width, dimLbl.height); break;
				}
				
				this.add(lblActuel);

			}		
		}

		for (Tuyau t : this.ensTuyaux)
			{	
			// Positionnement du Label
			int posXLblSection = Math.abs(t.getCuveOrig().getPosX() + t.getCuveDest().getPosX())/2;
			int posYLblSection = Math.abs(t.getCuveOrig().getPosY() + t.getCuveDest().getPosY())/2;
			this.lstLblTuyaux.add(new JLabel(""+t.getSection()));
			
			Dimension dimLblTuyaux = this.lstLblTuyaux.get(0).getPreferredSize();
			JLabel lblActuelTuyau = this.lstLblTuyaux.get(this.lstLblTuyaux.size()-1);

			lblActuelTuyau.setBounds(posXLblSection-5, posYLblSection+5, dimLblTuyaux.width, dimLblTuyaux.height);
			this.add(lblActuelTuyau);
			}
	}
	public void paint(Graphics g)
    {
        super.paint(g);
		Graphics2D g2D = (Graphics2D) g;

		if (this.ensTuyaux.size() != 0)
			for (Tuyau t : this.ensTuyaux)
			{		
				g.setColor(Color.GRAY);
				g2D.setStroke(new BasicStroke(t.getSection()/2));
				g2D.drawLine(t.getCuveOrig().getPosX(), t.getCuveOrig().getPosY(), t.getCuveDest().getPosX() , t.getCuveDest().getPosY());
			}

		for ( Cuve c : this.ensCuves)
        {	
			this.lstLblCuves.get(c.getId()-65).setText("<html>"+c.getId()+"<br>"+String.format("%4.2f", c.getContenu())+"/"+c.getCapacite()+"   </html>");	
			g.setColor(c.getCouleur());
            g.fillOval(c.getPosX()-(int) (c.getCapacite()/10), c.getPosY()-(int) (c.getCapacite()/10), (int) (c.getCapacite()/5), (int) (c.getCapacite()/5));
			g.setColor(Color.BLACK);
            g.drawOval(c.getPosX()-(int) (c.getCapacite()/10), c.getPosY()-(int) (c.getCapacite()/10), (int) (c.getCapacite()/5), (int) (c.getCapacite()/5));
        }

    }

	public Dimension getPreferredSize() {
		return new Dimension(this.ctrl.getMaxX() + 200, this.ctrl.getMaxY() + 200);
	}
	
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(1280, 720);
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 128;
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 128;
	}

	public boolean getScrollableTracksViewportWidth() {
		return getPreferredSize().width <= getParent().getSize().width;
	}

	public boolean getScrollableTracksViewportHeight() {
		return getPreferredSize().height <= getParent().getSize().height;
	}

}