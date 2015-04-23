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

import javax.swing.JButton;

public class PanelBotonesConsulta extends JPanel{

    public JButton consultar, salir;

    public PanelBotonesConsulta() {

    	iniciaComponentes();

    	agregaComponentes();

    }

    private void iniciaComponentes(){

    	consultar =new JButton("Consultar");

    	consultar.setMnemonic('c');

    	salir =new JButton("Salir");

    	salir.setMnemonic('s');

    }

    private void agregaComponentes(){

    	add(consultar);

    	add(salir);

    } 

}  
