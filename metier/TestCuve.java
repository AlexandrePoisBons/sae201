package sae201.metier;

import java.io.FileOutputStream;
// Pour l'écriture dans le fichier texte
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// EN cas d'encodage en utf-8 import cette classe :
// import java.io.OutputStreamWriter;

public class TestCuve
{
	public static void main(String[] args)
	{
		/*-----------------------------*/
		/*			Variables 		   */
		/*-----------------------------*/
		List<Cuve> ensCuves = new ArrayList<Cuve>();
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




		//------------------------------- Pour l'écriture dans le fichier texte ----------------------------------//
		//      Pour forcer l'encodage (par exemple en utf-8), remplacez l'instanciation de pw par :              //
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie.txt"), "UTF8" )); //
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("resultat.txt") );

			/* Pour l'écriture correcte du .txt */
			for(Cuve c : ensCuves)
				pw.println ( c );

			
			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
}