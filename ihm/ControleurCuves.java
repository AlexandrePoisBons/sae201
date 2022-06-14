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
    private String           choix;
    /*
     *Completer
     * 
     */
    
    public ControleurCuves(String choix)
    {
        this.choix = choix;
        switch(this.choix)
        {
            case "Simple":
            {
                ArrayList<String[]> res = lecteurTest.lire("../metier/resultat.txt");
                String tmp ="";
                for(String[]tabString: res)
                {
                    for(String str: tabString)
                    {
                        tmp += str+" ";
                    }
                    tmp += "\n";
                }
                System.out.println(tmp);
                
                //lireFichier(../metier/resultat.txt)
                //puis generer
                break;
            }

            case "Avance":
            {
                this.ihm    = new FrameSelectFichier(this);
                this.metier = new Metier(this);
                //lireFichier(path/to/fichierChoisi.txt)
                //generer
                break;
            }

            case "Manuel":
            {
                this.metier = new Metier(this);
                this.ihm = new FrameGUI(this);
                break;
            }
        }        
    }

    // Ajoute un ensemble de cuves au controleur
    public void setCuves(ArrayList<Cuve> ensCuves)
    {
        this.ensCuves  = ensCuves;
    }

    // Ajoute un ensemble de tuyaux au controleur
    public void setTuyau(ArrayList<Tuyau> ensTuyau)
    {
        this.ensTuyau  = ensTuyau;
    }

    // Renvoie une cuve si l'identifiant existe
    public Cuve estCuve(char c)
    {
        for (Cuve cuve: this.ensCuves)
        {
            if (cuve.getId() == c )
                return cuve;
        }
        return null;
    }

    // lie au tuyau deux cuves 
    public void setLien(int index, Cuve c1, Cuve c2)
    {
        this.ensTuyau.get(index).setLien(c1, c2);
    }

    // cree une matrice a partir de cuves et tuyaux d'un reseau
    public Tuyau[][] creerMatrice(ArrayList<Cuve> lstCuves, ArrayList<Tuyau> lstTuyaux, int taille)
    {
        return this.metier.creerMatrice(lstCuves, lstTuyaux, taille);
    }

    // renvoie une matrice optimisee sous forme textuelle a partir d'une matrice
    public String afficherMatriceOpti(Tuyau[][] matrice)
    {
        return this.metier.afficherMatriceOpti(matrice);
    }

    // generer la fenetre graphique correspondant au reseau
    public void generer() 
    {
        new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }

    public void lireFichier()
    {

    }

    // ecrit dans un .txt le contenu du reseaux (cuves, tuyaux, matrice correspondante sous la forme choisie) 
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
        new ControleurCuves("Avance");
        //new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }
}