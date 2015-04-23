

/**
 *
 * @author dani__000
 */
public class Comprobar2 {
    public static int comprobarDer(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (j != 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1]) && 
                    (Prueba.brill || Prueba.tablaB[i][j+1] == '1')) match = 1; //abajo
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1]) &&
                    (Prueba.brill || Prueba.tablaB[i][j-1] == '1')) match = 3; //arriba
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i+1][j] == '1')) match = 0; //derecha
        }
        return match;
    }
    
    public static int comprobarIzq(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (j != 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1]) &&
                    (Prueba.brill || Prueba.tablaB[i][j+1] == '1')) match = 1; //abajo
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1]) &&
                    (Prueba.brill || Prueba.tablaB[i][j-1] == '1')) match = 3; //arriba
            else if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i-1][j] == '1')) match = 2; //izquierda
        }
        return match;
    }
    
    public static int comprobarUp(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i-1][j] == '1')) match = 2; //izquierda
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i+1][j] == '1')) match = 0; //derecha
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1]) &&
                    (Prueba.brill || Prueba.tablaB[i][j-1] == '1')) match = 3; //arriba
        }
        return match;
    }
    
    public static int comprobarDown(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i-1][j] == '1')) match = 2; //izquierda
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i+1][j] == '1')) match = 0; //derecha
            else if (j < 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1]) &&
                    (Prueba.brill || Prueba.tablaB[i][j+1] == '1')) match = 1; //abajo
        }
        return match;
    }

    public static int comprobarTriHoriz(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (j < 7 && temp.equalsIgnoreCase(Prueba.tabla[i][j+1]) &&
                    (Prueba.brill || Prueba.tablaB[i][j+1] == '1')) match = 1; //abajo
            else if (j > 0 && temp.equalsIgnoreCase(Prueba.tabla[i][j-1]) &&
                    (Prueba.brill || Prueba.tablaB[i][j-1] == '1')) match = 3; //arriba
        }
        return match;
    }
    
    public static int comprobarTriVert(String temp, int i, int j) {
        int match = -1;
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7){
            if (i > 0 && temp.equalsIgnoreCase(Prueba.tabla[i-1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i-1][j] == '1')) match = 2; //izquierda
            else if (i < 7 && temp.equalsIgnoreCase(Prueba.tabla[i+1][j]) &&
                    (Prueba.brill || Prueba.tablaB[i+1][j] == '1')) match = 0; //derecha
        }
        return match;
    }
}
