package sae201.ihm;
import javax.swing.*;


public class Application2 extends JFrame
{
    private panelApplication2 panel;

    public Application2()
    {
        this.panel = new panelApplication2(this);
        this.setTitle("Application 2");
        //this.setSize(300, 100);
        this.setResizable(false);
        this.setSize(300, 300);
        this.setVisible(true);
        this.add(this.panel);
    } 

    public static void main(String[] args)
    {
        new Application2();
    }
}