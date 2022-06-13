package sae201.ihm;

import java.util.ArrayList;
import sae201.metier.*;
import javax.swing.JFrame;

public class ControleurCuves
{
    private ArrayList<Cuve>  ensCuves;
    private ArrayList<Tuyau> ensTuyau;
    private JFrame           ihm;
    /*
     *Completer
     * 
     */
    
    public ControleurCuves()
    {
        this.ihm = new FrameGUI(this);
    }

    public void setCuves(ArrayList<Cuve> ensCuves)
    {
        this.ensCuves  = ensCuves;
    }

    public void setTuyau(ArrayList<Tuyau> ensTuyau)
    {
        this.ensTuyau  = ensTuyau;
    }

    public void generer()
    {
        new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }

    public Cuve estCuve(char c)
    {
        for (Cuve cuve: this.ensCuves)
        {
            if (cuve.getId() == c )
                return cuve;
        }
        return null;
    }

    public void setLien(int index, Cuve c1, Cuve c2)
    {
        this.ensTuyau.get(index).setLien(c1, c2);
    }

    public static void main(String[] args)
    {
        new ControleurCuves();
        //new FramePrincipale(this, this.ensCuves, this.ensTuyau);
    }

}