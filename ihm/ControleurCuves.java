package sae201.ihm;

import java.util.ArrayList;
import sae201.metier.*;
import javax.swing.JFrame;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ControleurCuves
{
    private ArrayList<Cuve>  ensCuves;
    private ArrayList<Tuyau> ensTuyau;
    private JFrame           ihm;
    private Metier           metier;
    /*
     *Completer
     * 
     */
    
    public ControleurCuves()
    {
        this.ihm = new FrameGUI(this);
        this.metier = new Metier(this);
    }

    public void setCuves(ArrayList<Cuve> ensCuves)
    {
        this.ensCuves  = ensCuves;
    }

    public void setTuyau(ArrayList<Tuyau> ensTuyau)
    {
        this.ensTuyau  = ensTuyau;
    }

    public void generer()
    {
        new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }

    public Cuve estCuve(char c)
    {
        for (Cuve cuve: this.ensCuves)
        {
            if (cuve.getId() == c )
                return cuve;
        }
        return null;
    }

    public void setLien(int index, Cuve c1, Cuve c2)
    {
        this.ensTuyau.get(index).setLien(c1, c2);
    }

    public Tuyau[][] creerMatrice(ArrayList<Cuve> lstCuves, ArrayList<Tuyau> lstTuyaux, int taille)
    {
        return this.metier.creerMatrice(lstCuves, lstTuyaux, taille);
    }

    public String afficherMatriceOpti(Tuyau[][] matrice)
    {
        return this.metier.afficherMatriceOpti(matrice);
    }

    public static void main(String[] args)
    {
        new ControleurCuves();
        //new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }

    public static void ecrire(ArrayList<Cuve> ensCuves, ArrayList<Tuyau> ensTuyau)
    {
        String format = "Matrice";
        try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("sae201/metier/resultat.txt") );

			/* Pour l'écriture correcte du .txt */
			pw.println("Cuves\n");
			for(Cuve c : ensCuves)
				pw.println ( c );

			pw.println("\nTuyaux\n");
			for(Tuyau t: ensTuyau)
				pw.println ( t ); 

			pw.println("\n"+format+"\n");

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}

}