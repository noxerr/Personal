package bdapp;

/* @author dani__000 */
public class Numeros {
    public int color, tercio, mitad, paridad, numero; //(0 ->red, 1 ->black), (1, 2, 3), (1,2), (1 ->imp,2->par), (0-36) 
    public int orden[][] = new int[37][4];
    
    //ESTA CLASE SIRVE PARA CLASIFICAR LOS NUMEROS Y CREAR UNA MATRIZ DE 37 FILAS (UNA POR NUMERO)
    //CON 4 COLUMNAS PARA INDICAR COLOR, TERCIO DEL TABLERO, MITAD, PARIDAD
    public Numeros(){ //pos: (color = 1, .. numero = 5)
        int n;
        for (n = 1; n <= 36; n++){
            if ((n%2) == 0) orden[n][3] = 2;
            else orden[n][3] = 1;
            if (n < 13) {
                orden[n][2] = 1;
                orden[n][1] = 1;
            }
            else if (n < 25){
                if (n < 19) orden[n][2] = 1;
                else orden[n][2] = 2;
                orden[n][1] = 2;
            }
            else {
                orden[n][1] = 3;
                orden[n][2] = 2;
            }
            if ((n==1) || (n==3) || (n==5) || (n==7) || (n==9) || (n==12) || (n==14) || (n==16) || (n==18)
                     || (n==21) || (n==19) || (n==23) || (n==25) || (n==27) || (n==30) || (n==32) || (n==34) || (n==36))
                orden[n][0] = 0;
            else orden[n][0] = 1;
        }
        for (n= 0; n< 4; n++){
            orden[0][n] = -1;
        }
    }
}
