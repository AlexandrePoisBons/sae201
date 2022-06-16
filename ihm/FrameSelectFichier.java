package sae201.ihm;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class FrameSelectFichier extends JFrame implements ActionListener
{
    private ControleurCuves ctrl;
    private String          fichier;
    private JLabel          lblFichier;
    private JButton         btnCreer;
    private JPanel          panelInfo;

    private JMenuItem       menuiFichierOuvrir;
	private JMenuItem       menuiFichierQuitter;
    private boolean         estChoisi;

    public FrameSelectFichier(ControleurCuves ctrl)
    {
        this.ctrl  = ctrl;
        this.estChoisi = false;
        this.setTitle("Générer le Réseau");
        this.setSize (500, 200);
        
        /*---------------------------------*/
        /*     Création des composants     */
        /*---------------------------------*/
        this.panelInfo  = new JPanel();
        this.panelInfo.setLayout( new GridLayout(1,3, 5, 5));
        this.lblFichier = new JLabel();
        this.btnCreer   = new JButton("Générer");

        JMenuBar menubMaBarre = new JMenuBar();
        JMenu    menuFichier  = new JMenu("Fichier");

        this.menuiFichierOuvrir  = new JMenuItem ("Ouvrir" );
		this.menuiFichierQuitter = new JMenuItem ("Quitter");

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        menuFichier.add( this.menuiFichierOuvrir );
		menuFichier.addSeparator();
		menuFichier.add( this.menuiFichierQuitter );

		menubMaBarre.add( menuFichier );
        this.setJMenuBar( menubMaBarre );

        this.panelInfo.add( new JLabel("Nom du fichier selectionné :"));
        this.panelInfo.add(this.lblFichier);
        this.panelInfo.add(this.btnCreer);

        this.add(this.panelInfo);

        /*-------------------------------*/
        /*   Activation des composants   */
        /*-------------------------------*/
        this.menuiFichierOuvrir .addActionListener ( this );
		this.menuiFichierQuitter.addActionListener ( this );
        this.btnCreer           .addActionListener ( this );

        /*--------------------------------------------------*/
        /*               Concernant la JFrame               */
        /*--------------------------------------------------*/
    //    this.setExtendedState(JFrame.MAXIMIZED_BOTH );
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        /*---------------------------------------------------*/
    } 

    public void actionPerformed ( ActionEvent e )
	{
		// Création et ouverture d'un JFileChooser pour affecter
		if ( e.getSource() ==  this.menuiFichierOuvrir   )
		{
			JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers seulement .txt", "txt");
            chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog( this );
			if( returnVal == JFileChooser.APPROVE_OPTION )
			{
                this.fichier = "" + chooser.getSelectedFile();
				//this.ctrl.setFichierImage ( chooser.getSelectedFile().getPath() );
                this.lblFichier.setText( "      " + chooser.getSelectedFile().getName());
                this.lblFichier.setForeground(Color.BLACK);
                this.fichier = chooser.getSelectedFile().getPath();
                this.estChoisi = true;
			}
		}

		// Fermeture de l'application
		if ( e.getSource() == this.menuiFichierQuitter )
		{
			this.dispose();
		}

        if ( e.getSource() == this.btnCreer && this.estChoisi)
		{
			this.ctrl.creerGraph(this.fichier);
            this.dispose();
		}
        if ( e.getSource() == this.btnCreer && !this.estChoisi)
        {
            this.lblFichier.setText("Veuillez choisir un fichier");
            this.lblFichier.setForeground(Color.RED);
        }
	}
}