package sae201.ihm;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.GridLayout;
import sae201.Controleur;

import sae201.metier.*;

public class FrameTuyaux extends JFrame
{
	private Controleur ctrl;
	private PanelTuyaux   panelInfo;

	public FrameTuyaux(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle    ( "Frame Tuyaux" );
		this.setSize(500, 100);
		//this.pack();

		this.setVisible(true);

		this.panelInfo = new PanelTuyaux(this.ctrl);
		this.add(this.panelInfo);
	}

    public void maj(ArrayList<Tuyau> lstTuyaux) // A CHANGER CREER PANEL A PART//
    {
        this.panelInfo = new PanelTuyaux(this.ctrl);
		
        this.panelInfo.setLayout(new GridLayout(lstTuyaux.size()+1, 2));
        this.panelInfo.add(new JLabel("Cuve Origine", JLabel.CENTER));
        this.panelInfo.add(new JLabel("Cuve Destination", JLabel.CENTER));
        for(int j=0; j< lstTuyaux.size(); j++)
        {
            this.add(new JTextField());
            this.add(new JTextField());
        }

        this.add(this.panelInfo);
    }
} 