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

    public static Tuyau creerTuyau(int section) // cree un tuyau qui ne sera pas necessairement utilise par la suite //
    {
        if (section < 2 || section > 10) 
            return null;
        
        return new Tuyau (section);
    }

    public boolean setLien( Cuve cuveOrig, Cuve cuveDest  ) // connecte les cuves a partir d'un tuyau precedemment cree //
    {
        if ( cuveOrig == null || cuveDest == null )
            return false;

        this.cuveOrig = cuveOrig;
        this.cuveOrig.connecterTuyau(this); // Connecter les cuves entres elles //
        this.cuveDest = cuveDest;
        this.cuveDest.connecterTuyau(this); // Connecter les cuves entres elles //

        return true;
    }

    //-------------------- Getters ------------------//
    public Cuve getCuveOrig() { return this.cuveOrig; }
    public Cuve getCuveDest() { return this.cuveDest; }
    public int  getSection () { return this.section;  }

    public boolean equals(Tuyau t) // Renvoie vrai si le tuyau correspond a celuie en paramtre // A corriger
    {
        if  (this.cuveOrig == null || this.cuveDest == null) return false;
        return (    this.cuveDest == t.cuveDest 
                 && this.cuveOrig == t.cuveOrig
                 || this.cuveDest == t.cuveOrig
                 && this.cuveOrig == t.cuveDest
                );
    }

    public String toString()
    {
        return (char) this.cuveOrig.getId() + "----- " + this.section + " ------" + (char) this.cuveDest.getId();
    }

}