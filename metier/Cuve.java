package sae201.metier;
import java.util.ArrayList;
import java.awt.Color;

public class Cuve
{
    private static char id = 'A'-1;

    public  char   idCuve;
    private int    capacite;
    private double contenu;
    private int    posX;
    private int    posY;
    private String position;
    private Color  couleur;
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
        this.couleur            = new Color(0,0,0);
    }

    public static Cuve creerCuve(int capacite, int posX, int posY, String position)
    {
        /* Différentes vérifications nécessaires */
        if ( Cuve.id > 'Y' ) 
            return null;

        if ( capacite < 200 || capacite > 1000 ) 
            return null;

        if ( !position.toUpperCase().contains("HAUT")   && !position.toUpperCase().contains("BAS")    && 
             !position.toUpperCase().contains("DROITE") && !position.toUpperCase().contains("GAUCHE")    )
            return null;
            

        if ( posX < 0 || posY < 0 )
            return null;

        return new Cuve( capacite, posX, posY, position );
    }

    public void connecterTuyau(Tuyau t)
    {
        this.lstTuyauxConnectes.add(t);
    }

    public boolean remplir(double quantite)
    {
        if ( quantite > this.capacite || quantite < 0)
            return false;
    
        this.contenu += quantite;
        this.majCouleur();
        return true;
        
    }

    public boolean couler(Cuve cuveDest, Tuyau tuyau) 
    // Renvoie vrai si le transfert de fluide vers la cuve de destination a bien ete effectue//
    // FAIRE SYSTEME VASES COMMUNICANTS //
    {
        // Variable intermédiaire pour éviter de perdre trace de la valeur à transférer
        double contenuTransfert = 0;

        // Vérification des cuves
        if ( cuveDest.estPleine() || this.estVide() )
            return false;
    
        // Cas où le contenu à transférer est plus petit que la section du tuyau
        if ( this.getContenu() < tuyau.getSection() )
        {
            contenuTransfert = this.getContenu();

            cuveDest.contenu += contenuTransfert;
            this.contenu     -= contenuTransfert;
            this.majCouleur();
            return true;            
        }

        // Cas où la section du tuyau est plus grande que la place restante dans la cuveDest
        if ( cuveDest.getPlaceLibre() < tuyau.getSection() )
        {
            contenuTransfert = (double) cuveDest.getPlaceLibre();

            cuveDest.contenu += contenuTransfert;
            this.contenu     -= contenuTransfert;
            this.majCouleur();
            return true;

        }

        // Cas général
        cuveDest.contenu += tuyau.getSection();
        this.contenu     -= tuyau.getSection();
        this.majCouleur();
        return true;    
    }

    public void majCouleur()
    {
        int rgbValue = (int)this.contenu*(1000/(int)this.contenu)/2; // renvoie un nombre [0; 500]
        System.out.println(""+rgbValue);//TEST
        if ( rgbValue < 255)
            this.couleur = new Color(rgbValue, 0, 0);
        else
        {
            int diff = (500 - rgbValue);
            this.couleur = new Color(255,diff, diff);
        }
        
    }

    /* --------------------- Getters ----------------------- */
    public int     getCapacite()                 { return this.capacite;                          } 
    public char    getId()                       { return this.idCuve;                            }
    public double  getContenu()                  { return this.contenu;                           }
    public int     getPosX()                     { return this.posX;                              }
    public int     getPosY()                     { return this.posY;                              }
    public Color   getCouleur()                  { return this.couleur;                           }

    public int     getPlaceLibre()               { return this.capacite - (int)this.contenu;      }
    public ArrayList<Tuyau> getTuyauxConnectes() { return this.lstTuyauxConnectes;                }

    public boolean estVide()                     { return this.contenu == 0;                      }
    public boolean estPleine()                   { return this.capacite == this.contenu;          }
    /*-------------------------------------------------------*/

    public String toString()
    {
        return "Cuve: " + this.idCuve + " | capacite: " + this.capacite  + 
                                        " | Contenu: "  + this.contenu   + 
                                        " | positionne en (" + this.posX + "," + this.posY + ")" +
                                        " | et "        + this.position; 
    }
}
