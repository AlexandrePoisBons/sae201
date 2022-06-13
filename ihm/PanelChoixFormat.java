package sae201.ihm;

import javax.swing.JPanel;
import javax.swing.*;

import sae201.Controleur;
import sae201.metier.*;

import java.awt.CheckboxGroup;
import java.awt.Checkbox;

import java.awt.event.*;

public class PanelChoixFormat extends JPanel implements ActionListener, ItemListener
{
    private ButtonGroup   bgFormat;
    private JRadioButton  rbMatrice;
    private JRadioButton  rbMatriceOpti;
    private JRadioButton  rbListeAdjacence;
    private JButton       btnValider;
    private String        format;

    private ControleurCuves ctrl;
    
    public PanelChoixFormat(ControleurCuves ctrl)
    {
        this.ctrl = ctrl;

        // creation des composants //
            
        this.bgFormat           = new ButtonGroup();
        this.rbMatrice          = new JRadioButton("Matrice"); 
        this.rbMatriceOpti      = new JRadioButton("Matrice Opti");
        this.rbListeAdjacence   = new JRadioButton("Liste Adjacence");
        this.btnValider         = new JButton("Valider");

        this.bgFormat.add(this.rbMatrice);
        this.bgFormat.add(this.rbMatriceOpti);
        this.bgFormat.add(this.rbListeAdjacence);

        this.format = "";

        // positionnement des composants //

        this.add(this.rbMatrice);
        this.add(this.rbMatriceOpti);
        this.add(this.rbListeAdjacence);
        this.add(this.btnValider);

        // activation des composants//

        this.rbMatrice.addItemListener(this);
        this.rbMatriceOpti.addItemListener(this);   
        this.rbListeAdjacence.addItemListener(this);
        this.btnValider.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {

        //Tuyau[][] matrice = this.ctrl.creerMatrice(this.ensCuves, this.ensTuyaux, this.ensCuves.size());
        this.ctrl.ecrire(this.format);
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == this.rbMatrice)
        {
            this.format = "Matrice";
        }

        if (e.getSource() == this.rbMatriceOpti)
        {
            this.format = "Matrice Optimisée";
        }

        if (e.getSource() == this.rbListeAdjacence)
        {
            this.format = "Liste d'Adjacence";
        }
    }
}