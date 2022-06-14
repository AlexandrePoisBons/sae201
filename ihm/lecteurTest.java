package sae201.ihm;	

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class lecteurTest
{
    private String fichier;
 
    public lecteurTest(String fichier )
    {
    }

    public static void /*ArrayList<String[]>*/ lire(String fichier)
    {
        System.out.println("test1");
        ArrayList<String[]>cuves = new ArrayList<String[]>();
        boolean passage = false;
        boolean passage2 = true;
        try{
            FileReader fr = new FileReader(fichier);
            BufferedReader bufRead = new BufferedReader(fr);

            String ligneActuelle = null;
            int cpt = 0;
            String[] tmp;
            while ( (ligneActuelle = bufRead.readLine()) != "")
            {    
                tmp = null;
                System.out.println(ligneActuelle.split(","));
                cuves.add(tmp);
                cpt++;
            }
        }
        catch(Exception e) { e.getStackTrace(); }
        //return cuves;
    }

    public static void main(String[] args)
    {
        new lecteurTest("../metier/resultat.txt");
    }
}