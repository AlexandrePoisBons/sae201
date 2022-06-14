package sae201.ihm;

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
    }

    public void actionPerformed(ActionEvent ae)
    {
        //FAIRE DES CHOSES ICI
        this.frmParent.dispose();
    }
}