package sae201.ihm;

import java.util.ArrayList;
import sae201.metier.*;
import javax.swing.JFrame;

import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class ControleurCuves
{
    private ArrayList<Cuve>  ensCuves;
    public  ArrayList<Tuyau> ensTuyau; // en public juste pour tester
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
                this.creerGraph("sae201/metier/resultat.txt");
                break;
            }

            case "Avance":
            {
                this.ihm    = new FrameSelectFichier(this);
                this.metier = new Metier(this);
                // lireFichier();


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

    // Generation graphique depuis .txt
    public void creerGraph(String fichier)
    {
        //initialisation des ensemble de cuves et tuyaux du reseau
        this.ensCuves = new ArrayList<Cuve>();
        this.ensTuyau = new ArrayList<Tuyau>();

        //initialisation des ensemble de ligne de cuves et tuyaux du fichier
        ArrayList<String> lignesCuves  = new ArrayList<String>();
        ArrayList<String> lignesTuyaux = new ArrayList<String>();

        String data = "";
        try(FileReader fr = new FileReader(fichier))
        {
            int i = fr.read();
            char temp = ' ';
            while ( i != -1 )
            {
                temp = (char)i;
                data += temp;
                i = fr.read();
            }
        } catch ( IOException e )
        {
            e.printStackTrace();
        }

        String[] tabData = data.split("\r?\n|\r"); // tab des donnees separees par \n

        boolean cuveOk  = false;
        boolean tuyauOk = false;
        for (String s:  tabData)
        {
            if (!s.equals("Cuves") && !s.equals("") && !cuveOk)
            {
                if (s.equals("Tuyaux"))
                {
                    cuveOk = true;
                }
                else
                {
                    lignesCuves.add(s);
                }                
                
            }
            if (cuveOk)
            {
                if (!s.equals("Tuyaux") && !tuyauOk)
                {
                    if (s.equals(""))
                    {
                        tuyauOk = true;
                    }
                    else
                    {
                        lignesTuyaux.add(s);
                    }                    
                }
            }            
        }

        ArrayList<String[]> tabArgCuves = new ArrayList<String[]>();
        ArrayList<String[]> tabArgTuyau = new ArrayList<String[]>();
        for (String arg: lignesCuves)
        {
           // pour chaque ligne argument de cuve les mettre dans un tableau 
           tabArgCuves.add(arg.split(","));
        }

        for (String argT: lignesTuyaux)
        {
            // pour chaque ligne argument de cuve les mettre dans un tableau 
            tabArgTuyau.add(argT.split(","));
        }

        for(String[] argumentsCuve: tabArgCuves)
        {
            int capacite    = Integer.parseInt(argumentsCuve[0]);
            int posX        = Integer.parseInt(argumentsCuve[1]);
            int posY        = Integer.parseInt(argumentsCuve[2]);
            String position = argumentsCuve[3];

            this.ensCuves.add(Cuve.creerCuve(capacite, posX, posY, position));
        }

        for(String[] argumentsTuyau: tabArgTuyau)
        {
            int  section         = Integer.parseInt(argumentsTuyau[0]);
            Cuve cuveOrig        = this.estCuve    (argumentsTuyau[1].charAt(0));
            Cuve cuveDest        = this.estCuve    (argumentsTuyau[2].charAt(0));

            this.ensTuyau.add(Tuyau.creerTuyau(section));
            this.ensTuyau.get(this.ensTuyau.size()-1).setLien(cuveOrig, cuveDest);
        }

        // puis lance l'affichage du reseau
        this.generer();
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

            pw.println("Tuyaux");
            for(Tuyau t: ensTuyau)
                pw.println ( t.getSection ()        +","+
                             t.getCuveOrig().getId()+","+
                             t.getCuveDest().getId()
                            ); 

            pw.println(""+format);
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