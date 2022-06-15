package sae201.metier;

// Pour les structures de données
import java.util.ArrayList;
import java.util.List;

// Pour la lecture des saisies claviers
import java.util.Scanner;


public class TestEcoulementAuto2
{
	public static void main(String[] args)
	{
		/*-----------------------------------*/
		/*			  Variables              */
		/*-----------------------------------*/

		List<Cuve>   alCuves;
		List<Tuyau>  alTuyau;
		List<Double> alTransfert;

		String idCuveOrig = "";
        String idCuveDest = "";

        double qteLiqRemplirO = 0.0;
        double qteLiqRemplirD = 0.0;

        Cuve   cuveOrig   = null;
        Cuve   cuveDest   = null;

        boolean estEquilibre = false;

        // Scanner pour lire la saisie
        Scanner sc = new Scanner( System.in );

		/*-----------------------------------*/
		/*		    Instructions             */
		/*-----------------------------------*/

		alCuves = new ArrayList<Cuve>();
		alTuyau = new ArrayList<Tuyau>();

		// Instanciations des cuves
		alCuves.add( Cuve.creerCuve( 500,  50, 50, "Haut"   ) );
		alCuves.add( Cuve.creerCuve( 600,  80, 70, "Bas"    ) );
		alCuves.add( Cuve.creerCuve( 200, 150, 80, "Gauche" ) );
		alCuves.add( Cuve.creerCuve( 300, 250, 70, "dRoite" ) );

		// Instanciations des tuyaux
		alTuyau.add( Tuyau.creerTuyau( 5 ) ); // Tuyau 1
		alTuyau.add( Tuyau.creerTuyau( 3 ) ); // Tuyau 2

		// Création des liens
		alTuyau.get(0).setLien( alCuves.get(0), alCuves.get(1) ); // lier tuyau 1 a A et B
		alTuyau.get(1).setLien( alCuves.get(2), alCuves.get(3) ); // lier tuyau 2 a C et D

		// Remplissage des cuves
		alCuves.get(0).remplir(200); // remplir A à 200 
		alCuves.get(2).remplir( 50); // remplir C à 50

		/* Affichage
		for ( Cuve c : alCuves )
			System.out.println( c );
		*/

		while ( !estEquilibre )
		{
			for( Cuve c : alCuves )
			{
				for ( Tuyau t : c.getTuyauxConnectes )
				{
                    if (t.getCuveOrig() == c)
					cuveOrig = t.getCuveOrig();
					cuveDest = t.getCuveDest();

					qteLiqRemplirO = cuveOrig.getContenu() - cuveDest.getContenu();
					qteLiqRemplirD = cuveDest.getContenu() - cuveOrig.getContenu();

					System.out.println( " | " + cuveOrig + "|  Montant pour equilibre : " + qteLiqRemplirO );
					System.out.println( " | " + cuveDest + "|  Montant pour equilibre : " + qteLiqRemplirD );

					if ( cuveOrig.getContenu() > cuveDest.getContenu() )
						cuveOrig.couler( cuveDest, t );

					if ( cuveOrig.getContenu() < cuveDest.getContenu() )
						cuveDest.couler( cuveOrig, t );

					if ( qteLiqRemplirO < t.getSection() )
					{
						System.out.println("Test condition");
						if ( cuveOrig.getNbTuyaux() > 1 )
						{
							System.out.println("Flag1");
							for ( int cpt = 0; cpt < cuveOrig.getTuyauxConnectes().size(); cpt++ )							
								cuveOrig.recevoirDe( t.getCuveDest(), cuveOrig.getCapacite() - cuveOrig.getContenu() );
							
						}
						else 
						{
							alTransfert = calculerTransfert( alCuves, alTuyau, cuveOrig );
							System.out.println("Flag2");
							for ( int cpt = 0; cpt < cuveOrig.getTuyauxConnectes().size(); cpt++ )							
								cuveOrig.recevoirDe( t.getCuveDest(), alTransfert.get( cpt ) );
						}
						

					}

					if ( qteLiqRemplirD < t.getSection() )
					{
						if ( cuveDest.getNbTuyaux() > 1 )
						{
							System.out.println("Flag3");
							for ( int cpt = 0; cpt < cuveDest.getTuyauxConnectes().size(); cpt++ )							
								cuveDest.recevoirDe( t.getCuveOrig(), cuveDest.getCapacite() - cuveDest.getContenu() );
							
						}
						else 
						{
							alTransfert = calculerTransfert( alCuves, alTuyau, cuveDest );
							System.out.println("Flag4");
							for ( int cpt = 0; cpt < cuveDest.getTuyauxConnectes().size(); cpt++ )							
								cuveDest.recevoirDe( t.getCuveOrig(), alTransfert.get( cpt ) );
						}
					}

					sc.nextLine();
				}
			}			
		}		
	}

	public static List<Double> calculerTransfert( List<Cuve> alCuves, List<Tuyau> alTuyau, Cuve cuvePara )
	{
		/*------------------------*/
		/*	     Variables        */
		/*------------------------*/

		Cuve          cuveReceveur = cuvePara;	
		List<Integer> alSection    = new ArrayList<Integer>();
		List<Double>  alTransfert  = new ArrayList<Double>();
		double        contenu      = cuveReceveur.getCapacite() - cuveReceveur.getContenu();
		
		/*------------------------*/
		/*		Instructions      */
		/*------------------------*/

		for ( Tuyau t : cuveReceveur.getTuyauxConnectes() )
			alSection.add( (int) t.getSection() );


		for ( int cpt = 0; cpt < cuveReceveur.getNbTuyaux(); cpt++ )
			alTransfert.add( (double) ( contenu * alSection.get( cpt ) ) / 10 );

		return alTransfert;			
	}
}