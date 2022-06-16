package sae201.metier;

// Pour l'écriture dans le fichier texte
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

// Pour la lecture des saisies claviers
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

// EN cas d'encodage en utf-8 import cette classe :
// import java.io.OutputStreamWriter;

public class TestCuveTuyau
{

	public static void main(String[] args)
	{
		/*-----------------------------*/
		/*			Variables 		   */
		/*-----------------------------*/
		ArrayList<Cuve>  ensCuves = new ArrayList<Cuve> (); // --> ensemble des cuves crees
		ArrayList<Tuyau> ensTuyau = new ArrayList<Tuyau>(); // --> ensemble des tuyaux crees
		ArrayList<Tuyau> toRemove = new ArrayList<Tuyau>(); // --> tuyaux incorrects a supprimer
		String format = "";

		int nbCuves     = 0;
		int nbTuyaux    = 0;
		int nbTransfert = 0;

		String idCuveOrig = "";
		String idCuveDest = "";

		Scanner sc  = new Scanner(System.in);
        
        /* Récupération des valeurs des utilisateurs */
		System.out.print("Combien de cuves ? ");
		nbCuves = sc.nextInt();
		/*---------------------------------------------*/

		/*--------------------------------------------------------------------------------------------*/
		/*                           Création des cuves selon l'utilisateur                           */
		/*--------------------------------------------------------------------------------------------*/
		for(int cpt = 0; cpt < nbCuves; cpt++)
		{
			boolean positionTaken = false;

			System.out.print("Quelle capacité | posX | posY | position pour la cuve" + " : " + ( char ) ( 'A' + cpt ) + " ? " + "\n");
			int capacite 	= sc.nextInt();
			int posX 		= sc.nextInt();
			int posY 	 	= sc.nextInt();
			String position = sc.nextLine() + sc.nextLine(); // Double sc.nextLine() pour annuler le "\n" du sc.nextInt() précédent, qui comptait comme String position

			// Verification de la position //
			for (Cuve c: ensCuves)						
			{
				if (c.getPosX() == posX && c.getPosY() == posY)
				{
					positionTaken = true;
					cpt--;
					System.out.println("Position (" + posX + ", " + posY + ") deja occupee veuillez ressayer avec une position differente\n");
					break;
					
				}
			}
			if(!positionTaken)
			{
				Cuve verif;

				verif = Cuve.creerCuve(capacite, posX, posY, position);

				if ( verif != null )
					ensCuves.add(verif);
				
				if ( verif == null )
					nbCuves--;
			}
			
		}
		/*--------------------------------------------------------------------------------------------*/

		for(int cpt = 0; cpt < nbCuves; cpt++)
			System.out.println(ensCuves.get(cpt).toString());


		/*-----------------------------------------------------------*/
        /* Récupération des valeurs des utilisateurs pour les tuyaux */
		/*-----------------------------------------------------------*/

		System.out.print("\nCombien de Tuyaux voulez-vous créer ? ");
		nbTuyaux = sc.nextInt();



		/*-----------------------------------------*/
		/* Création des tuyaux selon l'utilisateur */
		/*-----------------------------------------*/

		for(int cpt = 0; cpt < nbTuyaux; cpt++)
		{
			System.out.print("\nQuelle valeurs voulez-vous donner à la section ?\n");
			ensTuyau.add(Tuyau.creerTuyau(sc.nextInt()));
		}


        /*----------------------------------------------*/
		/*            Remplissage des cuves             */
        /*----------------------------------------------*/

		for (Cuve c : ensCuves)
			if ( c != null )
			{
				System.out.println("Combien voulez vous remplir la cuve ? " + c.getId());
				c.remplir((double) sc.nextInt());
			}
			else
				System.out.println("La cuve n'existe pas");
		

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

			ensTuyau.get(j).setLien(cuveALier[0], cuveALier[1]);
			toRemove = new ArrayList<Tuyau>();

			boolean alreadySelected = false;
			for (Tuyau t3: ensTuyau)
				for (Tuyau t2: ensTuyau)
					if (t3 != t2 && t3.equals(t2))
					{
						for (Tuyau tRemove : toRemove)
							if (tRemove.equals(t3))
							{
								alreadySelected = true;
								break;
							}							

						if (!alreadySelected)
							toRemove.add(ensTuyau.get(ensTuyau.lastIndexOf(t3)));
					}

			for (Tuyau tRemove : toRemove)
				ensTuyau.remove(tRemove);	
		}


