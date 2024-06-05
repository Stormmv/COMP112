// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102/112 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2023T1, Assignment 7
 * Name: Michael Visser
 * Username: vissermich
 * ID: 300652084
 */

import ecs100.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.io.PrintStream;
import java.awt.Color;
import javax.swing.JButton;

/** The PetWorld program allows the user to create, save, and reload files
 *    specifying a world consisting of a list of animal objects.
 *    The program allows the user to
 *      - add a new animal to the world
 *      - remove an animal from the world
 *      - move a animal to a different position
 *      - make the animal turn. speak
 *      - save the current world to a file
 *      - load a previous world from a file.
 *        
 *    Classes
 *      The PetWorld class handles all the user interaction:
 *        buttons, textfields, mouse actions, file opening and closing.
 *        It stores the current world in an ArrayList of Animal .
 *
 *      The Animal class
 *
 *    Files:
 *      A world is stored in a file containing one line for each animal,
 *        
 *    User Interface:
 *        There are buttons for dealing with the whole world (New, Open, Save),
 *         a button and a textfiled for specifying the next animal to add, and
 *         buttons for removing and moving animals, as well at making them 
 *         do something.
 */

public class PetWorld {

    // Fields
    private String[] types = new String[] {"bird", "dinosaur", "dog", "grasshopper",
                                           "snake", "tiger", "turtle"};

    private ArrayList<Animal> animal = new ArrayList<Animal>();
    private Animal selected;
    private String type;
    private String direction = "left";
    private String speech;
    private String action = "select";
    private javax.swing.JButton add;
    private javax.swing.JButton animaltype;
    private javax.swing.JButton groupselect;
    private ArrayList<Animal> selectedanimal = new ArrayList<Animal>();
    private double startX;
    private double startY;
    private boolean move = false;
    /**
     * User interface has buttons for the actions and text field to enter animal's name
     * You will need to implement the methods here, or comment out the button.
     */
    public void setupGUI(){
        UI.addButton("Quit", UI::quit);
        UI.addButton("New", this::startWorld);
        UI.addButton("Save", this::save);
        UI.addButton("Load", this::load);
        animaltype = UI.addButton("Set Animal Type", this::setAnimalType);
        UI.addTextField("Direction", this::setDirection);
        add = UI.addButton("Add", this::setToAdd);
        groupselect = UI.addButton("Group Select: OFF", this::setGroupSelect);
        UI.addButton("Delete", this::delete);
        UI.addButton("Move", this::move);
        UI.addButton("Turn", this::turn);
        UI.addTextField("Speech", this::setSpeech);
        UI.addButton("Speak", this::speak);
        UI.setMouseListener(this::doMouse);
    }

    // Methods to add an animal
    /**
     * Ask the user for a destination station name and assign it to the destinationName field
     * Must pass a collection of the names of the stations to getOptionFromList
     */
    public void setAnimalType(){
        String type = getOptionFromList("Choose animal type", types);
        if (type==null ) {return;}
        UI.printMessage("Setting animal type to "+type);
        this.type = type;
        this.animaltype.setText("Animal Type Set To: "+ type);
    }

    /**
     * Set the direction the animal is facing when created
     */
    public void setDirection (String dir){
        this.direction = dir;
    }

    public void setToAdd(){ 
        this.action = "Add";
        this.add.setText("ADD");
    }

    /** Construct a new Animal object of the appropriate type and direction
     *    Do not create an animal if it does not have a type
     *    Adds the animal to the end of the collection.
     */
    public void addAnimal(double x, double y){
        if(type != null){
            Animal a1 = new Animal(this.type, this.direction, x, y);
            animal.add(a1);
        }
        else{
        UI.println("Please select an animal type");
        }
    }

    // Respond to mouse events 
    /** When mouse is pressed, remember the position in fields
     *  and also find the animal it is on (if any), and store
     *  the animal in a field (use the findAnimal(..) method)
     *  When the Mouse is released, depending on the currentAction,
     *  - perform the action (move, delete, or resize).
     *    move and resize are done on the animal where the mouse was pressed,
     *    delete is done on the animal where the mouse was released 
     *  - construct the animal and add to the world ArrayList,
     *    (though the polygon is more complicated)
     *  - redraw the drawing.
     *  It is easiest to call other methods (see below) to actually do the work,
     *  otherwise this method gets too big!
     */
    public void doMouse(String mouseAction, double x, double y) {
        if (mouseAction.equals("pressed")){
            startX = x;
            startY = y;
            if (this.action.equals("select")){
                if(!this.selectedanimal.isEmpty()){
                    this.selectedanimal.get(0).unselect();
                    this.selectedanimal.clear();
                }
                Animal clicked = findAnimal(x,y);
                if (clicked !=null){
                    this.selectedanimal.add(clicked);
                    this.selectedanimal.get(0).select();
                }
                this.action = "select";
            }    
        }
        else if (mouseAction.equals("released")){
            if (this.action == "Add"){
                addAnimal(x,y);
                this.action = "select";
                this.add.setText("Add");
            }
            if (this.move == true){
                if (!this.selectedanimal.isEmpty() && this.selectedanimal.get(0).on(x,y) == false){
                    this.selectedanimal.get(0).moveTo(x,y);
                    this.action = "select";
                }
                if (this.selectedanimal.isEmpty()){
                    UI.println("No animal selected");
                }      
            }
            if (this.action.equals("groupSelect")){
                double moveX = x - startX;
                double moveY = y - startY;
                for(Animal a : selectedanimal){
                a.addToPosition(moveX, moveY);
                }
            
            }
            if (this.action.equals("groupSelect")){
                Animal clicked = findAnimal(x,y);
                if (clicked !=null){
                    clicked.select();
                    this.selectedanimal.add(clicked);
                }
                if(clicked == null){
                    for (Animal a : this.selectedanimal){
                    a.unselect();
                    }
                }
            }
            if (this.action.equals("select")){
                if(!this.selectedanimal.isEmpty()){
                    this.selectedanimal.get(0).unselect();
                    this.selectedanimal.clear();
            }
            Animal clicked = findAnimal(x,y);
            if (clicked !=null){
                this.selectedanimal.add(clicked);
                this.selectedanimal.get(0).select();
            }
            this.action = "select";
            }
        }
        this.drawWorld();
    }
    

