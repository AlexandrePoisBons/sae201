package sae201.ihm;

import sae201.metier.*;
import java.util.ArrayList;
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


        } catch ( IOException e )
        {
            e.printStackTrace();
        }
        return sRet;

    
    }

    public static String[] splitS(String str2)
    {
        String[] tab = str2.split("\r?\n|\r");
        return tab;
    }

    public static String afficher(String[] tab)
    {
        String sRet ="";
        sRet += "[ ";
        for (String s: tab)
        {
            sRet += ", "+"|"+s+"|";
        }
        sRet += " ]";
        return sRet;
    }

    public static String parseData(String[] tab)
    {
        ArrayList<String> argCuve = new ArrayList<String>();
        ArrayList<String> argTuyaux = new ArrayList<String>();

        boolean cuveOk  = false;
        boolean tuyauOk = false;
        for (String s:  tab)
        {
            if (!s.equals("Cuves") && !s.equals("") && !cuveOk)
            {
                if (s.equals("Tuyaux"))
                {
                    cuveOk = true;
                }
                else
                {
                    argCuve.add(s);
                }                
                
            }
            if (cuveOk)
            {
                if (!s.equals("Tuyaux") && !tuyauOk)
                {
                    if (s.equals(""))
                    {
                        tuyauOk = true;
                    }
                    else
                    {
                        argTuyaux.add(s);
                    }                    
                }
            }            
        }
        String sRet = "";
        for (String arg: argCuve)
        {
            sRet += "\nArg Cuve --> " + arg;
        }

        for (String argT: argTuyaux)
        {
            sRet += "\n Arg Tuyau -->" + argT;
        }
        return sRet;
    }


    public static void main(String [] args)
    {
        //System.out.println(Lecture.creer("./travail/sae201/metier/resultat.txt"));
    }
}
