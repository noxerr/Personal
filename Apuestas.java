/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apuestas;

/**
 *
 * @author dani__000
 */

import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import javax.swing.*;

public class Apuestas extends JPanel implements ActionListener {

    private static final String SENTENCE = "  Match Chooser";
    public static final double InversionTotal = 50;
    public static Dimension size;
    public static double Cuota1 = 1.61;
    public static double CuotaX = 3.7;
    public static double Cuota2 = 5.5;
    
    public JFrame frame;
    public JLabel label, label2, label3, label4;
    public JTextArea input;
    public JDialog dial;
    public JTextField field, field2, field3;
    
    
    public void run(){
        setLayout(new BorderLayout());
        
        frame = new JFrame("Apuestas");
        frame.setAlwaysOnTop(true);
	frame.setFocusable(true);
        frame.addKeyListener(new MyKeyListener());
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel CreationPanel = new JPanel();
        CreationPanel.setLayout(new BorderLayout());
        
        label = new JLabel("RESULTADO");
        label2 = new JLabel("Local:");
        label3 = new JLabel("X:");
        label4 = new JLabel("Visit:");
        label.addKeyListener(new MyKeyListener());
        label.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        CreationPanel.add(label,BorderLayout.NORTH);
        
        input = new JTextArea("",3,10);
        field = new JTextField("",10);
        field2 = new JTextField("",10);
        field3 = new JTextField("",10);
        //dial = new JDialog(tiiis, "hola");
        // very important next 2 lines
        input.setLineWrap(true);
        input.setWrapStyleWord(true);        
        //input.getText(offs, len)
        //input.read(null, this);
        
        //Lay out the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridbag);

        //Create a regular text field.
        JTextField textField = new JTextField(10);
        textField.setActionCommand("textFieldString");
        textField.addActionListener(this);
        

        //Create a password field.
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setActionCommand("passwordFieldString");
        passwordField.addActionListener(this);

        //Create a formatted text field.
        JFormattedTextField ftf = new JFormattedTextField(
                java.util.Calendar.getInstance().getTime());
        ftf.setActionCommand("textFieldString");
        ftf.addActionListener(this);
        
        
        
        JLabel[] labels = {label2, label3, label4};
        JTextField[] textFields = {textField, passwordField, ftf};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.add(label, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Cuotas"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));

        
        
        
        
        
        //CreationPanel.add(new JScrollPane(input),BorderLayout.CENTER);
        CreationPanel.add(new JScrollPane(input),BorderLayout.BEFORE_FIRST_LINE);
        CreationPanel.add(new JScrollPane(field),BorderLayout.LINE_START);
        CreationPanel.add(new JScrollPane(field2),BorderLayout.CENTER);
        CreationPanel.add(new JScrollPane(field3),BorderLayout.SOUTH);
        
        //CreationPanel.add(new JScrollPane(dial),BorderLayout.WEST);

        frame.add(CreationPanel);
        frame.add(textControlsPane);
        
        //frame.add(dial);
        
        frame.pack();
        /*frame.setLocation(
        (int)(size.getWidth()-frame.getWidth())/2,
        (int)(size.getHeight()-frame.getHeight())/2);-*/
        frame.setVisible(true);
        
            
        
        input.append(SENTENCE);
        input.setCaretPosition( input.getText().length() );
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class MyKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
        }

        @Override
        public void keyTyped(KeyEvent event) {
        }

        @Override
        public void keyReleased(KeyEvent event) {
            int key = event.getKeyCode();
            //System.out.println("keyReleased="+KeyEvent.getKeyText(key));
            if (key == KeyEvent.VK_Q) {
                System.exit(0);
            }
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Runnable runnable2 = new Runnable(){
            @Override
            public void run(){
                Apuestas ap = new Apuestas();
                ap.run();
            }
        };
        Executors.newSingleThreadExecutor().execute(runnable2);
        
        
        double x, y, z, Porcent, Benef;
        boolean renta = false;
        DecimalFormat df = new DecimalFormat("0.000");
        //Cuota1 * x + CuotaX * y + Cuota2 * z = InversionTotal;
        // x + y + z = 40;
        z = InversionTotal/Cuota2;
        y = InversionTotal/CuotaX;
        x = InversionTotal - z - y;
        Benef = x * Cuota1 - InversionTotal;
        if (Benef > 0) renta = true;
        if (renta) Porcent = Benef / InversionTotal * 100;
        else Porcent = 0;
        //x = (InversionTotal - CuotaX * y - Cuota2 * z) / Cuota1;
        
        System.out.println("X: " + df.format(x) + " Y: " + df.format(y) + " z: " + df.format(z) + "\n"
                + "Beneficios: " + df.format(Benef) + " Porcent: " + df.format(Porcent));
    }
    
    
    
    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }
}
