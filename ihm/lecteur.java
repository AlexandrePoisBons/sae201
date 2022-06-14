package sae201.ihm;	

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class lecteur
{
    private String fichier;
 
    public lecteur(String fichier )
    {

    }

    public static String lire(String fichier)
    {
        boolean passage = false;
        boolean passage2 = true;
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
        return "bozo";
    }

    public static void main(String[] args)
    {
        new lecteur("../metier/resultat.txt");
    }
}