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
		List<Cuve> ensCuves = new ArrayList<Cuve>();
        List<Tuyau> ensTuyau = new ArrayList<Tuyau>();

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
		System.out.print("Combien de Tuyaux ? ");
		int nbTuyaux = sc.nextInt();
		/*---------------------------------------------*/

		/*-------------------------- Création des tuyaux selon l'utilisateur --------------------------*/
		for(int cpt = 0; cpt < nbTuyaux; cpt++)
		{
			System.out.print("Quelle section ?\n");
			ensTuyau.add(Tuyau.creerTuyau(sc.nextInt()));
			// Double sc.nextLine() pour annuler le "\n" du sc.nextInt() précédent, qui comptait comme String position
		}
		/*--------------------------------------------------------------------------------------------*/
		Cuve[] cuveALier= new Cuve[2];
		for (Tuyau t: ensTuyau)
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
			ensTuyau.get(ensTuyau.size()-1).setLien(cuveALier[0], cuveALier[1]);
		}

		for(int cpt = 0; cpt < nbTuyaux; cpt++)
			System.out.println(ensTuyau.get(cpt));


		//------------------------------- Pour l'écriture dans le fichier texte ----------------------------------//
		//      Pour forcer l'encodage (par exemple en utf-8), remplacez l'instanciation de pw par :              //
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie.txt"), "UTF8" )); //
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("sae201/metier/resultat.txt") );

			/* Pour l'écriture correcte du .txt */
			for(Cuve c : ensCuves)
				pw.println ( c );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}
}