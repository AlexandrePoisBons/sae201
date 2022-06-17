package sae201.ihm;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.awt.BorderLayout;
import sae201.ihm.panelAction;
import sae201.metier.*;

public class FramePrincipale extends JFrame
{
	private ControleurCuves  ctrl;
	private PanelCuves       panelCuves;
   private panelAction      panelAction;
   private ArrayList<Cuve>  ensCuves;  
   private ArrayList<Tuyau> ensTuyaux;
   private JScrollPane      scrollpane;

	public FramePrincipale(ControleurCuves ctrl, ArrayList<Cuve> ensCuves, ArrayList<Tuyau> ensTuyaux)
	{
      this.ctrl      = ctrl;
      this.ensCuves  = ensCuves;
      this.ensTuyaux = ensTuyaux;
        
		this.setTitle( "Affichage du reseaux de Cuves " );
      this.setSize (1000, 500);
      this.setLayout(new BorderLayout());

      /*---------------------------------*/
      /*     Création des composants     */
      /*---------------------------------*/

		this.panelCuves  = new PanelCuves(this.ctrl, this.ensCuves,  this.ensTuyaux);
      this.panelAction = new panelAction(this, this.ctrl);
      this.scrollpane  = new JScrollPane(this.panelCuves);


      /*-------------------------------*/
      /* Positionnement des composants */
      /*-------------------------------*/

		this.add(this.scrollpane,  BorderLayout.CENTER);
      this.add(this.panelAction, BorderLayout.EAST);

      // Permet d'étendre la fenêtre en fonction de la taille de l'écran
      this.setExtendedState(JFrame.MAXIMIZED_BOTH );

      // Permet de femer la fenêtre une fois fini
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Permet d'afficher la fenêtre
      this.setVisible(true);
	}

} 