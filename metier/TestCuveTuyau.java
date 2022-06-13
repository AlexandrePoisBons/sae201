package sae201.metier;


// Pour l'écriture dans le fichier texte
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

// Pour la lecture des saisies claviers
import java.util.Scanner;

// EN cas d'encodage en utf-8 import cette classe :
// import java.io.OutputStreamWriter;

public class TestCuveTuyau
{

	public static void main(String[] args)
	{
		/*-----------------------------*/
		/*			Variables 		   */
		/*-----------------------------*/
		ArrayList<Cuve>  ensCuves = new ArrayList<Cuve> ();
		ArrayList<Tuyau> ensTuyau = new ArrayList<Tuyau>();
		ArrayList<Tuyau> toRemove = new ArrayList<Tuyau>();

		String format = "";

		Scanner sc  = new Scanner(System.in);
        
        /* Récupération des valeurs des utilisateurs */
		System.out.print("Combien de cuves ? ");
		int nbCuves = sc.nextInt();
		/*---------------------------------------------*/


		/*-------------------------- Création des cuves selon l'utilisateur --------------------------*/
		for(int cpt = 0; cpt < nbCuves; cpt++)
		{
			System.out.print("Quelle capacité | posX | posY | position pour la cuve" + " : " + ( char ) ( 'A' + cpt ) + " ? " + "\n");
			ensCuves.add(Cuve.creerCuve(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextLine() + sc.nextLine()));
			// Double sc.nextLine() pour annuler le "\n" du sc.nextInt() précédent, qui comptait comme String position
		}
		/*--------------------------------------------------------------------------------------------*/

		for(int cpt = 0; cpt < nbCuves; cpt++)
			System.out.println(ensCuves.get(cpt));

        /* Récupération des valeurs des utilisateurs */
		System.out.print("\nCombien de Tuyaux ? ");
		int nbTuyaux = sc.nextInt();
		/*---------------------------------------------*/

		/*-------------------------- Création des tuyaux selon l'utilisateur --------------------------*/
		for(int cpt = 0; cpt < nbTuyaux; cpt++)
		{
			System.out.print("\nQuelle section ?\n");
			ensTuyau.add(Tuyau.creerTuyau(sc.nextInt()));
			// Double sc.nextLine() pour annuler le "\n" du sc.nextInt() précédent, qui comptait comme String position
		}
		/*--------------------------------------------------------------------------------------------*/
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

			//ensTuyau.get(ensTuyau.lastIndexOf(t)).setLien(cuveALier[0], cuveALier[1]); --> lier directement

			// Verifier si le tuyau n'existe pas deja // Erreur ici ou Tuyau.equals
			ensTuyau.get(j).setLien(cuveALier[0], cuveALier[1]);

			toRemove = new ArrayList<Tuyau>();
			boolean alreadySelected = false;
			for (Tuyau t3: ensTuyau)
			{
				for (Tuyau t2: ensTuyau)
				{
					if (t3!= t2 && t3.equals(t2))
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


		// Affichage //
		for (Tuyau tAffiche: ensTuyau)
		{
			System.out.println(tAffiche);
		}

		System.out.println("Quel format voulez vous ?(Matrice, Matrice Optimisee, Liste d'adjacence)\n");
		format = sc.nextLine()+sc.nextLine();
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
				System.out.println("Pas encore disponible");
				break;
			}
		}
		

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