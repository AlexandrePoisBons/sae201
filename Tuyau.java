public class Tuyau
{
    Cuve cuve1;
    Cuve cuve2;
    int section;

    private Tuyau(Cuve cuve1, Cuve cuve2, int section)
    {
        this.cuve1 = cuve1;
        this.cuve2 = cuve2;
        this.section = section;
    }

    public static Tuyau creerTuyau(Cuve cuve1, Cuve cuve2, int section)
    {
        if (section < 2 || section > 10) return null;
        if (cuve1 == null || cuve2 == null) return null;
        return new Tuyau(cuve1, cuve2, section);
    }   
}