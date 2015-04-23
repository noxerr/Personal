import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.awt.Robot;

/*
Aquesta clase no la explico ja que nomes es un keylistener y un frame molt simple amb un botó.
*/


public class GhostMouse{
    public static Dimension size;

    public void run(){
        //Robot robot = new Robot();
        size = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("Ghost Mouse");
        JButton button = new JButton("Mou punter");
        //button.addActionListener(new CircleListener(robot));
        button.addKeyListener(new MyKeyListener());
        //frame.addWindowFocusListener(null);
        //frame.hasFocus();
        //frame.isAutoRequestFocus();
        //frame.isAlwaysOnTop();
        //frame.requestFocus();
        //frame.setAlwaysOnTop(true);
        /*frame.setDefaultCloseOperation(operation);
        DO_NOTHING_ON_CLOSE (defined in WindowConstants): Don't do anything; require the program to handle the operation in the windowClosing method of a registered WindowListener object.
        HIDE_ON_CLOSE (defined in WindowConstants): Automatically hide the frame after invoking any registered WindowListener objects.
        DISPOSE_ON_CLOSE (defined in WindowConstants): Automatically hide and dispose the frame after invoking any registered WindowListener objects.
        EXIT_ON_CLOSE (defined in JFrame): Exit the application using the System exit method. Use this only in applications.
        */
        //frame.setMinimumSize(size);
        //frame.setSize(size);
        frame.setAlwaysOnTop(true);
	frame.setFocusable(true);
	frame.addKeyListener(new MyKeyListener());
        frame.getContentPane().add(button);
        frame.pack();
        frame.setLocation(
        (int)(size.getWidth()-frame.getWidth())/2 - 350,
        (int)(size.getHeight()-frame.getHeight())/2);
        frame.setVisible(true);
    }
}

class MyKeyListener implements KeyListener {
    @Override
    public void keyPressed(KeyEvent event) {
    }
    
    @Override
    public void keyTyped(KeyEvent event) {
	int key = event.getKeyCode();
	//System.out.println("keyPressed="+KeyEvent.getKeyText(key));
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


/*class CircleListener implements ActionListener {
    Robot robot;
    public CircleListener(Robot robot) {
        this.robot = robot;
    }

    public void actionPerformed(ActionEvent evt) {
        int originx = (int)GhostMouse.size.getWidth()/2;
        int originy = (int)GhostMouse.size.getHeight()/2;
        double pi = 3.1457;

        for(double theta = 0; theta < 4*pi; theta=theta+0.1) {
            double radius = theta * 20;
            double x = Math.cos(theta) * radius + originx;
            double y = Math.sin(theta) * radius + originy;
            robot.mouseMove((int)x,(int)y);
            try{Thread.sleep(25);} catch (Exception ex) { }
        }
    }
}*/