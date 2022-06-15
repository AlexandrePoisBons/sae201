package sae201.ihm;
import javax.swing.*;


public class Application2 extends JFrame
{
    private panelApplication2 panel;

    public Application2()
    {
        this.setTitle("Application 2");
        this.setSize (300, 300);
        
        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.panel = new panelApplication2(this);
        /*---------------------------------*/

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add(this.panel);


        this.setResizable(false);
        this.setVisible(true);
    } 

    public static void main(String[] args)
    {
        new Application2();
    }
}