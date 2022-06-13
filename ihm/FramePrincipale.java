package sae201.ihm;

import javax.swing.JFrame;

import java.util.ArrayList;
import sae201.Controleur;
import sae201.metier.*;

public class FramePrincipale extends JFrame
{
	private Controleur        ctrl;
	private PanelCuves        panelCuves;
    private ArrayList<Cuve>   ensCuves;  
    private ArrayList<Tuyau>  ensTuyaux;

	public FramePrincipale(Controleur ctrl, ArrayList<Cuve> ensCuves, ArrayList<Tuyau> ensTuyaux)
	{
		this.ctrl       = ctrl;
        this.ensCuves   = ensCuves;
        this.ensTuyaux  = ensTuyaux;
        
		this.setTitle    ( "Affichage du reseaux de Cuves " );
        this.setSize(1000, 500);
        //this.pack();

		this.setVisible(true);

		this.panelCuves = new PanelCuves(this.ctrl, this.ensCuves,  this.ensTuyaux);
		this.add(this.panelCuves);
	}

    public static void main(String[] args)
    {
       // new FramePrincipale(ctrl, ensCuves, ensTuyaux)
    }
} 