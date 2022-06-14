package sae201.metier;
import java.util.ArrayList;
import java.awt.Color;

public class Cuve
{
    private static char id = 'A'-1;

    private char   idCuve;
    private int    capacite;
    private double contenu;
    private int    posX;
    private int    posY;
    private String position;
    private Color  couleur;
    private ArrayList<Tuyau> lstTuyauxConnectes;

    /*-----------------------------------------------------------*/
    /*                     Constructeur Cuve                     */
    /*-----------------------------------------------------------*/
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
    /*-----------------------------------------------------------*/

    /*---------------------------------------------------------------------------*/
    /*                              Factory de Cuve                              */
    /*---------------------------------------------------------------------------*/
    public static Cuve creerCuve(int capacite, int posX, int posY, String position)
    {
        // Regarde si le numéro séquentiel n'est pas à Z
        if ( Cuve.id > 'Y' ) 
            return null;

        if ( capacite < 200 || capacite > 1000 ) 
            return null;

        // Vérification de la saisie utilisitatuer pour voir si elle est valide
        if ( !position.toUpperCase().contains("HAUT")   && !position.toUpperCase().contains("BAS")    && 
             !position.toUpperCase().contains("DROITE") && !position.toUpperCase().contains("GAUCHE")    )
            return null;
        

        // Correction de la saisie position ( par ex : haUt est valide mais est formaté en Haut pour des raisons esthéthique )
        switch ( position.toUpperCase() )
        {
            case "HAUT"   -> position = "Haut";
            case "BAS"    -> position = "Bas";
            case "GAUCHE" -> position = "Gauche";
            case "DROITE" -> position = "Droite";
        }

        // Vérification des positions
        if ( posX < 0 || posY < 0 )
            return null;

        return new Cuve( capacite, posX, posY, position );
    }
    /*---------------------------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /* Permet d'ajouter les tuyaux connectés dans l'ArrayList */
    /*--------------------------------------------------------*/
    public void connecterTuyau(Tuyau t)
    {
        this.lstTuyauxConnectes.add(t);
    }
    /*--------------------------------------------------------*/

    /*------------------------------------------------*/
    /* Méthode pour ajouter du liquide dans les cuves */
    /*------------------------------------------------*/
    public boolean remplir(double quantite)
    {
        if ( quantite > this.capacite || quantite < 0)
            return false;
    
        this.contenu += quantite;
        this.majCouleur();
        return true;
        
    }
    /*------------------------------------------------*/

    /*---------------------------------------------------*/
    /* Méthode pour transvaser du liquide dans les cuves */
    /*---------------------------------------------------*/
    public boolean couler(Cuve cuveDest, Tuyau tuyau) 
    // Renvoie vrai si le transfert de fluide vers la cuve de destination a bien été effectué//
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
    /*---------------------------------------------------*/


    public void majCouleur()
    {// mettre notre contenu sur 1000 puis apres easy
        int rgbValue = (int)(this.contenu)/2; // renvoie un nombre [0; 500]
        //System.out.println(rgbValue);
        if ( rgbValue > 255)
            this.couleur = new Color(rgbValue, 0, 0);
        else
        {
            int diff = (255 - rgbValue);
            this.couleur = new Color(255,diff, diff);
        }
        
    }

    /*------------------------------------------------------------------------*/
    /*                                 Getters                                */
    /*------------------------------------------------------------------------*/
    public int    getCapacite   () { return this.capacite;                     } 
    public int    getPlaceLibre () { return this.capacite - (int)this.contenu; }
    public int    getPosX       () { return this.posX;                         }
    public int    getPosY       () { return this.posY;                         }
    public char   getId         () { return this.idCuve;                       }
    public double getContenu    () { return this.contenu;                      }
    public String getPosition   () { return this.position;                     }
    public Color  getCouleur    () { return this.couleur;                      }
    
    public ArrayList<Tuyau> getTuyauxConnectes() { return this.lstTuyauxConnectes; }
    /*------------------------------------------------------------------------*/

    public boolean estVide   () { return this.contenu == 0;             }
    public boolean estPleine () { return this.capacite == this.contenu; }
    
    public boolean estVoisin( Cuve cuvePara )
    { 
        for ( Tuyau t : this.lstTuyauxConnectes )     
            if ( this.getId()     == t.getCuveOrig().getId() &&
                 cuvePara.getId() == t.getCuveDest().getId() ||
                 cuvePara.getId() == t.getCuveOrig().getId() &&
                 this.getId()     == t.getCuveDest().getId()     )
                return true;

            return false;
    }

    /*-----------------------------------------------*/
    /* Méthode toString() pour l'affichage des Cuves */
    /*-----------------------------------------------*/
    public String toString()
    {
        return " Cuve : "          + String.format("%2s"   , this.idCuve   ) + " | " + 
               " Capacité : "      + String.format("%4d"   , this.capacite ) + " | " + 
               " Contenu  : "      + String.format("%4.2f" , this.contenu  ) + " | " + 
               " Positionné en : " + String.format("%4d"   , this.posX     ) + " : " + 
                                     String.format("%-4d"  , this.posY     ) + " | " +
               " Direction : "     + String.format("%-5s"  , this.position );
    }
    /*-----------------------------------------------*/
}
