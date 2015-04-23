//  fichero CirculoGrafico.java

import java.awt.Graphics;
import java.awt.Color;

public class CirculoGrafico extends Circulo implements Dibujable {
	// se heredan las variables y métodos de la clase Circulo
	Color color;

	// constructor
	public CirculoGrafico(double x, double y, double r, Color unColor) {
		// llamada al constructor de Circulo
		super(x, y, r);
		this.color = unColor;    // en este caso this es opcional
	}

	// métodos de la interface Dibujable
	public void dibujar(Graphics dw) {
		dw.setColor(color);
		dw.drawOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
	}

	public void setPosicion(double x, double y) {
		;  // metodo vacío, pero necesario definir
	}

}  //  fin de la clase CirculoGrafico
 
