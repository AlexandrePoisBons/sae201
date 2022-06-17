package sae201.ihm;

import sae201.metier.*;

import javax.swing.*;

import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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

        //this.setLayout(new GridLayout(2, 3));

        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.bgFormat           = new ButtonGroup();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
/*/
        if (shouldFill) {
                        //natural height, maximum width
                        c.fill = GridBagConstraints.HORIZONTAL;
        }
*/
        this.rbMatrice          = new JRadioButton("Matrice", true); ;
 //       if (shouldWeightX) { c.weightx = 0.5; }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx    = 2;
        c.gridx      = 0;
        c.gridwidth  = 2;
        c.gridy      = 0;
        this.add(this.rbMatrice, c);

        this.rbMatriceOpti      = new JRadioButton("Matrice Opti");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx   = 2;
        c.gridx     = 2;
        c.gridwidth = 2;
        c.gridy     = 0;
        this.add(this.rbMatriceOpti, c);

        this.rbListeAdjacence   = new JRadioButton("Liste Adjacence");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx   = 2;
        c.gridx     = 4;
        c.gridwidth = 2;
        c.gridy     = 0;
        this.add(this.rbListeAdjacence, c);

        this.btnFinir           = new JButton("Générer .txt et quitter ");    
        c.fill      = GridBagConstraints.HORIZONTAL;
        c.gridy     = 1;       //reset to default
        c.weightx   = 0.5;   //request any extra vertical space
        c.weighty   = 1;
        c.gridx     = 0;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        this.add(this.btnFinir, c);

        this.btnCreer           = new JButton("Générer .txt et afficher ");
        c.fill      = GridBagConstraints.HORIZONTAL;
        c.gridy     = 1;       //reset to default
        c.weightx   = 0.5;   //request any extra vertical space
        c.weighty   = 1;
        c.gridx     = 3;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        this.add(this.btnCreer, c);


        this.bgFormat.add(this.rbMatrice);
        this.bgFormat.add(this.rbMatriceOpti);
        this.bgFormat.add(this.rbListeAdjacence);

        /*---------------------------------*/

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
/*
        this.add(this.rbMatrice);
        this.add(this.rbMatriceOpti);
        this.add(this.rbListeAdjacence);
        this.add(this.btnFinir);
        this.add(this.btnCreer);
        this.add(new JLabel());
*/
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
            alreadyChecked = true;
        }
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == this.rbMatrice)
            this.format = "Matrice";

        if (e.getSource() == this.rbMatriceOpti)
            this.format = "Matrice Optimisee";

        if (e.getSource() == this.rbListeAdjacence)
            this.format = "Liste d'Adjacence";
        
    }
}