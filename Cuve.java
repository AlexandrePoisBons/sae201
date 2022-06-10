public class Cuve
{
    static char id = 'A'-1;

    int capacite;
    double contenu;
    int posX;
    int posY;
    String position;

    public Cuve(int capacite, int posX, int posY, String position)
    {
        id++;
        this.capacite = capacite;
        this.contenu = 0;
        this.posX = posX;
        this.posY = posY;
        this.position = position;
    }

    private static Cuve creerCuve(int capacite, int posX, int posY, String position)
    {
        if (id > 'Z') return null;
        if (capacite < 200 || capacite > 1000) return null;
        return new Cuve(capacite, posX, posY, position);

    }

    public String toString()
    {
        return "Cuve: " + id + " | capacite: " + this.capacite + 
                               " | Contenu: " + this.contenu + 
                               " | positionne en (" + this.posX + "," +this.posY +")"+
                               " | et " + this.position; 
    }

    public static void main(String[] args)
    {
        Cuve c1 =  creerCuve(1000, 50, 50, "Haut");
        System.out.println(c1);    
    }
}