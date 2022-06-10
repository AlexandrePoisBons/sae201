package sae201.metier;

import java.util.ArrayList;

public class Metier
{
    ArrayList<Cuve>  lstCuves; 
    ArrayList<Tuyau> lstTuyaux; 

    public Metier()
    {

    }

    public static String creerMatrice(ArrayList<Cuve> lstCuves, ArrayList<Tuyau> lstTuyaux, int taille)
    {
        //this.lstCuves = lstCuves;
        //this.lstTuyaux = lstTuyaux;

        int [][] matrice = new int [taille][taille];
        for (int i=0; i<taille; i++)
        {
            for (int j=0; j<taille; j++)
            {
                matrice[i][j] = -1;
            } 
        };

        for( Cuve c: lstCuves)
        {
            for (Tuyau t: lstTuyaux)
            {
                if (t.getCuveOrig() == c || t.getCuveDest() == c)
                matrice[ (int) (  t.getCuveDest().getId() - 'A'  ) ] [ (int) (t.getCuveOrig().getId() - 'A')] = t.getSection();
            }
        }
        String sRet ="";
        for (int i=0; i<taille; i++)
        {
            for (int j=0; j<taille; j++)
            {
                sRet += matrice[i][j] +" ";
            } 
            sRet +="\n";
        };
        return sRet;
    }

    public static void main(String[] args)
    {
        Cuve c1 = Cuve.creerCuve(500, 50, 50, "Haut");
        System.out.println(c1);

        Cuve c2 = Cuve.creerCuve(300, 10, 10, "Bas");
        System.out.println(c2);

        Cuve c3 = Cuve.creerCuve(500, 60, 20, "Droite");
        System.out.println(c3);

        Tuyau t1c1c2 = Tuyau.creerTuyau(c1, c2, 5);
        t1c1c2.setLien(c1,c2);
        System.out.println(t1c1c2);

        Tuyau t1c1c3 = Tuyau.creerTuyau(c1, c3, 8);
        t1c1c3.setLien(c1,c3);
        System.out.println(t1c1c3);


        ArrayList<Cuve>  lstTempC  = new ArrayList<Cuve>(); 
        ArrayList<Tuyau> lstTempT  = new ArrayList<Tuyau>(); 
        lstTempC.add(c1);
        lstTempC.add(c2);
        lstTempC.add(c3);

        lstTempT.add(t1c1c2);
        lstTempT.add(t1c1c3);

        System.out.println(creerMatrice(lstTempC, lstTempT, 3));
    }
}