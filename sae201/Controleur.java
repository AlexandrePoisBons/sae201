package sae201;

import metier.Metier;
import sae201.ihm.FrameGUI;

public class Controleur
{
	private Metier   metier;
	private FrameGUI ihm;

	public Controleur()
	{
		this.metier = new Metier();
		this.ihm    = new FrameGUI(this);
	}

	public static void main (String[] args)
	{
		new Controleur();
	}
}