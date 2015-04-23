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
import javax.swing.JDialog;

public class DialogoAutenticador extends JDialog {//Obsérvese que hereda de JDialog

    public PanelAutenticador panel;

    /*Este programa sólo sirve para 

     *mostrar el panel autenticador

     *en un cuadro de diálogo*/

    public DialogoAutenticador() {

    	panel=new PanelAutenticador();//Una instancia de nuestro panel autenticador

    	add(panel);//agrega el panel autenticador

    	setSize(300,150); //Dimensiona el diálog   	

    }

}
