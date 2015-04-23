
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class TrianguloGrafico extends Triangulo implements Dibujable {
	// se heredan las variables y métodos de la clase Circulo
	Color color;

	// constructor
	public TrianguloGrafico(int x1, int x2, int y1, int y2,
                int z1, int z2, Color unColor) {
		// llamada al constructor de Circulo
		super(x1, x2, y1, y2, z1, z2);
		this.color = unColor;    // en este caso this es opcional
	}

	// métodos de la interface Dibujable
	public void dibujar(Graphics dw) {
		dw.setColor(color);
                dw.drawPolygon(xPoints, yPoints, numTriangulos);
	}

	public void setPosicion(double x, double y) {
		;  // metodo vacío, pero necesario definir
	}

}
