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
        this.ctlr             = ctrl;
        this.panelChoixFormat = new PanelChoixFormat(this, ctrl);

        this.setTitle("Choix format");
        this.setSize (500, 200);
        
        this.add(this.panelChoixFormat);

        /*--------------------------------------------------*/
        /*               Concernant la JFrame               */
        /*--------------------------------------------------*/
        this.setExtendedState(JFrame.MAXIMIZED_BOTH );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /*---------------------------------------------------*/
    }
}