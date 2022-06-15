package sae201.metier;

// Pour les structures de données
import java.util.ArrayList;
import java.util.List;

// Pour la lecture des saisies claviers
import java.util.Scanner;


public class TestEcoulementAuto
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

        double montantEquilibre = 0.0;

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
		alCuves.add( Cuve.creerCuve( 500,  50, 50, "Haut"   ) ); // Cuve A
		alCuves.add( Cuve.creerCuve( 600,  80, 70, "Bas"    ) ); // Cuve B
		alCuves.add( Cuve.creerCuve( 200, 150, 80, "Gauche" ) ); // Cuve C
		alCuves.add( Cuve.creerCuve( 300, 250, 70, "dRoite" ) ); // Cuve D


		// Test 2 //
		alCuves.add( Cuve.creerCuve( 500, 100, 60, "Droite" ) ); // Cuve E
		alCuves.add( Cuve.creerCuve( 600,  70, 60, "Gauche" ) ); // Cuve F
		alCuves.add( Cuve.creerCuve( 700,  90, 60, "bAS"    ) ); // Cuve G

		// Instanciations des tuyaux
		alTuyau.add( Tuyau.creerTuyau( 5 ) ); // Tuyau 0
		alTuyau.add( Tuyau.creerTuyau( 3 ) ); // Tuyau 1


		// Test 2 //
		alTuyau.add( Tuyau.creerTuyau( 4 ) ); // Tuyau 2
		alTuyau.add( Tuyau.creerTuyau( 6 ) ); // Tuyau 3

		// Création des liens
		alTuyau.get(0).setLien( alCuves.get(0), alCuves.get(1) ); // Lier Tuyau 0 a A et B
		alTuyau.get(1).setLien( alCuves.get(2), alCuves.get(3) ); // Lier Tuyau 1 a C et D


		// Test 2 
		alTuyau.get(2).setLien( alCuves.get(4), alCuves.get(5) ); // Lier Tuyau 2 à E et F
		alTuyau.get(3).setLien( alCuves.get(4), alCuves.get(6) ); // Lier Tuyau 3 à E et G

		// Remplissage des cuves
		alCuves.get(0).remplir(213); // Remplir A à 200 
		alCuves.get(3).remplir( 79); // Remplir C à  50

		alCuves.get(5).remplir(100); // remplir F à 100
		alCuves.get(6).remplir(200); // Remplir G à 200

		/* Affichage
		for ( Cuve c : alCuves )
			System.out.println( c );
		*/

		while ( !estEquilibre )
		{
			for( Cuve c : alCuves )
			{
				for ( Tuyau t : alTuyau )
				{
					cuveOrig = t.getCuveOrig();
					cuveDest = t.getCuveDest();

					montantEquilibre = ( cuveOrig.getContenu() + cuveDest.getContenu() ) / 2;

					qteLiqRemplirO = cuveOrig.getContenu() - cuveDest.getContenu();
					qteLiqRemplirD = cuveDest.getContenu() - cuveOrig.getContenu();

					System.out.println( cuveOrig + " |  Montant pour equilibre : " + qteLiqRemplirO );
					System.out.println( cuveDest + " |  Montant pour equilibre : " + qteLiqRemplirD );

					System.out.println(montantEquilibre);


					if ( cuveOrig.getContenu() > cuveDest.getContenu() )
						cuveOrig.couler( cuveDest, t );

					if ( cuveOrig.getContenu() < cuveDest.getContenu() )
						cuveDest.couler( cuveOrig, t );

					if ( qteLiqRemplirO < t.getSection() && qteLiqRemplirO > 0 )
					{
						System.out.println("\n Test condition 1 qteLiqRemplirO :" + qteLiqRemplirO);
						
						if ( cuveOrig.getNbTuyaux() == 1 )
						{
							System.out.println("Flag1");
							for ( int cpt = 0; cpt < cuveOrig.getTuyauxConnectes().size(); cpt++ )							
								cuveOrig.recevoirDe( t.getCuveDest(), cuveDest.getContenu() - montantEquilibre );
							
						}
						else 
						{
							for( Tuyau tu : cuveOrig.getTuyauxConnectes() )
								montantEquilibre += tu.getCuveDest().getContenu();

							montantEquilibre += cuveOrig.getContenu() / cuveOrig.getNbTuyaux() + 1;

							alTransfert = calculerTransfert( alCuves, alTuyau, cuveOrig );
							System.out.println("Flag2");
							for ( int cpt = 0; cpt < cuveOrig.getTuyauxConnectes().size(); cpt++ )
							{
								System.out.println(alTransfert);
								cuveDest.recevoirDe( t.getCuveOrig(), cuveOrig.getContenu() - montantEquilibre );

							}						
								
						}
					}



					if ( qteLiqRemplirD < t.getSection() && qteLiqRemplirD > 0 )
					{
						System.out.println("\n Test condition 1 qteLiqRemplirD :" + qteLiqRemplirD);
						if ( cuveDest.getNbTuyaux() == 1 )
						{
							System.out.println("Flag3");
							for ( int cpt = 0; cpt < cuveDest.getTuyauxConnectes().size(); cpt++ )					
								cuveDest.recevoirDe( t.getCuveOrig(), cuveOrig.getContenu() - montantEquilibre );					
						}
						else 
						{	
							for( Tuyau tu : cuveDest.getTuyauxConnectes() )
								montantEquilibre += tu.getCuveOrig().getContenu() ;

							montantEquilibre += cuveDest.getContenu() / cuveDest.getNbTuyaux() + 1;

							alTransfert = calculerTransfert( alCuves, alTuyau, cuveDest );
							System.out.println("Flag4");
							for ( int cpt = 0; cpt < cuveDest.getTuyauxConnectes().size(); cpt++ )
							{
								System.out.println(alTransfert);
								cuveDest.recevoirDe( t.getCuveOrig(), cuveOrig.getContenu() - montantEquilibre );
							}					
								
								
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