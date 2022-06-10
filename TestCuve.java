// Pour l'écriture dans le fichier texte
import java.io.PrintWriter;
import java.io.FileOutputStream;
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

		System.out.print("Quelle capacité ? ");
		int capaCuves = sc.nextInt();
		/*---------------------------------------------*/

		for(int cpt = 0; cpt < nbCuves; cpt++)
			ensCuves.add(new Cuve(capaCuves, 50, 50, "Haut"));

		for(int cpt = 0; cpt < nbCuves; cpt++)
			System.out.println(ensCuves.get(cpt));







		// Pour l'écriture dans le fichier texte
		// Pour forcer l'encodage (par exemple en utf-8), remplacez l'instanciation de pw par :
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie.txt"), "UTF8" ));
		/*try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("resultat.txt") );

			pw.println ( cuve1.toString() );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }*/
	}
}