public class Tuyau
{
    Cuve cuveOrig;
    Cuve cuveDest;
    int  section;

    private Tuyau(Cuve cuveOrig, Cuve cuveDest, int section)
    {
        this.cuveOrig        = cuveOrig;
        this.cuveDestination = cuveDest;
        this.section         = section;
    }

    public static Tuyau creerTuyau(Cuve cuveOrig, Cuve cuveDest, int section)
    {
        if (section < 2 || section > 10) 
            return null;
        
        return new Tuyau (null, null, section);
    }

    public boolean setLien( Cuve cuveOrig, Cuve cuveDest  )
    {
        this.cuveOrig = cuveOrig;
        this.cuveDest = cuveDest;
    }

    // Getters
    public Cuve getCuveOrig() { return this.cuveOrig; }
    public Cuve getCuveDest() { return this.cuveDest; }
    public int  getSection () { return this.section;  }

     public String toString()
    {
        return this.cuveOrig.idCuve + "----- " + this.section + " ------" + this.cuveDest.idCuve;
    }

}