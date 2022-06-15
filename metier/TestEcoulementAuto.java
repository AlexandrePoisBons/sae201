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

        double transfert;

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
		alTuyau.add( Tuyau.creerTuyau( 5 ) );
		alTuyau.add( Tuyau.creerTuyau( 3 ) );

		// Création des liens
		alTuyau.get(0).setLien( alCuves.get(0), alCuves.get(1) );
		alTuyau.get(1).setLien( alCuves.get(2), alCuves.get(3) );

		// Remplissage des cuves
		alCuves.get(0).remplir(200);
		alCuves.get(2).remplir( 50);

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

					System.out.println( " | " + cuveOrig );
					System.out.println( " | " + cuveDest );

					if ( cuveOrig.getContenu() > cuveDest.getContenu() )
						cuveOrig.couler( cuveDest, t );

					if ( cuveOrig.getContenu() < cuveDest.getContenu() )
						cuveDest.couler( cuveOrig, t );

					if ( cuveOrig.getContenu() < t.getSection() )
					{
						transfert = ( cuveOrig.getContenu() * t.getSection() ) / 10;
						//alTransfert = calculerTransfert( alCuves, alTuyau, cuveOrig );
						

						cuveOrig.recevoirDe( cuveDest, transfert );

					}

					if ( cuveDest.getContenu() < t.getSection() )
					{
						transfert = ( cuveDest.getContenu() * t.getSection() ) / 10;
						//alTransfert = calculerTransfert( alCuves, alTuyau, cuveDest );
						

						cuveDest.recevoirDe( cuveOrig, transfert );
					}

					sc.nextLine();
				}
			}			
		}		
	}





	public List<Double> calculerTransfert( ArrayList<Cuve> alCuves, ArrayList<Tuyau> alTuyau, Cuve cuvePara )
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
			alTransfert.add(  (double) ( contenu * alSection.get( cpt ) ) / 10 );

		return alTransfert;			
	}
}