        /*--------------------------------------------------------*/
		/*          Demande du nombre de transfert voulu          */
        /*--------------------------------------------------------*/

		System.out.println("Combien de transfert voulez vous réaliser ? ");
		nbTransfert = sc.nextInt();	


		for ( int cpt = 0; cpt < nbTransfert; cpt++ )
		{
			System.out.println( "A quelle cuve voulez-vous transferer ? Cuve Origine : "    );
			idCuveOrig = sc.nextLine() + sc.nextLine();

			System.out.println( "A quelle cuve voulez-vous transferer ? Cuve Destination : ");
			idCuveDest = sc.nextLine();

			System.out.println("ID Cuve Origine     : |" + idCuveOrig + "|");
			System.out.println("ID Cuve Destination : |" + idCuveDest + "|");

			Cuve cuveOrig = null;
			Cuve cuveDest = null;
			
			Tuyau t = null;
			
			for ( Cuve c : ensCuves )
			{
				if ( c != null )
				{
					if ( c.getId() == idCuveOrig.charAt(0) ) // on cherche la cuve qui correspond a notre id d'origine
					{
						for ( Tuyau tConnecte : c.getTuyauxConnectes() ) // pour tous les tuyaux connecte a cette origine A --> B B -->A 
						{ // si (l'origine == Id Origine && la destnation == Id destination ) ou l'id Origine == destination && id destination == cuve de destination
							
							if (tConnecte.getCuveOrig().getId() == idCuveOrig.charAt(0) && tConnecte.getCuveDest().getId() == idCuveDest.charAt(0))

							// Si le tuyau connecte a moi est connecte a la destination ou si la destination est connecte a moi et si la destination n'est pas moi	
							{
								System.out.println("-----Avant Transfert-----\n"																					);
								System.out.println("Cuve Origine : "          + c.getId() +", Contenu " + c.getContenu()											);
								System.out.println("    --Verifications-- "																							);										
								System.out.println("    Cuve Origine est vide ? " + c.estVide()																			);			
								System.out.println("    Cuve dest est pleine ? "  + tConnecte.getCuveDest().estPleine()													);
								System.out.println("    ----------------- "																							);										
								System.out.println("Cuve Dest : " + tConnecte.getCuveDest().getId() + ", Contenu " + tConnecte.getCuveDest().getContenu() + "\n\n" 	);
								
								System.out.println(( c.couler( tConnecte.getCuveDest(), tConnecte) ));

								System.out.println("\n\n-----Apres Transfert-----\n"																				);
								System.out.println("Cuve Origine : " + c.getId()                       + ", Contenu " + c.getContenu()								);
								System.out.println("Cuve Dest : "    + tConnecte.getCuveDest().getId() + ", Contenu " + tConnecte.getCuveDest().getContenu()		);
							}
		
							if (tConnecte.getCuveDest().getId() == idCuveOrig.charAt(0) && (tConnecte.getCuveOrig().getId() == idCuveDest.charAt(0)))
							{
								System.out.println("-----Avant Transfert-----\n"																					);
								System.out.println("Cuve Origine : "          + c.getId() + ", Contenu " + c.getContenu()											);
								System.out.println("    --Verifications-- "																							);										
								System.out.println("     Cuve Origine est vide ? "  + c.estVide()																		);
								System.out.println("    Cuve dest est pleine ? "   + tConnecte.getCuveDest().estPleine()												);
								System.out.println("    ----------------- "																							);										
								System.out.println("Cuve Dest : " + tConnecte.getCuveOrig().getId() + ", Contenu " + tConnecte.getCuveOrig().getContenu() + "\n\n"	);
								
								
								System.out.println(( c.couler( tConnecte.getCuveOrig(), tConnecte) ));

								System.out.println("\n\n-----Apres Transfert-----\n"																				);
								System.out.println("Cuve Origine : " + c.getId()                       + ", Contenu " + c.getContenu()								);
								System.out.println("Cuve Dest : "    + tConnecte.getCuveOrig().getId() + ", Contenu " + tConnecte.getCuveOrig().getContenu()		);
							}
						}
					}
				}
			}

		}


		/*----------Affichage----------*/
		for ( Tuyau tAffiche: ensTuyau )
			System.out.println(tAffiche);
		/*-----------------------------*/


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