// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2023T1, Assignment 4
 * Name: Michael Visser
 * Username: vissermich
 * ID: 300652084
 */

import ecs100.*;
import java.awt.Color;

/** PatternsDrawer: draws various repetitive patterns. */

public class PatternsDrawer{

    public static final double PATTERN_LEFT = 50.0;   // Left side of the pattern
    public static final double PATTERN_TOP = 50.0;    // Top of the pattern
    public static final double PATTERN_SIZE = 300.0;  // The size of the pattern on the window

    /** Draw a checkered board with alternating black and white squares
     *    Asks the user for the number of squares on each side
     */
    public void drawDraughtsBoard(){
        UI.clearGraphics();
        UI.setColor(Color.black);
        int num = UI.askInt("How many rows:");
        
        double squareSize = PATTERN_SIZE / num;
        double x = PATTERN_LEFT;
        double y = PATTERN_TOP;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if ((i + j) % 2 == 0) {
                    UI.fillRect(x, y, squareSize, squareSize);
                }
                x += squareSize;
            }
            x = PATTERN_LEFT;
            y += squareSize;
        }
        
        UI.setColor(Color.black);
        UI.drawRect(PATTERN_LEFT, PATTERN_TOP, PATTERN_SIZE, PATTERN_SIZE);
    }

    /** TriGrid
     * a triangular grid of squares that makes dark circles appear 
     * in the intersections when you look at it.
     */
    public void drawTriGrid(){
        UI.clearGraphics();
        UI.setColor(Color.black);
        int num = UI.askInt("How many rows:");
        double triSize = PATTERN_SIZE / num;
        double x = PATTERN_LEFT;
        double y = PATTERN_TOP;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num - i; j++) {
                UI.fillRect(x, y, triSize, triSize);
                x += triSize + triSize / 4;
            }
            x = PATTERN_LEFT;
            y += triSize + triSize / 4;
        }

    }


    /**   CHALLENGE
     * DO NOT DO THIS IF YOU HAVE NOT DONE THE PREVIOUS METHODS
     * Draw a board made of hexagonals
     *  Asks the user for the number of hexagonals on each side
     */
    public void drawHexagonalBoard(){
        /*# YOUR CODE HERE */

    }

    /** Draw a Spiral board consisting of a square spiral path of small white squares.
     *  Asks the user for the number of squares along the top
     *
     * CHALLENGE
     */
    public void drawSpiralBoard(){
        /*# YOUR CODE HERE */

    }

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear",UI::clearPanes);
        UI.addButton("Draughts", this::drawDraughtsBoard);
        UI.addButton("TriGrid", this::drawTriGrid);
        UI.addButton("CHALLENGE: Hexagon", this::drawHexagonalBoard);
        UI.addButton("CHALLENGE Spiral", this::drawSpiralBoard);
        UI.addButton("Quit",UI::quit);
    }   

    public static void main(String[] arguments){
        PatternsDrawer pd = new PatternsDrawer();
        pd.setupGUI();
    }

}
