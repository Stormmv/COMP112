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
import java.lang.reflect.Array;
import java.util.*;

/** 
 * The program contains several methods for analysing and displaying
 *   the profile of a road, based on GPS measurements every 10 meters
 *   along a section of the road.
 *  There are several things about the profile that a user may be
 *    interested in, especially
 *    - The average and maximum height
 *    - A visual plot of the road profile
 */
public class RoadProfiler{

    public static final double LEFT = 30;       // where the start of the road segment will be plotted
    public static final double SEA_LEVEL = 400; // heights will be plotted as a distance above y = SEA_LEVEL
    public static final double STEP = 10;       // horizontal distance along road between height measurements


    /**
     *  analyseProfile() reads a sequence of heights from the user,
     *  prints out average and maximum height and plots a profile of the road
     *  by calling appropriate methods
     */
    public void analyseProfile(){
        UI.clearPanes();
        ArrayList<Double> listOfHeights = UI.askNumbers("Enter profile levels, end with 'done': ");
        if (listOfHeights.size() != 0) {
            this.printAverageHeight(listOfHeights);
            UI.printf("Highest point was:  %.2f\n", this.maximumHeight(listOfHeights));
            this.displayProfile(listOfHeights);
        }
        else {
            UI.println("No readings");
        }
    }

    /**
     * Print the average height
     *   Assumes there is at least one number in the list.
     */
    public void printAverageHeight(ArrayList<Double> listOfHeights) {
        double sum = 0;
        for (double height : listOfHeights) {
            sum += height;
        }
        double average = sum / listOfHeights.size();
        UI.printf("Average height was: %.2f\n", average);
    }

    /**
     *  Find and return the maximum level in the list
     *   Assumes there is at least one number in the list.
     */
    public double maximumHeight(ArrayList<Double> listOfHeights) {
        double max = listOfHeights.get(0);
        for (double height : listOfHeights) {
            if (height > max) {
                max = height;
            }
        }
        return max;
	
    }

    /**
     * Plot a continuous profile of the road segment,
     *   that join up adjacent points
     *   assuming that 0 m (sealevel) is at y=SEA_LEVEL 
     */
    public void displayProfile(ArrayList<Double> listOfHeights) {
        UI.clearGraphics();
        UI.drawLine(LEFT, SEA_LEVEL, LEFT+1000, SEA_LEVEL); // draw the base line to show sea level

        double x = LEFT;
        double y = SEA_LEVEL - listOfHeights.get(0);
        
        for (int i = 1; i < listOfHeights.size(); i++) {
            double nextX = x + STEP;
            double nextY = SEA_LEVEL - listOfHeights.get(i);
            UI.drawLine(x, y, nextX, nextY);
            x = nextX;
            y = nextY;
        }
        UI.println("Finished plotting");
    }

    /**
     * Set up the Gui with buttons
     */
    public void setupGUI() {
        UI.initialise();
        UI.addButton("Analyse", this::analyseProfile );
        UI.addButton("Quit", UI::quit );
    }

    /**
     * main: construct object and call setup GUI.
     */
    public static void main(String[] args) {
        RoadProfiler rp = new RoadProfiler();
        rp.setupGUI();
    }

}
