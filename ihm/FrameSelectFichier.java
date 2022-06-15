package sae201.ihm;
import javax.swing.*;




public class FrameSelectFichier extends JFrame
{
    private panelSelectFichier panel;
    private ControleurCuves    ctrl;
    public FrameSelectFichier(ControleurCuves ctrl)
    {
        this.ctrl  = ctrl;
        
        this.setTitle("Choix du fichier");
        this.setSize (500, 200);
        
        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.panel = new panelSelectFichier(this, this.ctrl);

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add(this.panel);

        /*--------------------------------------------------*/
        /*               Concernant la JFrame               */
        /*--------------------------------------------------*/
    //    this.setExtendedState(JFrame.MAXIMIZED_BOTH );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /*---------------------------------------------------*/
    } 
}