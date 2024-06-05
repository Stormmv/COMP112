// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2023T1, Assignment 6
 * Name: Michael Visser
 * Username: vissermich
 * ID: 300652084
 */

import ecs100.*;
import java.awt.Color;

/** Ball represents a ball that is launched by the mouse towards a direction.
 *    Each time the step() method is called, it will take one step.  
 */

public class Ball{

    // Constants for all balls: size, position of the ground
    public static final double DIAM = 16;  // diameter of the balls.
    public static final double GROUND = BallGame.GROUND;
    public static final double RIGHT_END = BallGame.RIGHT_END;

    // Fields to store state of the ball:
    // x position, height above ground, stepX, stepY, colour
    //   The ball should initially be not moving at all. (step should be 0)
    //   The colour should be a random colour.

    
    private double x;
    private double height;
    private double stepX;
    private double stepY;
    private Color colour;

    // Constructor
    /** Construct a new Ball object.
     *	Parameters are the initial position (x and the height above the ground),
     *	Stores the parameters into fields 
     *	and initialises the colour to a random colour
     *  SHOULD NOT DRAW THE BALL!
     */
    public Ball(double x, double h){
        this.x = x;
        this.height = h;
        this.stepX = 0;
        this.stepY = 0;
        this.colour = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }   

    // Methods
    /**
     * Draw the ball on the Graphics Pane in its current position
     * (unless it is past the RIGHT_END )
     */
    public void draw(){
        UI.setColor(this.colour);
        UI.fillOval(this.x, this.height, DIAM, DIAM);
    }

    /**
     * Move the ball one step (DO NOT REDRAW IT)
     *    Change its height and x position using the vertical and horizonal steps
     *    Reduce its vertical speed each step (due to gravity), 
     *    If it would go below ground, then change its y position to ground level
     *      and set the  vertical speed back to 0.
     */
    public void step(){
        this.height = this.height - this.stepY;
        this.x = this.x + this.stepX;
        this.stepY = this.stepY - 0.2;
        if (this.height > GROUND){
            this.height = GROUND;
            this.stepY = 0;
        }
    }

    /**
     * Set the horizontal speed of the ball: how much it moves to the right in each step.
     * (negative if ball going to the left).
     */
    public void setXSpeed(double xSpeed){
        this.stepX = xSpeed;
    }

    /**
     * Set the vertical speed of the ball: how much it moves up in each step
     * (negative if ball going down).
     */
    public void setYSpeed(double ySpeed){
        this.stepY = ySpeed;
    }

    /**
     * Return the height of the ball above the ground
     */
    public double getHeight(){
        return this.height;
    }

    /**
     * Return the horizontal position of the ball
     */
    public double getX(){
        return this.x;
    }


}
