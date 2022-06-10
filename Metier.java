public class Metier
{
    public Metier()
    {
        // blablabla
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