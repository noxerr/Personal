/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genhoraris;

/**
 *
 * @author dani__000
 */
import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {//Hereda de DefaultTableModel

    public ModeloTabla() {

    	//Dimensiona la tabla para la presentación inicial

    	setColumnCount(7);

    	setRowCount(30);

    }    

}   
