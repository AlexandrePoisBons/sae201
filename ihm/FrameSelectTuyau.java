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
	private ControleurCuves ctrl;
	private PanelCreerTuyau panelCreerTuyau;
    private int             nbTuyaux;   

	public FrameSelectTuyau(ControleurCuves ctrl, int nbTuyaux)
	{
        this.ctrl = ctrl;
        this.panelCreerTuyau = new PanelCreerTuyau(this, this.ctrl, nbTuyaux);

		this.setTitle("Creation de tuyaux");
		this.setSize (500, 200);

		this.add(this.panelCreerTuyau);	

		/*--------------------------------------------------*/
		/*               Concernant la JFrame               */
		/*--------------------------------------------------*/
	//	this.setExtendedState(JFrame.MAXIMIZED_BOTH );
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		/*---------------------------------------------------*/
	}

} 