import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class TestFileReader
{
    private String fichier;

    public TestFileReader(String fichier )
    {
        this.fichier = fichier;
        try{
            FileReader fr = new FileReader(fichier);
            Scanner    sc = new Scanner(fr);

            int cpt = 0;
            sc.next();
            while (sc.hasNextLine())
            {                
                while(sc.next()!= "")
                {
                    System.out.println("Cuve -->"+ sc.next());
                }
                
                while(sc.next()!= "")
                {
                    System.out.println("Tuyau -->" + sc.next());
                }
               
                System.out.println("Format -->"+sc.next());
                while(sc.next()!= "")
                {
                    System.out.println(sc.next());
                }
                System.out.println("FIN");
            }
            sc.close();
        }
        catch(Exception e) { e.getStackTrace(); }
        
    }

    public static void main(String[] args)
    {
        new TestFileReader("../metier/resultat.txt");
    }
}