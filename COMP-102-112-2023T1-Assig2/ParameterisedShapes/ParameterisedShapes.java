// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2023T1, Assignment 2
 * Name: Michael Visser
 * Username: vissermich
 * ID: 300652084
 */

import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;


/** Parameterised Shapes: 
 * Pass/Fail level: draw rectangles with three vertical stripes
 * Challenge: draw the flag of China
 */
public class ParameterisedShapes{

    /**
     * Asks user for a position, three colours, three widths and whether the circles are filled.
     * Then calls the drawFancyRect method, passing the appropriate arguments
     */
    public void doFancyRect(){
        double left = UI.askDouble("Left of flag");
        double top = UI.askDouble("Top of flag");
        UI.println("Now choose the colours");
        Color col1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color col2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color col3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        UI.println("Now choose the sizes");
        double width1 = UI.askDouble("Width of first stripe");
        double width2 = UI.askDouble("Width of second stripe");
        double width3 = UI.askDouble("Width of third stripe");
        boolean filled = UI.askBoolean("Filled circles?");
        this.drawFancyRect(left, top, col1, col2, col3, width1, width2, width3, filled);
    }

    /** 
     * Calculates the total height and width of the rectangle.
     * The height of the rectangle is 2/3 the width of the rectangle.
     * It then calls drawStripe three times to draw the three stripes,
     * and outlines the rectangle with a black contour.
     */
    public void drawFancyRect(double left, double top, Color col1, Color col2, Color col3, double width1, double width2, double width3, boolean filled){
        UI.clearGraphics();
        double height = 2.0/3.0 * (width1 + width2 + width3);
        double diameter = width1 * 0.2;
        double c1 = top + height/6 - diameter/2;
        double diameter2 = width2 * 0.2;
        double c2 = top + height/2 - diameter2/2;
        double diameter3 = width3 * 0.2;
        double c3 = top + height*5/6 - diameter3/2;
        this.drawStripe(left, top, col1, width1, height, filled, c1, diameter);
        this.drawStripe(left + width1, top, col2, width2, height, filled, c2, diameter2);
        this.drawStripe(left + width1 + width2, top, col3, width3, height, filled, c3, diameter3);
        UI.setColor(Color.black);
        UI.drawRect(left, top, width1 + width2 + width3, height);
    }

    /**
     * Draws a stripe at the given position that has the right circle at the right place.
     * diameter of the circle is 1/3 the height of the stripe.
     * for left stripe, circle is top third of stripe
     * for middle stripe, circle is middle third of stripe
     * for right stripe, circle is bottom third of stripe
     * 
     */
    public void drawStripe(double left, double top, Color col, double width, double height, boolean filled, double c, double diameter){
        UI.setColor(col);  
        UI.fillRect(left, top, width, height);
        if (filled){
            UI.setColor(Color.black);
            UI.fillOval(left + width/2 - diameter/2, c, diameter, diameter);
        }
        else{
            UI.setColor(Color.black);
            UI.drawOval(left + width/2 - diameter/2, c, diameter, diameter);
        }   
    }

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes );
        UI.addButton("Fancy Rect", this::doFancyRect );
        // Add a button here to call your method for the challenge part
        UI.addButton("Quit", UI::quit );
    }

    public static void main(String[] args){
        ParameterisedShapes ps = new ParameterisedShapes ();
        ps.setupGUI();
    }

}
