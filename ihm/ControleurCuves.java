package sae201.ihm;

import java.util.ArrayList;
import java.util.Collections;
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
    public  ArrayList<Cuve>  ensCuves;  //REMETTRE EN PRIVE APRES TESTS
    public  ArrayList<Tuyau> ensTuyau; // en public juste pour tester
    private JFrame           ihm;
    private Metier           metier;
    private String           choix;
    private int              nbCuves;
    private int              cptEquilibre = 0;
    //private boolean          estEquilibre;
    /*
     *Completer
     * 
     */
    
    /*-------------------------------------------------------------*/
    /*               Constructeur du ControleurCuves               */
    /*-------------------------------------------------------------*/
    public ControleurCuves(String choix)
    {
        this.choix   = choix;
        this.nbCuves = 0;
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
                this.metier = new Metier            (this);
                //generer

                break;
            }

            case "Manuel":
            {
                this.metier = new Metier  (this);
                this.ihm    = new FrameGUI(this);
                break;
            }
        }        
    }
    /*-------------------------------------------------------------*/


    /*--------------------------------------------*/
    /* Ajout d'un ensemble de cuves au controleur */
    /*--------------------------------------------*/
    public void setCuves(ArrayList<Cuve> ensCuves)
    {
        this.ensCuves  = ensCuves;
    }
    /*--------------------------------------------*/


    /*---------------------------------------------*/
    /* Ajout d'un ensemble de tuyaux au controleur */
    /*---------------------------------------------*/
    public void setTuyau(ArrayList<Tuyau> ensTuyau)
    {
        this.ensTuyau  = ensTuyau;
    }
    /*---------------------------------------------*/


    /*------------------------------------------*/
    /* Renvoie une cuve si l'identifiant existe */
    /*------------------------------------------*/
    public Cuve estCuve(char c)
    {
        for (Cuve cuve: this.ensCuves)
            if (cuve.getId() == c )
                return cuve;

        return null;
    }
    /*------------------------------------------*/


    /*---------------------------------------------*/
    /*           Lie au tuyau deux cuves           */
    /*---------------------------------------------*/
    public void setLien(int index, Cuve c1, Cuve c2)
    {
        this.ensTuyau.get(index).setLien(c1, c2);
    }
    /*---------------------------------------------*/


    /*-----------------------------------------------------------*/
    /* Créer une matrice à partir de cuves et tuyaux d'un reseau */
    /*-----------------------------------------------------------*/
    public Tuyau[][] creerMatrice(ArrayList<Cuve> lstCuves, ArrayList<Tuyau> lstTuyaux, int taille)
    {
        return this.metier.creerMatrice(lstCuves, lstTuyaux, taille);
    }
    /*-----------------------------------------------------------*/


    /*---------------------------------------------------------------------------*/
    /* Renvoie une matrice optimisee sous forme textuelle a partir d'une matrice */
    /*---------------------------------------------------------------------------*/
    public String afficherMatriceOpti(Tuyau[][] matrice)
    {
        return this.metier.afficherMatriceOpti(matrice);
    }
    /*---------------------------------------------------------------------------*/


    /*---------------------------------------------------------*/
    /* Génération de la fenêtre graphique correspond au réseau */
    /*---------------------------------------------------------*/
    public void generer() 
    {
        new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }
    /*---------------------------------------------------------*/


    /*------------------------------------------------------*/
    /* Méthode de génération des graphiques depuis les .txt */
    /*------------------------------------------------------*/
    public void creerGraph(String fichier)
    {
        //Initialisation des ensembles de cuves et tuyaux du reseau
        this.ensCuves = new ArrayList<Cuve>();
        this.ensTuyau = new ArrayList<Tuyau>();

        //Initialisation des ensembles de lignes de cuves et tuyaux du fichier
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
        } catch ( IOException e ) { e.printStackTrace(); }

        // Tableau des données separés par un "\n"
        String[] tabData = data.split("\r?\n|\r"); 

        boolean cuveOk  = false;
        boolean tuyauOk = false;
        for (String s:  tabData)
        {
            if (!s.equals("Cuves") && !s.equals("") && !cuveOk)
                if (s.equals("Tuyaux"))
                    cuveOk = true;
                else
                    lignesCuves.add(s);             

            if (cuveOk)
                if (!s.equals("Tuyaux") && !tuyauOk)
                    if (s.equals(""))
                    {
                        tuyauOk = true;
                        break;
                    }
                    else
                        lignesTuyaux.add(s);  
        }

        ArrayList<String[]> tabArgCuves = new ArrayList<String[]>();
        ArrayList<String[]> tabArgTuyau = new ArrayList<String[]>();

        // Pour chaque ligne arguments de cuves les mettre dans un tableau
        for (String arg: lignesCuves)    
           tabArgCuves.add(arg.split(","));

        // Pour chaque ligne arguments de cuves les mettre   dans un tableau
        for (String argT: lignesTuyaux)
            tabArgTuyau.add(argT.split(","));

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
            int  section  = Integer.parseInt(argumentsTuyau[0]          );
            Cuve cuveOrig = this.estCuve    (argumentsTuyau[1].charAt(0));
            Cuve cuveDest = this.estCuve    (argumentsTuyau[2].charAt(0));

            this.ensTuyau.add(Tuyau.creerTuyau( section )                        );
            this.ensTuyau.get(this.ensTuyau.size()-1 ).setLien(cuveOrig, cuveDest);
        }

        // TESTS
        for ( Cuve cu: this.ensCuves )
            System.out.println(cu);

        for ( Tuyau tu: this.ensTuyau )
            System.out.println(tu);

        // puis lance l'affichage du reseau
        this.generer();

    }

    public void trier(ArrayList<Cuve> lstATrier)
    {
        Collections.sort(lstATrier);
    }


    // transfert de la cuve la plus pleine vers ses voisins les plus vides
    ArrayList<Cuve> transmetteurs = new ArrayList<Cuve>();
    boolean estEquilibre = false;
    int totalSection = 0;
    int checkContenu = 0;
    public void transferer(Cuve cuveDepart)
    {
        // si le contenu de la 1ere est le meme que la derniere les autres sont tous egaux donc equilibre 
        if ( (this.ensCuves.get(0).getContenu()) == (this.ensCuves.get(this.ensCuves.size()-1).getContenu()) )
        {
            this.estEquilibre = true;
            System.out.println("Les cuves sont equilibree");
        }

        if (!this.estEquilibre)
        {
            transmetteurs.add(cuveDepart);
            // pour chacun de mes voisins
            
            for (Cuve voisin: cuveDepart.getVoisins())
            {
                transmetteurs.clear();
                totalSection = 0;

                // pour chacun des voisins de mes voisins
                for (Cuve voisin2: voisin.getVoisins() )
                {
                    // je suis le voisin de mon voisin 
                    if (voisin2.equals(cuveDepart))
                    {
                        transmetteurs.add(voisin2);
                        totalSection += voisin.getTuyauEntre(voisin2).getSection();
                    }
                    // si le voisin du voisin que je regarde n'est pas moi
                    else
                    {
                        // et si son contenu et le meme que moi alors transfert
                        if (voisin2.getContenu() == cuveDepart.getContenu())
                        {
                            transmetteurs.add(voisin2);
                            System.out.println(transmetteurs);
                            totalSection += voisin.getTuyauEntre(voisin2).getSection();
                        }
                    }
                }
                
                for (Cuve transmet: transmetteurs)
                {
                    
                    if(voisin.getPlaceLibre() < transmet.getTuyauEntre(voisin).getSection())
                    {
                        //chauqe trasnemtteur ennvoie ca
                        double qte = (transmet.getTuyauEntre(voisin).getSection()*voisin.getPlaceLibre())/totalSection;
                        System.out.println("-------------Flag1----------");
                        System.out.println("transmetteur: " + transmet.getId()+ ", qte = " +qte+", a: "+voisin.getId()+"total section: "+totalSection);
                        voisin.recevoirDe(transmet, qte);
                        System.out.println("contenu "+voisin.getId() + "apres transfert: "+ voisin.getContenu());
                    }
                    else
                    {
                        //cas 'ping-pong'
                        double diff = Math.abs(transmet.getContenu()-voisin.getContenu());
                        if ( diff <= transmet.getTuyauEntre(voisin).getSection())
                        {
                            System.out.println("-------------Flag2----------");
                            voisin.recevoirDe(transmet, diff/2);
                            System.out.println("transmetteur: " + transmet.getId()+ ", a: "+voisin.getId());
                            //estEquilibre = true ;
                        }
                        else
                        {
                        System.out.println("-------------Flag3----------");
                        System.out.println("transmetteur: " + transmet.getId()+ ", voisin (receveur) a: "+voisin.getId());
                        transmet.couler(voisin, transmet.getTuyauEntre(voisin));
                        System.out.println("contenu du receveur ("+voisin.getId() + ") apres transfert: "+ voisin.getContenu());
                        }
                    }
                    
                    
                    
                } 

            }
            this.trier(this.ensCuves);
            //this.transferer(this.ensCuves.get(0));
        }
        for (Cuve c: this.ensCuves)
            System.out.println(c);
               
    }
         
    /*------------------------------------------------------*/
    
 

    // ecrit dans un .txt le contenu du reseaux (cuves, tuyaux, matrice correspondante sous la forme choisie) 
    public void ecrire(String format)
    {
        String formatChoisi = "";
        Tuyau [][] matrice  = this.metier.creerMatrice(this.ensCuves, this.ensTuyau, this.ensCuves.size());

        switch(format)
        {
            case "Matrice" :           formatChoisi = this.metier.afficherMatrice       (matrice);
            break;

            case "Matrice Optimisee" : formatChoisi = this.metier.afficherMatriceOpti   (matrice);
            break;

            case "Liste d'Adjacence" : formatChoisi = this.metier.afficherListeAdjacence(matrice);
            break;
        }

        try
        {
            PrintWriter pw = new PrintWriter( new FileOutputStream("sae201/metier/resultat.txt") );

            /* Pour l'écriture correcte du .txt */
            pw.println( "Cuves" );
            for( Cuve c : ensCuves )
                pw.println ( c.getCapacite() + "," +
                             c.getPosX    () + "," +  
                             c.getPosY    () + "," +
                             c.getPosition()
                            );

            pw.println( "Tuyaux" );
            for( Tuyau t: ensTuyau )
                pw.println ( t.getSection ()         +","+
                             t.getCuveOrig().getId() +","+
                             t.getCuveDest().getId()
                            ); 

            pw.println( "\n" + format );
            pw.println( formatChoisi  );

            pw.close();
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    public void setNbCuves(int nbCuves)
    {
        this.nbCuves = nbCuves;
    }

    public int getNbCuves()
    {
        return this.nbCuves;
    }

    public int getMaxX()
    {
        int x = 0;
        for (Cuve c: this.ensCuves)
        {
            if (c.getPosX() > x)
                x = c.getPosX();
        }
        return x;
    }

    public int getMaxY()
    {
        int y = 0;
        for (Cuve c: this.ensCuves)
        {
            if (c.getPosY() > y)
                y = c.getPosY();
        }
        return y;
    }

    public ArrayList<Cuve> getCuves()
    {
        return this.ensCuves;
    }

    public static void main(String[] args)
    {    
        new ControleurCuves("Manuel");
        //new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }
}

/*

    while (!estEquilibre)
    {
        Cuve cuveActuelle = cuveDepart; // on regarde la cuve actuelle
        this.trier(cuveActuelle.getVoisins());// on trie ses voisins par cuve la + remplie

        ArrayList<Cuve> lstATransferer = new ArrayList<Cuve>();              
        
        for (Cuve cTest: cuveActuelle.getVoisins())
        {
            if (cTest != cuveActuelle.getVoisins().get(0) && cTest.getContenu() == cuveActuelle.getVoisins().get(0).getContenu() )
                estEquilibre = true;
        }
        if (!estEquilibre)                    
            transferer(cuveActuelle, cuveActuelle.getVoisins().get(0));


           /*

    // si la place libre de la cuve est + petite que deux section 
    if(voisin.getPlaceLibre() < Cuve.getTuyauEntre(cuveDepart, voisin2) + Cuve.getTuyauEntre(voisin, voisin2) )

    public ArrayList<Cuve> trier() 
    {
        for (int i = 0; i < this.ensCuves.size(); i++)
        {
            Cuve min = this.ensCuves.get(i);
            int minId = i;
            for (int j = i+1; j < this.ensCuves.size(); j++)
            {
                if (this.ensCuves.get(j).getContenu() > min.getContenu()) {
                    min = this.ensCuves.get(j);
                    minId = j;
                }
            }
            // swapping
            Cuve temp = this.ensCuves.get(i);
            this.ensCuves.set(i, min);
            this.ensCuves.set(minId, temp);
        }
        return this.ensCuves;
    }
*/