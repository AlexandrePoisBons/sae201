package sae201.ihm;
import javax.swing.*;


public class Application2 extends JFrame
{
    private panelApplication2 panel;

    public Application2()
    {
        this.setTitle("Application 2");
        
        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.panel = new panelApplication2(this);
        /*---------------------------------*/

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add(this.panel);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
    } 

    public static void main(String[] args)
    {
        new Application2();
    }
}