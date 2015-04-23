//  Fichero VentanaCerrable.java

import java.awt.*;
import java.awt.event.*;

class VentanaCerrable extends Frame implements WindowListener {

	//  constructores
	public VentanaCerrable()  {
		//  llamada al constructor de Frame
		super();
	}
	public VentanaCerrable(String title)  {
		super(title);
		setSize(500,500);
		addWindowListener(this);
	}

	// métodos de la interface WindowsListener
	public void windowActivated(WindowEvent e)  {;}
	public void windowClosed(WindowEvent e)  {;}
	public void windowClosing(WindowEvent e)  {System.exit(0);}
	public void windowDeactivated(WindowEvent e)  {;}
	public void windowDeiconified(WindowEvent e)  {;}
	public void windowIconified(WindowEvent e)  {;}
	public void windowOpened(WindowEvent e)  {;}

} // fin de la classe VentanaCerrable