    /** Draws all the animals in the list on the graphics pane
     *  First clears the graphics pane, then draws each animal,
     *  Finally repaints the graphics pane
     */
    public void drawWorld(){
        UI.clearGraphics();
        /*# YOUR CODE HERE */
        for(Animal a: animal){
            a.draw();
        }

    }   

    /** Checks each animal in the list to see if the point (x,y) is on the animal.
     *  It returns the topmost animal for which this is true.
     *     Returns null if there is no such animal.
     */
    public Animal findAnimal(double x, double y){
        /*# YOUR CODE HERE */
        boolean check = false;
        Animal found = this.selected;
        for  (Animal a : animal){
            check = a.on(x,y);
            if (check){
            found = a;
            break;
            }
        }
            if (check == false){
        return null;
        }
        return found;
        // failed to find any animal that the point was over
    
}

    /** Start a new world -
     *  initialise the world ArrayList and clear the graphics pane. 
     */
    public void startWorld(){
        /*# YOUR CODE HERE */
        boolean restart = false;
        if (!this.animal.isEmpty())
            restart = UI.askBoolean("Do you want to restart?");
            if (restart) { 
            UI.clearGraphics();
            animal.clear();
            this.groupselect.setText("Group Select: OFF");
            }
    }

    // Add here methods to delete, turn, speak, move, load and save
    /*# YOUR CODE HERE */
    public void setGroupSelect(){
        if (this.action.equals("select")){
        this.action = "groupSelect";
        this.groupselect.setText("Group Select: ON");
        }
        else if (this.action.equals("groupSelect")){
        this.action = "select";
        this.groupselect.setText("Group Select: OFF");
        for (Animal a : selectedanimal){
            a.unselect();
            }
        this.selectedanimal.clear();
        drawWorld();
        }
    }
    
    public void delete(){
        if (this.selectedanimal.isEmpty()){
            UI.println("Please select an animal type");
            return;
        }
        for(int i = 0; i <= selectedanimal.size()-1; i++){
        animal.remove(this.selectedanimal.get(i));
        }
        this.drawWorld();
    }
    
    public void turn(){
        if (this.selectedanimal.isEmpty()){
            UI.println("Please select an animal type");
            return;
        }
        for (Animal a : selectedanimal){
            a.turn();
        }
        this.drawWorld();
    }
    
    public void speak(){
        if (this.selectedanimal.isEmpty()){
            UI.println("Please select an animal type");
            return;
        }
        for(int i = 0; i <= selectedanimal.size()-1; i++){
            this.selectedanimal.get(i).speak(this.speech);   
        }
    }
    
    public void setSpeech(String speech){
        this.speech = speech;
    }
    
    public void move(){
        if (this.move == false){
        this.move = true;
        }
        if (this.move == true){
        this.move = false;
        }
    }
    
    public void load(){
        this.animal.clear();
        this.drawWorld();
        try{
            Scanner scan = new Scanner(Path.of(UIFileChooser.open("Animal file")));
            while (scan.hasNext()){
                double x = scan.nextDouble();
                double y = scan.nextDouble();
                String type = scan.next();
                String direction = scan.next();
                
                Animal a = new Animal(type, direction, x, y);
                this.animal.add(a);
            }
        } catch(Exception e) {UI.println("Error:" + e);}
    }
    
    public void save(){
        try{
            PrintStream stream = new PrintStream(UIFileChooser.save("Animal File"));
            for (Animal a : animal){
                stream.println(a.toString());
            }
        } catch (FileNotFoundException e) {UI.println("Error:" + e);}
    }

    /**
     * Method to get a string from a dialog box with an array options
     */
    public String getOptionFromList(String question, String[] options){
        return (String)javax.swing.JOptionPane.showInputDialog
        (UI.getFrame(),
            question, "",
            javax.swing.JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0].toString());
    }

    /**
     * main method: set up the user interface
     */
    public static void main(String[] args){
        PetWorld petWorld = new PetWorld();
        petWorld.setupGUI();   // set up the interface
    }

}

