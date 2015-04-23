
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

/**
 * Es una subclase del robot que mueve el raton poco a poco para que no se note
 */
public class EasingRobot extends Robot {
  public EasingRobot() throws AWTException {
  }

  /**
   * obtiene la pos actual del mouse y las devuelve en un vector de 2 pos: [0] = x y [1] = y.
   *
   * @see stackoverflow.com/a/4484149/59087
   */
  public static int[] getCurrentLocation() {
    PointerInfo pointer = MouseInfo.getPointerInfo();
    Point p = pointer.getLocation();

    // stackoverflow.com/a/1154027/59087
    return new int[]{ (int)p.getX(), (int)p.getY() };
  }

  /**
   * Returns the number of steps to go from point A to B.
     * @return 
   */
  public static int getSteps() { //esta funcion indica cuantos pasos habran entre final y origen. Tenemos tambn un rand.
      int delays = (int) (Math.random() * 7);
    return 9+delays;
  }

  /**
   * Returns the number of milliseconds to delay between each step of
   * the simulated mouse movement animation.
     * @return 
   */
  public static int getSpeed() { //esto es lo que tardara entre paso y paso
      int delays2 = (int) (Math.random() * 5);
    return 4+delays2;
  }

  /**
   * 
   * Este es el algoritmo con el que se moverá el ratón. Sacado de internet.
   * 
   * Performs a sinusoidal easing operation.
   *
   * @param t - Current step in the animation.
   * @param b - Starting value (e.g., starting X or Y value).
   * @param c - Difference between starting value and ending value.
   * @param d - Total steps in the animation.
     * @return 
   *
   * @see upshots.org/actionscript/jsas-understanding-easing
   * @see github.com/jesusgollonet/processing-penner-easing/tree/master/src
   * @see www.gizma.com/easing/
   */
  public static double easing( double t, double b, double c, double d ) {
    return -c/2 * ((float)Math.cos(Math.PI*t/d) - 1) + b;
  }

  /**
   * Esta funcion basicamente lo que hace es usar las funciones anteriores
   * y mover el raton a donde hayamos escogido como destino.
   *
     * @param x2
     * @param y2
   * @see stackoverflow.com/a/6147716/59087
   */
  @Override
  public void mouseMove( int x2, int y2 ) {
    int start[] = getCurrentLocation();
    int x1 = start[0];
    int y1 = start[1];
    int steps = getSteps();
    int speed = getSpeed();

    for( int i = 0; i < steps; i++ ) {
      double dx = easing( i, x1, x2 - x1, steps );
      double dy = easing( i, y1, y2 - y1, steps );

      super.mouseMove( (int)dx, (int)dy );

      delay( speed );
    }
  }
}