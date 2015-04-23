/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio.controlador;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author dani__000
 */
public class CtrlPlantilla {
    //EN CARGAR Y GUARDAR FALTA MODIFICARLO YA QUE NO DEVOLVERA NI GUARDARA
    //PLANTILLA, EN REALIDAD GUARDARA EL OBJECT[2] CON LOS ATRIBUTOS EL DE 
    //GETPOND DE PLANTILLA
    
    public static double[] cargarPlantilla(String nom){
        //llamar a la capa de persistencia que nos cargue una plantilla
        double[] lista = new double[9];
        lista[0] = 7;
        lista[1] = 9;
        lista[2] = 0;
        lista[3] = 1;
        lista[4] = 2;
        lista[5] = 4;
        lista[6] = 8;
        lista[7] = 3;
        lista[8] = 13;
        //double[] lista = null;
        return lista;
    }
    
    public static void guardarPlantilla(Object[] ob){
        //llamar a la capa de persistencia que guarde la plantilla que nos han pasado
        Map<String, Double> mapa = new HashMap<String,Double>();
        mapa = (Map)ob[1];
        for (String clave : mapa.keySet()) {   
            Double valor = mapa.get(clave);
            System.out.println("Clave: " + clave + ": " + valor);
        }
    }
    
    public static String[] cargarListaPlantillas(){
        String[] ret = null;
        return ret;
    }
}
