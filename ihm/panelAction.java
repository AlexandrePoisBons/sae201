package sae201.ihm;

import sae201.metier.*;

import javax.swing.*;

import java.awt.CheckboxGroup;
import java.awt.Checkbox;

import java.awt.event.*;

public class panelAction extends JPanel implements ActionListener
{
    private JFrame          frmParent;
    private JComboBox<String> lstCuveRemplir;
    private JTextField      txtQuantite;

    private JLabel          lblCuve;
    private JLabel          lblQuantite;
    private JButton         btnValider;
    private JButton         btnSuivant;

    private ControleurCuves ctrl;
    
    public panelAction(JFrame frmParent, ControleurCuves ctrl)
    {
        this.frmParent = frmParent;
        this.ctrl      = ctrl;

        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.lblCuve        = new JLabel("Cuve a remplir");
        this.lblQuantite    = new JLabel("Quantite");
        this.lstCuveRemplir = new JComboBox();
        this.txtQuantite    = new JTextField(4);
        this.btnValider     = new JButton("Valider");
        this.btnSuivant     = new JButton ("Suivant");


        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        for (Cuve c: this.ctrl.getCuves())
        {
            this.lstCuveRemplir.addItem("" + c.getId());
        }
        
        
        this.add(this.lblCuve);
        this.add(this.lblQuantite);
        this.add(this.lstCuveRemplir);
        this.add(this.txtQuantite);
        this.add(this.btnValider);
        this.add(this.btnSuivant);

        

        /*-----------------------------------*/
        /*     Activation des composants     */
        /*-----------------------------------*/

        this.btnValider.addActionListener(this);
        this.btnSuivant.addActionListener(this);
    }  

    public void actionPerformed(ActionEvent e)
    {
        if ( e.getSource() == this.btnValider )
        {
            char cuve = ((String)(this.lstCuveRemplir.getSelectedItem())).charAt(0);
            double qte = Double.parseDouble(this.txtQuantite.getText());
            this.ctrl.estCuve(cuve).remplir(qte);
            this.frmParent.repaint();
        }

        if (e.getSource() == this.btnSuivant )
        {
            //passer a l'iteration suivante.
        }
    }

}