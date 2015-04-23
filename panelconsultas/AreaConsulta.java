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
import javax.swing.JScrollPane;

import javax.swing.JTextArea;

public class AreaConsulta extends JScrollPane{//Esta clase es un panel desplazable

	public JTextArea texto;

    public AreaConsulta() {

    	texto=new JTextArea(4,30);

    	texto.setLineWrap(true);//Hace que las líneas corten en el límite del área

    	texto.setWrapStyleWord(true);//Hace que corten sólo en palabras completas

    	setViewportView(texto);//Dentro de las barras se verá le área de texto

    } 

}    
