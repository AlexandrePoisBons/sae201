package sae201.metier;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Collections;

public class Cuve implements Comparable<Cuve>
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
        this.couleur            = new Color(255,255,255);
    }
 

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
        switch ( position.toUpperCase().replace( " ", "") )
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


    /*--------------------------------------------------------*/
    /*               Constructeur par recopie                 */
    /*--------------------------------------------------------*/

    public static Cuve creerCuve(Cuve c)
    {
        int capacite    = c.getCapacite();
        int posX        = c.getPosX();
        int posY        = c.getPosY();
        String position = c.getPosition();

        return Cuve.creerCuve(capacite, posX, posY, position);
    }

    /*--------------------------------------------------------*/
    /* Permet d'ajouter les tuyaux connectés dans l'ArrayList */
    /*                   ---------------                      */
    /*  Chaque Cuve aura pourra ainsi acceder a ses voisins   */
    /*--------------------------------------------------------*/

    public void connecterTuyau(Tuyau t)
    {
        this.lstTuyauxConnectes.add(t);
    }


    /*------------------------------------------------*/
    /* Méthode pour ajouter du liquide dans les cuves */
    /*                   -----------                  */
    /*     Renvoie vrai sur la cuve a etre remplie    */
    /*           avec la quantite souhaitee           */
    /*------------------------------------------------*/

    public boolean remplir(double quantite)
    {
        if ( quantite > this.getPlaceLibre() || quantite < 0 || this.estPleine())
        {
            return false;
        }
    
        this.contenu += quantite;
        this.majCouleur();
        return true;
        
    }


    /*---------------------------------------------------*/
    /* Méthode pour transvaser du liquide dans les cuves */
    /*                  -----------                      */
    /*  Renvoie vrai si le transfert de fluide vers la   */
    /*     cuve de destination a bien été effectué       */
    /*---------------------------------------------------*/

    public boolean couler(Cuve cuveDest, Tuyau tuyau) 
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
        cuveDest.majCouleur();

        return true;    
    }


    /*---------------------------------------------------*/
    /*    Méthode pour changer le contenu de la cuve     */
    /*                  -----------                      */
    /*       Sert pour effectuer les transfert           */
    /*---------------------------------------------------*/

    public void recevoirDe( Cuve cuveDest, double transfert )
    {
        this.contenu     += transfert;
        cuveDest.contenu -= transfert;
        cuveDest.majCouleur();
        this.majCouleur();
    }


    /*---------------------------------------------------*/
    /*      Méthode pour mettre a jour la couleur en     */
    /*           fonction du contenu de la cuve          */
    /*                  -----------                      */
    /*     Est appelee a chaque fois que le contenu      */
    /*                  vient a changer                  */
    /*---------------------------------------------------*/

    public void majCouleur()
    {// mettre notre contenu sur 1000 puis apres easy
        int rgbValue = (int)(this.contenu)/2; // renvoie un nombre [0; 500]
        System.out.println("Couleur: "+rgbValue);
        //System.out.println(rgbValue);
        if ( rgbValue > 255)
            this.couleur = new Color(255-rgbValue/2, 0, 0);
        else
        {
            int diff = (255 - rgbValue/2);
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
    public int    getNbTuyaux   () { return this.lstTuyauxConnectes.size();    }
     
    public ArrayList<Tuyau> getTuyauxConnectes() { return this.lstTuyauxConnectes; }

    public boolean estVide   () { return this.contenu == 0;             }
    public boolean estPleine () { return this.capacite == this.contenu; }
  
    
    /*------------------------------------------------*/
    /*  Renvoie vrai si la cuve actuelle est voisine  */
    /*    a celle pasee en paramatre (si il y a un    */
    /*            tuyau entre les deux)               */
    /*------------------------------------------------*/
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
    /*   Renvoie le tuyau qui relie la cuve actuelle */
    /*            a celle pasee en paramatre         */
    /*-----------------------------------------------*/

    public Tuyau getTuyauEntre( Cuve cuvePara )
    { 
        for ( Tuyau t : this.lstTuyauxConnectes )     
            if ( this.getId()     == t.getCuveOrig().getId() &&
                 cuvePara.getId() == t.getCuveDest().getId() ||
                 cuvePara.getId() == t.getCuveOrig().getId() &&
                 this.getId()     == t.getCuveDest().getId()     )
                return t;

            return null;
    }


    /*-----------------------------------------------*/
    /*   Permet l'utilisation de Collection.sort()   */
    /*-----------------------------------------------*/

    public int compareTo(Cuve cuve2)
    {
        if ( this.getContenu() >  cuve2.getContenu() ) return  1;
        if ( this.getContenu() == cuve2.getContenu() ) return  0;
        if ( this.getContenu() >  cuve2.getContenu() ) return -1;
        else return 99;
    }

    public static void decrement()
    {
        id--;
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

}
