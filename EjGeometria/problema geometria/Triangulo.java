
import static java.lang.Math.pow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Triangulo extends Geometria {
	// definición de variables miembro de la clase
	static int numTriangulos = 0;
	protected int x1, x2, y1, y2, z1, z2;
        static int[] xPoints = new int [3];
        static int [] yPoints = new int [3];

	// constructores de la clase
	public Triangulo(int x1, int x2, int y1, int y2, 
        int z1, int z2) {
            
		this.x1=x1;
                xPoints[0] = x1;
                this.x2=x2;
                yPoints[0] = x2;
                
                this.y1=y1;
                xPoints[1] = y1;
		this.y2=y2;
                yPoints[1] = y2;
                
		this.z1=z1;
                xPoints[2] = z1;
                this.z2=z2;
                yPoints[2] = z2;
                
		numTriangulos++;
	}
	public Triangulo(int z1, int z2) {
		 this(0, 0, 0, 0, z1, z2);
	}
	public Triangulo(Triangulo c) {
		 this(c.x1, c.x2, c.y1, c.y2, c.z1, c.z2);
	}
	public Triangulo() {
		 this(0, 0, 0, 0, 1, 0);
	}
	
	// definición de métodos
	public double perimetro() {
		return 2.0 * 1 * z1;
	}
	public double area() {
		return (pow(5,2));
                
	}

	// método de objecto para comparar círculos
	public Triangulo elMayor(Triangulo c) {
		if (this.z1>=c.z1) return this;
		else return c;
	}

	// método de clase para comparar círculos
	public static Triangulo elMayor(Triangulo c, Triangulo d) {
		if (c.z1>=d.z1) return c;
		else return d;
	}

}
