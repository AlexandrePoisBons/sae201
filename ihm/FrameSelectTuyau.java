package sae201.ihm;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JPanel;

import java.awt.event.*;

import sae201.Controleur;
import sae201.ihm.PanelCreerCuves;
import sae201.ihm.PanelCreerTuyau;

public class FrameSelectTuyau extends JFrame
{
	private Controleur        ctrl;
	private PanelCreerTuyau   panelCreerTuyau;

    private int               nbCuves;    

	public FrameSelectTuyau()//Controleur ctrl)
	{
        this.ctrl = ctrl;

		this.setTitle    ( "Creation de tuyaux" );
		this.setSize(500, 100);
		//this.pack();

		this.setVisible(true);

		this.panelCreerTuyau = new PanelCreerTuyau(this.ctrl);
		this.add(this.panelCreerTuyau);	
	}

    public static void main(String[]args)
    {
        new FrameSelectTuyau()
    }

} 