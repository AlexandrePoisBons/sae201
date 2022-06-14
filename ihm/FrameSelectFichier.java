package sae201.ihm;
import javax.swing.*;




public class FrameSelectFichier extends JFrame
{
    private panelSelectFichier panel;
    private ControleurCuves    ctrl;
    public FrameSelectFichier(ControleurCuves ctrl)
    {
        this.ctrl = ctrl;
        this.panel = new panelSelectFichier(this, this.ctrl);
        this.setTitle("Choix du fichier");
        this.setResizable(false);
        this.setSize(300, 300);
        this.setVisible(true);
        this.add(this.panel);
    } 
}