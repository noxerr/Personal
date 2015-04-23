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
import javax.swing.JPanel;

import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.JPasswordField;

import javax.swing.JButton;

import java.awt.GridLayout;



//Obsérvese que esta clase es un panel

public class PanelAutenticador extends JPanel {

	//Crea los componentes necesarios:

	//3 cuadros de texto

	public JTextField servidor, usuario, base;

	//Un cuadro de texto para contraseñas

	public JPasswordField password;

	//Dos botones

	public JButton aceptar, cancelar;

    public PanelAutenticador() {

    	iniciaComponentes();

    	agregaComponentes();

    }    

    private void iniciaComponentes(){

    	servidor=new JTextField();

    	usuario=new JTextField();

    	password=new JPasswordField();

    	base=new JTextField();

    	

    	aceptar=new JButton("Aceptar");

    	aceptar.setMnemonic('a');//Subraya la A para activar el atajo de tecla 

    	cancelar=new JButton("Cancelar");

    	cancelar.setMnemonic('c'); //Subraya la C para activar el atajo de tecla 

    	

    }

    private void agregaComponentes(){

    	/*Esta línea convierte al panel en una rejilla  de cinco filas y dos columnas,

*que acomoda los componentes, conforme son agregados, de izquierda a 

*derecha y de arriba abajo

*/

    	setLayout(new GridLayout(5,2));//La manera de acomodar los componentes es una rejilla de 5x2

    	//Agrega los componentes al panel según la rejilla de izquierda a derecha y de arriba a abajo

add(new JLabel("Servidor",JLabel.RIGHT));

    	add(servidor);

    	add(new JLabel("Usuario",JLabel.RIGHT));

    	add(usuario);

    	add(new JLabel("Contraseña",JLabel.RIGHT));

    	add(password);

    	add(new JLabel("Base de datos",JLabel.RIGHT));

    	add(base);

    	add(aceptar);

    	add(cancelar);

    }

}      
