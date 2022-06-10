package sae201.metier;

import java.util.ArrayList;

public class Metier
{
    ArrayList<Cuve>  lstCuves; 
    ArrayList<Tuyau> lstTuyaux; 

    public Metier()
    {

    }

    public static Tuyau[][] creerMatrice(ArrayList<Cuve> lstCuves, ArrayList<Tuyau> lstTuyaux, int taille)
    {
        //this.lstCuves = lstCuves;
        //this.lstTuyaux = lstTuyaux;

        Tuyau [][] matrice = new Tuyau [taille][taille];
        for (int i=0; i<taille; i++)
        {
            for (int j=0; j<taille; j++)
            {
                matrice[i][j] = null;
            } 
        };

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
        };
        return sRet;
    }

    public static String afficherMatriceOpti(Tuyau[][]matrice)
    {
        String sRet ="";
        for (int i=0; i<matrice.length; i++) // pour chaque ligne
        {
            for (int j=0; j<=i; j++)
            {
                if (matrice[i][j] != null)
                    sRet += matrice[i][j].getSection() +" ";
                else sRet += 0 +" ";
            } 
            sRet +="\n";
        };
        return sRet;
    }

    public static void main(String[] args)
    {
        //------------------------- Creation de cuves -------------------------//
        Cuve c1 = Cuve.creerCuve(500, 50, 50, "Haut");
        System.out.println(c1);

        Cuve c2 = Cuve.creerCuve(300, 10, 10, "Bas");
        System.out.println(c2);

        Cuve c3 = Cuve.creerCuve(500, 60, 20, "Droite");
        System.out.println(c3);

        //------------------------- Creation de tuyaux -------------------------//
        Tuyau t1c1c2 = Tuyau.creerTuyau(c1, c2, 5);
        t1c1c2.setLien(c1,c2);
        System.out.println(t1c1c2);

        Tuyau t2c1c3 = Tuyau.creerTuyau(c1, c3, 8);
        t2c1c3.setLien(c1,c3);
        System.out.println(t2c1c3);

        Tuyau t3c2c3 = Tuyau.creerTuyau(c2, c3, 3);
        t3c2c3.setLien(c2,c3);
        System.out.println(t3c2c3);

        //------------------------- Creation de matrice -------------------------//
        ArrayList<Cuve>  lstTempC  = new ArrayList<Cuve>(); 
        ArrayList<Tuyau> lstTempT  = new ArrayList<Tuyau>(); 
        
        // Ajout cuves dans liste de cuves //
        lstTempC.add(c1);
        lstTempC.add(c2);
        lstTempC.add(c3);

        // Ajout tuyaux dans liste de tuyaux //
        lstTempT.add(t1c1c2);
        lstTempT.add(t2c1c3);
        lstTempT.add(t3c2c3);

        System.out.println();

        System.out.println("Matrice: \n"+afficherMatrice(creerMatrice(lstTempC, lstTempT, 3)));
        System.out.println("Matrice Opti: \n" + afficherMatriceOpti(creerMatrice(lstTempC, lstTempT, 3)));
        //System.out.println("Liste d'adjacence: \n");
    }
}