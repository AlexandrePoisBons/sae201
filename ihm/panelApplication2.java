package sae201.ihm;

import javax.swing.JPanel;
import javax.swing.*;



import java.awt.CheckboxGroup;
import java.awt.Checkbox;

import java.awt.event.*;

public class panelApplication2 extends JPanel implements ActionListener, ItemListener
{
    private JFrame        frmParent;
    private ButtonGroup   bgChoix;
    private JRadioButton  rbSimple;
    private JRadioButton  rbAvance;
    private JRadioButton  rbManuel;
    // rajouter mode CUI ?
    private JButton       btnValider;
    private String        choix;

    private Boolean alreadyChecked = false;

    private ControleurCuves ctrl;
    
    public panelApplication2(JFrame frmParent)
    {
        this.frmParent = frmParent;
        this.choix="";

        // creation des composants //
            
        this.bgChoix            = new ButtonGroup();
        this.rbSimple           = new JRadioButton("Simple ( recommande ) "); 
        this.rbAvance           = new JRadioButton("Avance ( a partir d'un fichier existant )");
        this.rbManuel           = new JRadioButton("Manuel ( entrer donnees manuellement )");
        this.btnValider         = new JButton("Valider");

        this.bgChoix.add(this.rbSimple);
        this.bgChoix.add(this.rbAvance);
        this.bgChoix.add(this.rbManuel);

        // positionnement des composants //

        this.add(this.rbSimple);
        this.add(this.rbAvance);
        this.add(this.rbManuel);
        this.add(this.btnValider);

        // activation des composants//

        this.rbSimple.addItemListener(this);
        this.rbAvance.addItemListener(this);   
        this.rbManuel.addItemListener(this);   
        this.btnValider.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {

        //Tuyau[][] matrice = this.ctrl.creerMatrice(this.ensCuves, this.ensTuyaux, this.ensCuves.size());
        if (!alreadyChecked)
        {
            //this.ctrl.lancerApp(this.choix);
            this.ctrl = new ControleurCuves(this.choix);
            this.frmParent.dispose();
            alreadyChecked = true;
        }
        
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == this.rbSimple)
        {
            this.choix = "Simple";
        }

        if (e.getSource() == this.rbAvance)
        {
            this.choix = "Avance";
        }

        if (e.getSource() == this.rbManuel)
        {
            this.choix = "Manuel";
        }
    }
}