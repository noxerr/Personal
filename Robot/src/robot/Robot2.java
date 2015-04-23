package robot;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;
//import java.awt.geom.Point2D;


/**
 * SpinBot - a sample robot by Mathew Nelson, and maintained by Flemming N. Larsen
 * <p/>
 * Moves in a circle, firing hard when an enemy is detected
 */
public class Robot2 extends AdvancedRobot {

	/**
	 * SpinBot's run method - Circle
	 */
        @Override
	public void run() {
		// Set colors
		setBodyColor(Color.white);
		setGunColor(Color.blue);
		setRadarColor(Color.black);
		setScanColor(Color.red);

		// Loop forever
		while (true) {
			// Tell the game that when we take move,
			// we'll also want to turn right... a lot.
			setTurnRight(10000);
			// Limit our speed to 5
			setMaxVelocity(5);
			// Start moving (and turning)
			ahead(10000);
			// Repeat.
		}
	}

	/**
	 * onScannedRobot: Fire hard!
     * @param e
	 */
        @Override
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
	}

	/**
	 * onHitRobot:  If it's our fault, we'll stop turning and moving,
	 * so we need to turn again to keep spinning.
     * @param e
	 */
        @Override
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			if (e.getBearing() > -10 && e.getBearing() < 10) fire(3);
			turnRight(10);
		}
	}

    /*public Point2D.Double guessPosition(long when) {
    /**time is when our scan data was produced.  when is the time 
    that we think the bullet will reach the target.  diff is the 
    difference between the two **/
 /*   double diff = when - time;
    double newX, newY;
    /**if there is a significant change in heading, use circular 
    path prediction**/
   /* if (Math.abs(changehead) > 0.00001) {
        double radius = speed/changehead;
        double tothead = diff * changehead;
        newY = y + (Math.sin(heading + tothead) * radius) - 
                      (Math.sin(heading) * radius);
        newX = x + (Math.cos(heading) * radius) - 
                      (Math.cos(heading + tothead) * radius);
    }
    /**if the change in heading is insignificant, use linear 
    path prediction**/
   /* else {
        newY = y + Math.cos(heading) * speed * diff;
        newX = x + Math.sin(heading) * speed * diff;
    }
    return new Point2D.Double(newX, newY);
    }
    
    
    /**This function predicts the time of the intersection between the 
bullet and the target based on a simple iteration.  It then moves 
the gun to the correct angle to fire on the target.**/
/*void doGun() {
    long time;
    long nextTime;
    Point2D.Double p;
    p = new Point2D.Double(target.x, target.y);
    for (int i = 0; i < 10; i++){
        nextTime = 
    (intMath.round((getRange(getX(),getY(),p.x,p.y)/(20-(3*firePower))));
        time = getTime() + nextTime;
        p = target.guessPosition(time);
    }
    /**Turn the gun to the correct angle**/
  /*  double gunOffset = getGunHeadingRadians() - 
                  (Math.PI/2 - Math.atan2(p.y - getY(), p.x - getX()));
    setTurnGunLeftRadians(normaliseBearing(gunOffset));
}

/*double normaliseBearing(double ang) {
    if (ang > Math.PI)
        ang -= 2*Math.PI;
    if (ang < -Math.PI)
        ang += 2*Math.PI;
    return ang;
}

public double getrange(double x1,double y1, double x2,double y2) {
    double x = x2-x1;
    double y = y2-y1;
    double h = Math.sqrt( x*x + y*y );
    return h;	
}*/
    


}