package sae201.ihm;

import javax.swing.filechooser.FileNameExtensionFilter;

import sae201.ihm.FrameCreation;
import sae201.ihm.FrameSelectFichier;

import javax.swing.*;
import java.awt.event.*;

public class panelSelectFichier extends JPanel implements ActionListener
{
    private FrameSelectFichier  frmParent;
    private ControleurCuves     ctrl;
    private String              fichier;

    public panelSelectFichier(FrameSelectFichier frmParent, ControleurCuves ctrl)
    {
        this.ctrl      = ctrl;
        this.frmParent = frmParent;
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers seulement .txt", "txt");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(this.frmParent);
        if(returnVal == JFileChooser.APPROVE_OPTION)
           this.fichier = ""+chooser.getSelectedFile();
 
    }

    public void actionPerformed(ActionEvent ae)
    {
        //FAIRE DES CHOSES ICI
        this.ctrl.creerGraph(this.fichier);
        this.frmParent.dispose();
    }
}