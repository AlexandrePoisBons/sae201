    package sae201.metier;

public class Tuyau
{
    private Cuve cuveOrig;
    private Cuve cuveDest;
    private int  section;

    private Tuyau(Cuve cuveOrig, Cuve cuveDest, int section)
    {
        this.cuveOrig = cuveOrig;
        this.cuveDest = cuveDest;
        this.section  = section;
    }

    public static Tuyau creerTuyau(Cuve cuveOrig, Cuve cuveDest, int section)
    {
        if (section < 2 || section > 10) 
            return null;
        
        return new Tuyau (null, null, section);
    }

    public boolean setLien( Cuve cuveOrig, Cuve cuveDest  )
    {
        if ( cuveOrig == null || cuveDest == null )
            return false;

        this.cuveOrig = cuveOrig;
        this.cuveDest = cuveDest;

        return true;
    }

    // Getters
    public Cuve getCuveOrig() { return this.cuveOrig; }
    public Cuve getCuveDest() { return this.cuveDest; }
    public int  getSection () { return this.section;  }

    public String toString()
    {
        return (char) this.cuveOrig.getId() + "----- " + this.section + " ------" + (char) this.cuveDest.getId();
    }

}