package sae201.metier;

import sae201.ihm.ControleurCuves;


// Pour l'écriture dans le fichier texte
import java.io.FileOutputStream;
import java.io.PrintWriter;

// Pour les structures de données
import java.util.ArrayList;
import java.util.List;

import java.awt.Point;

// Pour la lecture des saisies claviers
import java.util.Scanner;

public class Application1
{
    public static void main(String[] args)
    {
        if ( args.length == 1)
            switch( args[0].toUpperCase() )
            {
                case "GUI" : 
                {
                    System.out.println("Affichage en mode graphique :\n"); 
                    new ControleurCuves();
                    // System.exit(0);
                    break;
                }

                case "CUI" : 
                {
                    System.out.println("Affichage en mode console   :\n");
                    modeConsole();
                    break;
                }
            }
    }

    public static void modeConsole()
    {
        /*-----------------------------*/
        /*          Variables          */
        /*-----------------------------*/
        
        ArrayList<Cuve>  ensCuves = new ArrayList<Cuve> (); // --> ensemble des cuves crees
        ArrayList<Tuyau> ensTuyau = new ArrayList<Tuyau>(); // --> ensemble des tuyaux crees
        ArrayList<Tuyau> toRemove = new ArrayList<Tuyau>(); // --> tuyaux incorrects a supprimer
        String format = "";

        // Variavles pour la saisie utilisateurs
        int    nbCuves    =  0; 
        int    nbTuyaux   =  0; 
        String idCuveOrig = "";
        String idCuveDest = "";

        // Scanner pour lire la saisie
        Scanner sc  = new Scanner(System.in);

        /*-----------------------------*/
        /*        Instructions         */
        /*-----------------------------*/
        
        /* Récupération des valeurs des utilisateurs */
        System.out.print("Combien de cuves ? ");
        nbCuves = sc.nextInt();

        for(int cpt = 0; cpt < nbCuves; cpt++)
        {
            boolean positionTaken = false;

            System.out.print("Quelle capacité | posX | posY | position pour la cuve" + " : " + ( char ) ( 'A' + cpt ) + " ? " + "\n");
            int capacite    = sc.nextInt();
            int posX        = sc.nextInt();
            int posY        = sc.nextInt();
            String position = sc.nextLine() + sc.nextLine(); // Double sc.nextLine() pour annuler le "\n" du sc.nextInt() précédent, qui comptait comme String position

            // Verification de la position //
            for (Cuve c: ensCuves)                      
            {
                if ( c.getPosX() == posX && c.getPosY() == posY )
                {
                    positionTaken = true;
                    cpt--;
                    System.out.println("Position (" + posX + ", " + posY + ") déjà occupée veuillez réessayer avec une position différente\n");
                    break;
                    
                }
            }
            if(!positionTaken)
                ensCuves.add(Cuve.creerCuve(capacite, posX, posY, position));
            
        }
        /*--------------------------------------------------------------------------------------------*/

        for(int cpt = 0; cpt < nbCuves; cpt++)
            System.out.println(ensCuves.get(cpt));


        /*--------------------------------------------------------------------------------------------*/
        /*                Récupération des valeurs des utilisateurs pour les tuyaux                   */
        /*--------------------------------------------------------------------------------------------*/
        System.out.print("\nCombien de Tuyaux voulez-vous créer ? ");
        nbTuyaux = sc.nextInt();
        /*---------------------------------------------------------------------------------------------*/



        /*----------------------------------------------------------------------------------*/
        /*                     Création des tuyaux selon l'utilisateur                      */
        /*----------------------------------------------------------------------------------*/
        for(int cpt = 0; cpt < nbTuyaux; cpt++)
        {
            System.out.print("\nQuelle section ?\n");
            ensTuyau.add(Tuyau.creerTuyau(sc.nextInt()));
        }
        /*----------------------------------------------------------------------------------*/



        /*---------------------------------------------------*/
        /*      Création de la liaison entre deux cuves      */
        /*---------------------------------------------------*/
        Cuve[] cuveALier= new Cuve[2];
        for (int j=0; j< nbTuyaux; j++)
        {
            System.out.print("Quelles cuves voulez vous relier ?\n");
            String stringCuve1 = sc.next();
            String stringCuve2 = sc.next();
            for (Cuve c :ensCuves)
            {
                if (c.getId() == stringCuve1.charAt(0)) 
                    cuveALier[0] =  c;
                if (c.getId() == stringCuve2.charAt(0))
                    cuveALier[1] = c;
            }

            // Ligne de code pour faire une liaison directe //
            //ensTuyau.get(ensTuyau.lastIndexOf(t)).setLien(cuveALier[0], cuveALier[1]);


            /*------------------------------------------------*/
            /*   Vérification si le tuyau n'existe pas déjà   */
            /*------------------------------------------------*/
            // Erreur ici ou Tuyau.equals
            ensTuyau.get(j).setLien(cuveALier[0], cuveALier[1]);

            toRemove = new ArrayList<Tuyau>();
            boolean alreadySelected = false;
            for (Tuyau t3: ensTuyau)
            {
                for (Tuyau t2: ensTuyau)
                {
                    if (t3 != t2 && t3.equals(t2))
                    {
                        for (Tuyau tRemove : toRemove)
                        {
                            if (tRemove.equals(t3))
                            {
                                alreadySelected = true;
                                break;
                            }                           
                        }
                        if (!alreadySelected)
                            toRemove.add(ensTuyau.get(ensTuyau.lastIndexOf(t3)));
                    }
                }
            }
            for (Tuyau tRemove : toRemove)
            {
                ensTuyau.remove(tRemove);
            }
                        
        }
        /*---------------------------------------------------*/ 




        /*--------------------Création de la matrice / liste voulu--------------------*/
        System.out.println("Quel format voulez vous ?(Matrice, Matrice Optimisee, Liste d'adjacence)\n");
        format = sc.nextLine() + sc.nextLine();
        switch(format)
        {
            case "Matrice":
            {
                System.out.println((Metier.afficherMatrice(Metier.creerMatrice(ensCuves, ensTuyau, nbCuves))));
                format = "Matrice\n"+(Metier.afficherMatrice(Metier.creerMatrice(ensCuves, ensTuyau, nbCuves)));
                break;
            }

            case "Matrice Optimisee":
            {
                System.out.println(Metier.afficherMatriceOpti(Metier.creerMatrice(ensCuves, ensTuyau, nbCuves)));
                format = "Matrice Opti\n"+(Metier.afficherMatriceOpti(Metier.creerMatrice(ensCuves, ensTuyau, nbCuves)));
                break;
            }

            case "Liste d'adjacence":
            {
                System.out.println(Metier.afficherListeAdjacence(Metier.creerMatrice(ensCuves, ensTuyau, nbCuves)));
                format = "Liste d'adjacence\n"+(Metier.afficherListeAdjacence(Metier.creerMatrice(ensCuves, ensTuyau, nbCuves)));
                break;
            }
        }
        /*----------------------------------------------------------------------------*/


        //------------------------------- Pour l'écriture dans le fichier texte ----------------------------------//
        //      Pour forcer l'encodage (par exemple en utf-8), remplacez l'instanciation de pw par :              //
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie.txt"), "UTF8" )); //
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