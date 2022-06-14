package sae201.ihm;	

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class TestFileReader
{
    private String fichier;
 
    public TestFileReader(String fichier )
    {
        boolean passage = false;
        boolean passage2 = true;
        this.fichier = fichier;
        try{
            FileReader fr = new FileReader(fichier);
            Scanner    sc = new Scanner(fr);

            int cpt = 0;
            sc.nextLine();

            while (sc.hasNextLine())
            {   
                String temp = sc.nextLine(); 

                if (temp.equals("Tuyaux"))
                {
                    passage = true;
                    passage2 = false;
                    temp = sc.nextLine();
                }

                if (temp.equals("Matrice Optimisee") || temp.equals("Liste d'Adjacence") || temp.equals("Matrice"))
                {
                    passage2 = true;
                }

                if (!temp.equals("") && passage == false)
                {
                    System.out.println("Cuves " + (char)(65+cpt) + " --> " + temp);
                }

                if(!temp.equals("") && passage2 == false)
                {
                    System.out.println("Tuyaux  --> " + temp);
                }

                /*while(sc.next()!= "")
                {
                    System.out.println("Tuyau -->" + sc.next());
                }
               
                System.out.println("Format -->"+sc.next());
                while(sc.next()!= "")
                {
                    System.out.println(sc.next());
                }
                System.out.println("FIN");*/
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