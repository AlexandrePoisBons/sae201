import java.util.ArrayList;

public class Metier
{
    public Metier()
    {
        ArrayList<Cuve>  lstCuves   = new ArrayList<Cuve>(); 
        ArrayList<Tuyau> lstTuyasux = new ArrayList<Tuyau>(); 

        Tuyau [][] matrice;
        // liste de cuves
        // liste de tuyaux
         
       /* 
       //             de       A   B   C   D   E   F   G        / vers
       int[][] matrice = {  { -1,  3, -1,  0, -1, -1, -1, },    // A
                            {  1, -1,  3, -1, -1, -1, -1, },    // B
                            { -1,  1, -1, -1,  0, -1, -1, },    // C
                            {  2, -1, -1, -1, -1,  0, -1, },    // D
                            { -1, -1,  2, -1, -1, -1, -1, },    // E
                            { -1, -1, -1,  2, -1, -1,  3, },    // F
                            { -1, -1, -1, -1, -1,  1, -1  }  }; // G
        */

        for( Cuve c: lstCuves)
        {
            for (Tuyau t: lstTuyasux)
            {
                if (t.getCuve1() == c)matrice["depart"]["destination"] = t;
                else if (t.getCuve2() == c ) ["un nombre"][]
            }
        }
        
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