public class Cuve
{
   private static char id = 'A'-1;

    public char   idCuve;
    private int    capacite;
    private double contenu;
    private int    posX;
    private int    posY;
    private String position;

    private Cuve(int capacite, int posX, int posY, String position)
    {   
        this.idCuve   = Cuve.id++;
        this.capacite = capacite;
        this.contenu  = 0;
        this.posX     = posX;
        this.posY     = posY;
        this.position = position;
    }

    public static Cuve creerCuve(int capacite, int posX, int posY, String position)
    {
        /* Différentes vérification nécessaires */
        if ( Cuve.id > 'Z' ) 
            return null;

        if ( capacite < 200 || capacite > 1000 ) 
            return null;

        if (position != "Haut" && position != "Bas" && position != "Gauche" && position != "Droite" ) 
            return null;
        /*-------------------------------------------------------------------------------------------*/

        return new this( capacite, posX, posY, position );
    }

    public int getCapacite()    { return this.capacite; }
    public int getid()          { return this.idCuve;   }
    public double getContenu()  { return this.contenu;  }

    public String toString()
    {
        return "Cuve: " + this.idCuve + " | capacite: " + this.capacite + 
                                        " | Contenu: "  + this.contenu  + 
                                    " | positionne en (" + this.posX + "," +this.posY +")"+
                                    " | et "        + this.position; 
    }
}
