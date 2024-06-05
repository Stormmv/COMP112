// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2023T1, Assignment 3
 * Name: Michael Visser 
 * Username: vissermich
 * ID: 300652084
 */

import ecs100.*;

/** Program to create simple animated animal character using the
 *  Animal class.  
 */

public class PetShow{

    public static final double EXIT_LEFT = 200;    
    public static final double EXIT_RIGHT = 700;    
    public static final double START_LEFT = 300;    
    public static final double START_RIGHT = 600;  
    /** animate creates three animals (randomly situated),
     *   that performs the same routine.
     *   Then the left and right most animals will compete
     *      towards their respective exit (the closest to them).
     *   The animal in the middle (horizontally) remains still.
     *   The animation stops when one of them have crossed their
     *      respective exit line.
     */
    public void animate(){
        UI.clearGraphics();
        UI.drawLine(EXIT_LEFT, 10, EXIT_LEFT, 700);
        UI.drawLine(EXIT_RIGHT, 10, EXIT_RIGHT, 700);
        double a1Start = Math.random()*(START_RIGHT-START_LEFT)+START_LEFT;
        double a2Start = Math.random()*(START_RIGHT-START_LEFT)+START_LEFT;
        double a3Start = Math.random()*(START_RIGHT-START_LEFT)+START_LEFT;
        Animal a1 = new Animal("dog", "Scruff", a1Start, 50);
        Animal a2 = new Animal("snake", "Felix", a2Start, 150);
        Animal a3 = new Animal("bird", "Tweety", a3Start, 250);
        Animal left = closestToLeft(a1, a2, a3);
        Animal right = closestToRight(a1, a2, a3);
        routine(a1);
        routine(a2);
        routine(a3);
        while (left.getX() > EXIT_LEFT && right.getX() < EXIT_RIGHT){
            left.goLeft();
            right.goRight();
            UI.sleep(100);
        }    }

    public Animal closestToRight(Animal a1, Animal a2, Animal a3){
        if (a1.getX() > a2.getX() && a1.getX() > a3.getX()){
            return a1;
        }
        else if (a2.getX() > a1.getX() && a2.getX() > a3.getX()){
            return a2;
        }
        else if (a3.getX() > a1.getX() && a3.getX() > a2.getX()){
            return a3;
        }
        else{
            return null;
        }
    }

    public void routine(Animal a){
        String[] array = new String[5];
        array[0] = "I'm going to win";
        array[1] = "Hello everyone";
        array[2] = "Zooming";
        array[3] = "I'm the best";
        array[4] = "I'm ready to race";
        String random = array[(int)(Math.random()*5)];
        a.speak(random);
        UI.sleep(1000);
        a.jump(10);
        
    }

    /** return the animal closest to the left */
    public Animal closestToLeft (Animal a1, Animal a2, Animal a3){
        if (a1.getX() < a2.getX() && a1.getX() < a3.getX()){
            return a1;
        }
        else if (a2.getX() < a1.getX() && a2.getX() < a3.getX()){
            return a2;
        }
        else if (a3.getX() < a1.getX() && a3.getX() < a2.getX()){
            return a3;
        }
        else{
            return null;
        }
    }



    /** Make buttons to let the user run the methods */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Animate", this::animate );
        UI.addButton("Quit", UI::quit );
        UI.setWindowSize((int)(EXIT_RIGHT+100), 700);
        UI.setDivider(0);       // Expand the graphics area
    }

    public static void main(String[] args){
        PetShow ps = new PetShow();
        ps.setupGUI();
    }
}

