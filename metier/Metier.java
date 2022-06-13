package sae201.metier;

import sae201.ihm.*;

import java.util.ArrayList;

public class Metier
{
    private ArrayList<Cuve>  lstCuves; 
    private ArrayList<Tuyau> lstTuyaux; 
    private ControleurCuves  ctrl;

    public Metier(ControleurCuves ctrl)
    {
        this.lstCuves = new ArrayList<Cuve>();
        this.lstTuyaux = new ArrayList<Tuyau>(); 
    }

    public static Tuyau[][] creerMatrice(ArrayList<Cuve> lstCuves, ArrayList<Tuyau> lstTuyaux, int taille)
    {
        Tuyau [][] matrice = new Tuyau [taille][taille];
        for (int i=0; i<taille; i++)
        {
            for (int j=0; j<taille; j++)
            {
                matrice[i][j] = null;
            } 
        }

        for( Cuve c: lstCuves)
        {
            for (Tuyau t: lstTuyaux)
            {
                if (t.getCuveOrig() == c || t.getCuveDest() == c)
                {
                    matrice[ (int) (  t.getCuveDest().getId() - 'A'  ) ] [ (int) (  t.getCuveOrig().getId() - 'A'  ) ] = t;
                    matrice[ (int) (  t.getCuveOrig().getId() - 'A'  ) ] [ (int) (  t.getCuveDest().getId() - 'A'  ) ] = t;
                }
            }
        }
        return matrice;       
    }

    public static String afficherMatrice(Tuyau[][] matrice)
    {
        String sRet ="";
        for (int i=0; i<matrice.length; i++)
        {
            for (int j=0; j<matrice[0].length; j++)
            {
                if (matrice[i][j] != null)
                    sRet += matrice[i][j].getSection() +" ";
                else sRet += 0 +" ";
            } 
            sRet +="\n";
        }
        return sRet;
    }

    public static String afficherMatriceOpti(Tuyau[][]matrice)
    {
        String sRet = "";
        for (int i=0; i<matrice.length; i++) // pour chaque ligne
        {
            for (int j=0; j<=i; j++)
            {
                if (matrice[i][j] != null)
                    sRet += matrice[i][j].getSection() +" ";
                else sRet += 0 +" ";
            } 
            sRet +="\n";
        }
        return sRet;
    }

    public static String afficherListeAdjacence( Tuyau[][] matrice )
    {
        String sRet = "";
        for ( int i = 0; i < matrice.length; i++ ) 
        {
            for ( int j = 0; j < matrice[0].length; j++ )
            {
                if (matrice[i][j] != null)
                     sRet += 1 + " ";
                else sRet += 0 + " ";
            }
            sRet += "\n";
        }

        return sRet;
    }

    public static void main(String[] args)
    {
        //------------------------- Creation de cuves -------------------------//
        Cuve c1 = Cuve.creerCuve(500, 50, 50, "Haut");
        System.out.println(c1);

        Cuve c2 = Cuve.creerCuve(300, 100, 100, "Bas");
        System.out.println(c2);

        Cuve c3 = Cuve.creerCuve(500, 150, 150, "Droite");
        System.out.println(c3);

        Cuve c4 = Cuve.creerCuve(500, 200, 200, "Droite");
        System.out.println(c4);

        Cuve c5 = Cuve.creerCuve(500, 250, 250, "Droite");
        System.out.println(c5);

        Cuve c6 = Cuve.creerCuve(500, 300, 300, "Droite");
        System.out.println(c6);

        //------------------------- Creation de tuyaux -------------------------//
        Tuyau t1c1c2 = Tuyau.creerTuyau(5);
        t1c1c2.setLien(c1,c2);
        System.out.println(t1c1c2);

        Tuyau t2c1c3 = Tuyau.creerTuyau(8);
        t2c1c3.setLien(c1,c3);
        System.out.println(t2c1c3);

        Tuyau t3c2c3 = Tuyau.creerTuyau(3);
        t3c2c3.setLien(c2,c3);
        System.out.println(t3c2c3);

        Tuyau t4c3c5 = Tuyau.creerTuyau(4);
        t4c3c5.setLien(c3,c5);
        System.out.println(t4c3c5);

        Tuyau t5c2c5 = Tuyau.creerTuyau(6);
        t5c2c5.setLien(c2,c5);
        System.out.println(t5c2c5);

        Tuyau t6c1c6 = Tuyau.creerTuyau(6);
        t6c1c6.setLien(c1,c6);
        System.out.println(t6c1c6);

        //------------------------- Creation de matrice -------------------------//
        ArrayList<Cuve>  lstTempC  = new ArrayList<Cuve>(); 
        ArrayList<Tuyau> lstTempT  = new ArrayList<Tuyau>(); 
        
        // Ajout cuves dans liste de cuves //
        lstTempC.add(c1);
        lstTempC.add(c2);
        lstTempC.add(c3);
        lstTempC.add(c4);
        lstTempC.add(c5);
        lstTempC.add(c6);

        // Ajout tuyaux dans liste de tuyaux //
        lstTempT.add(t1c1c2);
        lstTempT.add(t2c1c3);
        lstTempT.add(t3c2c3);
        lstTempT.add(t4c3c5);
        lstTempT.add(t5c2c5);
        lstTempT.add(t6c1c6);

        System.out.println();

        System.out.println("Matrice: \n"+afficherMatrice(creerMatrice(lstTempC, lstTempT, 6)));
        System.out.println("Matrice Opti: \n" + afficherMatriceOpti(creerMatrice(lstTempC, lstTempT, 6)));
        System.out.println("Liste d'adjacence: \n" + afficherListeAdjacence(creerMatrice(lstTempC, lstTempT, 6)));
    }
}