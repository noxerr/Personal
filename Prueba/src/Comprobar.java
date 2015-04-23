
/**
 *
 * @author dani__000
 */
public class Comprobar {
    
    /*
     * En esta clase comprobamos si se da el caso de un movimiento válido. Si se da,
     * devuelve la dirección en la que se tiene que hacer el movimiento.
     * Este es el caso de las de 4 o mas seguidas, y las comunes de 3 (no comprueba brillantes)
    */
    
    public static int comprobarDer(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (j != 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1])) match = 1; //abajo
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1])) match = 3; //arriba
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j])) match = 0; //derecha
        }
        return match;
    }
    
    public static int comprobarIzq(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (j != 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1])) match = 1; //abajo
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1])) match = 3; //arriba
            else if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j])) match = 2; //izquierda
        }
        return match;
    }
    
    public static int comprobarUp(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j])) match = 2; //izquierda
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j])) match = 0; //derecha
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1])) match = 3; //arriba
        }
        return match;
    }
    
    public static int comprobarDown(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j])) match = 2; //izquierda
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j])) match = 0; //derecha
            else if (j < 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1])) match = 1; //abajo
        }
        return match;
    }

    public static int comprobarTriHoriz(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (j < 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1])) match = 1; //abajo
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1])) match = 3; //arriba
        }
        return match;
    }
    
    public static int comprobarTriVert(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j])) match = 2; //izquierda
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j])) match = 0; //derecha
        }
        return match;
    }
}

/*

private Runnable DRAG = new Runnable() {

        @Override
        public void run() {
          if (!DRAGGING) {
            return;
          }
          Point l = MouseInfo.getPointerInfo().getLocation();
          window.setLocation(l.x - 50, l.y - 50);
        }
      };


 donde DRAGGING es un boolean


 y window es el JFrame

*/