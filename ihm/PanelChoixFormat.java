package sae201.ihm;

import sae201.Controleur;
import sae201.metier.*;

import javax.swing.*;

import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.event.*;
import java.awt.GridLayout;

public class PanelChoixFormat extends JPanel implements ActionListener, ItemListener
{
    private JFrame        frmParent;
    private ButtonGroup   bgFormat;
    private JRadioButton  rbMatrice;
    private JRadioButton  rbMatriceOpti;
    private JRadioButton  rbListeAdjacence;
    private JButton       btnFinir;
    private JButton       btnCreer;
    private String        format;

    private Boolean alreadyChecked = false;

    private ControleurCuves ctrl;
    
    public PanelChoixFormat(JFrame frmParent, ControleurCuves ctrl)
    {
        this.ctrl      = ctrl;
        this.frmParent = frmParent;
        this.format    = "";

        this.setLayout(new GridLayout(2, 3));

        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.bgFormat           = new ButtonGroup();
        this.rbMatrice          = new JRadioButton("Matrice", true); 
        this.rbMatriceOpti      = new JRadioButton("Matrice Opti");
        this.rbListeAdjacence   = new JRadioButton("Liste Adjacence");
        this.btnFinir           = new JButton("Générer .txt et quitter ");
        this.btnCreer           = new JButton("Générer .txt et afficher ");
        
        this.bgFormat.add(this.rbMatrice);
        this.bgFormat.add(this.rbMatriceOpti);
        this.bgFormat.add(this.rbListeAdjacence);
        /*---------------------------------*/

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add(this.rbMatrice);
        this.add(this.rbMatriceOpti);
        this.add(this.rbListeAdjacence);
        this.add(this.btnFinir);
        this.add(this.btnCreer);
        this.add(new JLabel());
        /*-------------------------------*/

        /*-----------------------------------*/
        /*     Activation des composants     */
        /*-----------------------------------*/
        this.rbMatrice       .addItemListener  (this);
        this.rbMatriceOpti   .addItemListener  (this);   
        this.rbListeAdjacence.addItemListener  (this);
        this.btnFinir        .addActionListener(this);
        this.btnCreer        .addActionListener(this);
        /*-----------------------------------*/
    }

    public void actionPerformed(ActionEvent e)
    {
        //Tuyau[][] matrice = this.ctrl.creerMatrice(this.ensCuves, this.ensTuyaux, this.ensCuves.size());
        if (e.getSource() == this.btnCreer && !alreadyChecked )
        {
            this.ctrl.ecrire(this.format);
            this.ctrl.generer();
            this.frmParent.dispose();
            alreadyChecked = true;
        }

        if (e.getSource() == this.btnFinir)
        {
            this.ctrl.ecrire(this.format);
            this.frmParent.dispose();
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == this.rbMatrice)
            this.format = "Matrice";
            this.alreadyChecked = true;

        if (e.getSource() == this.rbMatriceOpti)
            this.format = "Matrice Optimisee";
            this.alreadyChecked = true;

        if (e.getSource() == this.rbListeAdjacence)
            this.format = "Liste d'Adjacence";
            this.alreadyChecked = true;
        
    }
}