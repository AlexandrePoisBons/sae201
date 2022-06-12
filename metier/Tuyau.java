    package sae201.metier;

public class Tuyau
{
    private Cuve cuveOrig;
    private Cuve cuveDest;
    private int  section;

    private Tuyau(int section)
    {
        this.section  = section;
    }

    public static Tuyau creerTuyau(int section)
    {
        if (section < 2 || section > 10) 
            return null;
        
        return new Tuyau (section);
    }

    public boolean setLien( Cuve cuveOrig, Cuve cuveDest  )
    {
        if ( cuveOrig == null || cuveDest == null )
            return false;

        this.cuveOrig = cuveOrig;
        this.cuveDest = cuveDest;

        return true;
    }

    //-------------------- Getters ------------------//
    public Cuve getCuveOrig() { return this.cuveOrig; }
    public Cuve getCuveDest() { return this.cuveDest; }
    public int  getSection () { return this.section;  }

    public boolean equals(Tuyau t)
    {
        return (    this.cuveDest == t.cuveDest 
                 || this.cuveOrig == t.cuveOrig
                 || this.cuveDest == t.cuveOrig
                 || this.cuveOrig == t.cuveDest
                );
    }

    public String toString()
    {
        return (char) this.cuveOrig.getId() + "----- " + this.section + " ------" + (char) this.cuveDest.getId();
    }

}