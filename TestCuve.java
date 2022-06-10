// Pour l'écriture dans le fichier texte
import java.io.PrintWriter;
import java.io.FileOutputStream;

// EN cas d'encodage en utf-8 import cette classe :
//  import java.io.OutputStreamWriter;

public class TestCuve
{
	public static void main(String[] args)
	{
		/*-----------------------------*/
		/*			Variables 		   */
		/*-----------------------------*/

		System.out.print("Combien de cuves ? ");

		System.out.print("Quelle capacité ? ");

		System.out.println("");







		// Pour l'écriture dans le fichier texte
		// Pour forcer l'encodage (par exemple en utf-8), remplacez l'instanciation de pw par :
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sortie.txt"), "UTF8" ));
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("sortie.txt") );

			pw.println ( "Pierre\tDubois\n" );
			pw.println ( "Régis\tLoisel\n" );
			pw.println ( "René\tGoscinny" );

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}
}