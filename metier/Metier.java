package sae201.metier;

import java.util.ArrayList;

public class Metier
{
    ArrayList<Cuve>  lstCuves; 
    ArrayList<Tuyau> lstTuyaux; 

    public Metier()
    {

    }

    public int[][] creerMatrice(ArrayList<Cuve> lstCuves, ArrayList<Tuyau> lstTuyaux)
    {
        this.lstCuves = lstCuves;
        this.lstTuyaux = lstTuyaux;

        int [][] matrice = new int [30][30];
        for (int i=0; i<=30; i++)
        {
            for (int j=0; j<=30; j++)
            {
                matrice[i][j] = -1;
            } 
        } ;

        for( Cuve c: lstCuves)
        {
            for (Tuyau t: lstTuyaux)
            {
                if (t.getCuveOrig() == c || t.getCuveDest() == c)
                matrice[ (int) ('A' - t.getCuveOrig().getId() ) ][ (int) ( 'A' - t.getCuveOrig().getId() ) ] = t.getSection();
            }
        }
        return matrice;
    }

    public static void main(String[] args)
    {
        Cuve c1 = Cuve.creerCuve(500, 50, 50, "Haut");
        System.out.println(c1);

        Cuve c2 = Cuve.creerCuve(300, 10, 10, "Bas");
        System.out.println(c2);

        Tuyau t1c1c2 = Tuyau.creerTuyau(c1, c2, 5);
        System.out.println(t1c1c2);
    }
}