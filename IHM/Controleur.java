public class Controleur
{
	private Metier   metier;
	private FrameGUI ihm;

	public Controleur()
	{
		this.metier = new Metier();
		this.ihm    = new FrameGUI();
	}

	public static void main (String[] args)
	{
		new Controleur();
	}
}