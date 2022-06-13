package sae201.ihm;

import javax.swing.JFrame;

import sae201.Controleur;
import sae201.metier.*;

public class FrameFormat extends JFrame
{
   
    private ControleurCuves ctrl;
    private PanelChoixFormat panelChoixFormat;

    public FrameFormat(ControleurCuves ctrl)
    {
        this.setTitle("Choix format");
        this.setSize(500, 200);
        this.setResizable(false);

        this.panelChoixFormat = new PanelChoixFormat(this, ctrl);
        this.add(this.panelChoixFormat);

        this.setVisible(true);
    }
}