package sae201.ihm;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import java.awt.event.*;

public class panelSelectFichier extends JPanel implements ActionListener
{
    private JFrame          frmParent;
    private ControleurCuves ctrl;

    public panelSelectFichier(JFrame frmParent, ControleurCuves ctrl)
    {
        this.frmParent = frmParent;
        this.ctrl      = ctrl;
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Fichiers seulement .txt", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this.frmParent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           System.out.println("Vous avez choisi d'ouvrir ce fichier: " +
                chooser.getSelectedFile().getName());
        }
 
    }

    public void actionPerformed(ActionEvent ae)
    {
        //FAIRE DES CHOSES ICI
        this.frmParent.dispose();
    }
}