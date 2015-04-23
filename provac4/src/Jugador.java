
import java.awt.Color;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Profe
 */
public interface Jugador {
    /**
     * Implementa el moviment del jugador amb un tauler donat i un color de peça.
     * @param t  Tauler actual de joc
     * @param color  Color de la peça que possarà
     * @return Columna on fer el moviment
     */
     
    public int moviment(Tauler t, int color);
        
    

    /**
     * Retorna el nom del jugador que es dona al crear-lo
     * @return Nom del jugador
     */
    public String nom();
    
  
}
