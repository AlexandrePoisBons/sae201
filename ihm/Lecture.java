package sae201.ihm;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;

public class Lecture
{

    public static String creer(String fichier)
    {
        String sRet = "";
        try(FileReader fr = new FileReader(fichier))
        {
            int i = fr.read();
            char temp = ' ';
            while ( i != -1 )
            {
                temp = (char)i;
                sRet += temp;
                i = fr.read();
            }


        }catch (IOException e)
        {
            e.printStackTrace();
        }
/*
        try
        {
            Scanner sc = new Scanner ( new FileReader ( "./travail/sae201/metier/resultat.txt" ) );

            while ( sc.hasNextLine() )
            {
                String[] tabS = sc.nextLine().split("\t");

                for(int cpt = 0; cpt < tabS.length; cpt++)
                    System.out.println(tabS[cpt]);

                //Cuve.creerCuve(Integer.parseInt(tabS[0]), Integer.parseInt(tabS[1]), Integer.parseInt(tabS[2]), tabS[3]);
            }
        }catch (Exception e){ e.printStackTrace(); }
*/
    return sRet;
    }

    public static void main(String [] args)
    {
        System.out.println(Lecture.creer("./travail/sae201/metier/resultat.txt"));
    }
}
