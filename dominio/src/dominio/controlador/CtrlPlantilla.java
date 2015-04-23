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
    
    public static int[] cargarPlantilla(String nom){
        //llamar a la capa de persistencia que nos cargue una plantilla
        int[] lista = new int[9];
        lista[0] = 7;
        lista[1] = 9;
        lista[2] = 0;
        lista[3] = 1;
        lista[4] = 2;
        lista[5] = 4;
        lista[6] = 8;
        lista[7] = 3;
        lista[8] = 13;
        //int[] lista = null;
        return lista;
    }
    
    public static void guardarPlantilla(Object[] ob){
        //llamar a la capa de persistencia que guarde la plantilla que nos han pasado
        Map<String, Integer> mapa = new HashMap<String,Integer>();
        mapa = (Map)ob[1];
        for (String clave : mapa.keySet()) {   
            Integer valor = mapa.get(clave);
            //System.out.println("Clave: " + clave + ": " + valor);
        }
    }
    
    public static String[] cargarListaPlantillas(){
        String[] ret = null;
        return ret;
    }
}
