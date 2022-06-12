package sae201.metier;
import java.util.ArrayList;

public class Cuve
{
    private static char id = 'A'-1;

    public  char   idCuve;
    private int    capacite;
    private double contenu;
    private int    posX;
    private int    posY;
    private String position;
    private ArrayList<Tuyau> lstTuyauxConnectes;

    private Cuve(int capacite, int posX, int posY, String position)
    {   
        this.idCuve             = ++Cuve.id;
        this.capacite           = capacite;
        this.contenu            = 0;
        this.posX               = posX;
        this.posY               = posY;
        this.position           = position;
        this.lstTuyauxConnectes = new ArrayList<Tuyau>();
    }

    public static Cuve creerCuve(int capacite, int posX, int posY, String position)
    {
        /* Différentes vérifications nécessaires */
        if ( Cuve.id > 'Y' ) 
            return null;

        if ( capacite < 200 || capacite > 1000 ) 
            return null;

        if ( !position.contains("Haut") && !position.contains("Bas") && !position.contains("Droite") && !position.contains("Gauche") )
            return null;
            

        if ( posX < 0 || posY < 0 )
            return null;

        return new Cuve( capacite, posX, posY, position );
    }

    public void connecterTuyau(Tuyau t)
    {
        this.lstTuyauxConnectes.add(t);
    }

    public boolean Couler(Cuve cuveDest, Tuyau tuyau) 
    // Renvoie vrai si le transfert de fluide vers la cuve de destination a bien ete effectue//
    // FAIRE SYSTEME VASES COMMUNICANTS //
    {
        if (cuveDest.estPleine() || !this.estVide())
            return false;
        else
        {
            
            if (cuveDest.placeLibre() < tuyau.getSection())
            {
                cuveDest.contenu += cuveDest.placeLibre();
                this.contenu -= cuveDest.placeLibre();
            }

            else
                cuveDest.contenu += tuyau.getSection();
                this.contenu -= tuyau.getSection();
            return true;
        }
    }

    /* --------------------- Guetteurs ----------------------- */
    public int     getCapacite()                 { return this.capacite;                          } 
    public char    getId()                       { return this.idCuve;                            }
    public double  getContenu()                  { return this.contenu;                           }

    // Autres méthodes //
    public int placeLibre()                      { return this.capacite - (int)this.contenu;      }
    public boolean estVide()                     { return this.contenu == 0;                      }
    public boolean estPleine()                   { return (double) this.capacite == this.contenu; }
    public ArrayList<Tuyau> getTuyauxConnectes() { return this.lstTuyauxConnectes;                }

    public String toString()
    {
        return "Cuve: " + this.idCuve + " | capacite: " + this.capacite  + 
                                        " | Contenu: "  + this.contenu   + 
                                        " | positionne en (" + this.posX + "," + this.posY + ")" +
                                        " | et "        + this.position; 
    }
}
