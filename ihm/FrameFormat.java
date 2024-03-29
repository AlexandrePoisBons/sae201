package sae201.ihm;

import sae201.metier.*;

import javax.swing.JFrame;

public class FrameFormat extends JFrame
{
    private ControleurCuves  ctrl;
    private PanelChoixFormat panelChoixFormat;

    public FrameFormat(ControleurCuves ctrl)
    {
        this.setTitle("Choix format");
        this.setSize (500, 200);

        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/

        this.panelChoixFormat = new PanelChoixFormat(this, ctrl);


        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/

        this.add(this.panelChoixFormat);


        /*--------------------------------------------------*/
        /*               Concernant la JFrame               */
        /*--------------------------------------------------*/

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
    }
}