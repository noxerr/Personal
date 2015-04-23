import java.awt.*;
import javax.swing.*;

// Has an instance of frame, does not need to extend it.
public class ClassCreation { //extends JFrame {

    private JFrame frame;
    private JLabel instructionlabel;
    // as mentioned by talnicolas
    private JTextArea inputUML;

    // Don't give a method the same name as a class!!
    //public void ClassCreation(){
    public void initGui(){
        frame = new JFrame();
        //frame.setSize(300, 400);  //pack() instead!
        //frame.setLocationRelativeTo(null);    // do something better
        frame.setLocationByPlatform(true);  // better!
        
        //frame.setVisible(true);   // do later
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Class Design");

        JPanel CreationPanel = new JPanel();
        CreationPanel.setLayout(new BorderLayout());

        instructionlabel = new JLabel("Fill Class details in using UML");
        CreationPanel.add(instructionlabel,BorderLayout.NORTH);

        inputUML = new JTextArea("",7,30);
        // very important next 2 lines
        inputUML.setLineWrap(true);
        inputUML.setWrapStyleWord(true);
        // add it to a scrollpane
        CreationPanel.add(new JScrollPane(inputUML),BorderLayout.CENTER);

        frame.add(CreationPanel);

        frame.pack();   // assume the natural size!
        frame.setVisible(true);

        for (int ii=0; ii<150; ii++) {
            
            inputUML.append(SENTENCE);
            inputUML.setCaretPosition( inputUML.getText().length() );
        }
    }

    public static void main(String[] args) {
        // Swing GUIs should be created and altered on the EDT.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClassCreation cc = new ClassCreation();
                cc.initGui();
            }
        });
    }

    private static String SENTENCE = "The quick brown fox jumps over the lazy dog!  ";
}