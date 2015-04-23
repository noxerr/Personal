// fichero Ejemplo.java
import java.util.Vector;
import java.util.ArrayList;
import java.awt.*;

class Ejemplo {
	public static void main(String arg[]) throws InterruptedException
	{
        	
		System.out.println("Comienza main()...");

        	Circulo c = new Circulo(2.0, 2.0, 4.0);
		System.out.println("Radio = " + c.r + " unidades.");
		System.out.println("Centro = (" + c.x + "," + c.y + ") unidades.");

		Circulo c1 = new Circulo(1.0, 1.0, 2.0);
		Circulo c2 = new Circulo(0.0, 0.0, 3.0);

		VentanaCerrable ventana = 
			new VentanaCerrable("Ventana abierta al mundo...");
		ArrayList v = new ArrayList();

		CirculoGrafico cg1 = new CirculoGrafico(200, 200, 100, Color.red);
		CirculoGrafico cg2 = new CirculoGrafico(300, 200, 100, Color.blue);
		RectanguloGrafico rg = new RectanguloGrafico(50, 50, 450, 350, Color.green);
		
		v.add(cg1);
		v.add(cg2);
		v.add(rg);

		PanelDibujo mipanel = new PanelDibujo(v);
		ventana.add(mipanel);
		ventana.setSize(500,400);
		ventana.setVisible(true);
		System.out.println("Termina main()...");
	} // fin de main()

} // fin de class Ejemplo
