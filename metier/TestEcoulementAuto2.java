package sae201.metier;

// Pour les structures de données
import java.util.ArrayList;
import java.util.List;
import sae201.ihm.*;

// Pour la lecture des saisies claviers
import java.util.Scanner;


public class TestEcoulementAuto2
{
	public static void main(String[] args)
	{
		ControleurCuves ctrl = new ControleurCuves("Manuel");
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

		System.out.println("avant tri");
		for ( Cuve c : ctrl.getCuves())
			System.out.println( c );


		while ( !estEquilibre )
		{
			for( Cuve c : alCuves )
			{
				for ( Tuyau t : c.getTuyauxConnectes() )
				{
                    if (t.getCuveOrig() == c) // cas ou les tuyaux sont inverses
                    {
                        Cuve cuveActuelle = c;
                        Cuve cuveDestination = t.getCuveDest();
                    }
                    else
                    {
                        Cuve cuveActuelle = t.getCuveDest();
                        Cuve cuvedestination = c;
                    }					

					sc.nextLine();
				}
			}			
		}
		ctrl.trier(ctrl.ensCuves);
		System.out.println("\nApres tri");
		for ( Cuve c : ctrl.getCuves())
			System.out.println( c );
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

// Tant que ce n'est pas equilibre on peut passer au tour suivant .