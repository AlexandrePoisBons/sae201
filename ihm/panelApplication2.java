package sae201.ihm;

import javax.swing.*;

import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.GridLayout;

import java.awt.event.*;

public class PanelApplication2 extends JPanel implements ActionListener, ItemListener
{
    private JFrame        frmParent;
    private ButtonGroup   bgChoix;
    private JRadioButton  rbSimple;
    private JRadioButton  rbAvance;
    private JRadioButton  rbManuel;
    private JButton       btnValider;
    private String        choix;

    private Boolean alreadyChecked = false;

    private ControleurCuves ctrl;
    
    public PanelApplication2(JFrame frmParent)
    {
        this.frmParent = frmParent;
        this.setLayout(new GridLayout(4,1));
        this.choix     = "Simple"; //par defaut

        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.bgChoix    = new ButtonGroup ();

        this.rbSimple   = new JRadioButton("Simple ( Recommandé ) "                   , true); 
        this.rbAvance   = new JRadioButton("Avance ( à partir d'un fichier existant )");
        this.rbManuel   = new JRadioButton("Manuel ( Entrer donnees )"   );

        this.btnValider = new JButton     ("Valider");

        this.bgChoix.add(this.rbSimple);
        this.bgChoix.add(this.rbAvance);
        this.bgChoix.add(this.rbManuel);
        /*---------------------------------*/

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add(this.rbSimple);
        this.add(this.rbAvance);
        this.add(this.rbManuel);
        this.add(this.btnValider);
        /*-------------------------------*/

        /*-----------------------------------*/
        /*     Activation des composants     */
        /*-----------------------------------*/
        this.rbSimple  .addItemListener  (this);
        this.rbAvance  .addItemListener  (this);   
        this.rbManuel  .addItemListener    (this);   
        this.btnValider.addActionListener(this);
        /*-----------------------------------*/
    }  

    public void actionPerformed(ActionEvent e)
    {
        if (!alreadyChecked)
        {
            this.ctrl = new ControleurCuves(this.choix);
            this.frmParent.dispose();
            alreadyChecked = true;
        } 
    }

    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == this.rbSimple)
            this.choix = "Simple";

        if (e.getSource() == this.rbAvance)
            this.choix = "Avance";

        if (e.getSource() == this.rbManuel)
            this.choix = "Manuel";
    }
}