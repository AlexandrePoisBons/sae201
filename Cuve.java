public class Cuve
{
   private static char id = 'A'-1;

    private char   idCuve;
    private int    capacite;
    private double contenu;
    private int    posX;
    private int    posY;
    private String position;

    public Cuve(int capacite, int posX, int posY, String position)
    {
        Cuve.id++;
        
        this.idCuve   = Cuve.id;
        this.capacite = capacite;
        this.contenu  = 0;
        this.posX     = posX;
        this.posY     = posY;
        this.position = position;
    }

    private static Cuve creerCuve(int capacite, int posX, int posY, String position)
    {
        if ( Cuve.id > 'Z') return null;
        if ( capacite < 200 || capacite > 1000 ) return null;
        return new Cuve( capacite, posX, posY, position );
    }

    public String toString()
    {
        return "Cuve: " + this.idCuve + " | capacite: " + this.capacite + 
                                    " | Contenu: "  + this.contenu  + 
                                    " | positionne en (" + this.posX + "," +this.posY +")"+
                                    " | et "        + this.position; 
    }
}
