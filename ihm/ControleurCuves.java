package sae201.ihm;

import java.util.ArrayList;
import sae201.metier.*;
import javax.swing.JFrame;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ControleurCuves
{
    private ArrayList<Cuve>  ensCuves;
    public ArrayList<Tuyau>  ensTuyau; // en public juste pour tester
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

    /* ECRIRE DANS LE RESULTAT.TXT */
    public void ecrire(String format)
    {
        String formatChoisi ="";
        Tuyau [][] matrice = this.metier.creerMatrice(this.ensCuves, this.ensTuyau, this.ensCuves.size());

        switch(format)
        {
            case "Matrice" : formatChoisi = this.metier.afficherMatrice(matrice);
            break;

            case "Matrice Optimisee" : formatChoisi = this.metier.afficherMatriceOpti(matrice);
            break;

            case "Liste d'Adjacence" : formatChoisi = this.metier.afficherListeAdjacence(matrice);
            break;
        }

        try
        {
            PrintWriter pw = new PrintWriter( new FileOutputStream("sae201/metier/resultat.txt") );

            /* Pour l'écriture correcte du .txt */
            pw.println("Cuves");
            for(Cuve c : ensCuves)
                pw.println ( c.getCapacite()+","+
                             c.getPosX()    +","+  
                             c.getPosX()    +","+
                             c.getPosition()
                            );

            pw.println("\nTuyaux");
            for(Tuyau t: ensTuyau)
                pw.println ( t.getSection ()        +","+
                             t.getCuveOrig().getId()+","+
                             t.getCuveDest().getId()
                            ); 

            pw.println("\n"+format);
            pw.println(formatChoisi);

            pw.close();
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public static void main(String[] args)
    {
        new ControleurCuves();
        //new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }
}