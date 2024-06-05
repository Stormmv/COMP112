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

/**
 * Asks user for a key word and checks whether it meets a set of rules or not.
 */

public class KeyValidator {

    /**
     * Asks user for key word and a name and then checks if it is a valid key word.
     */
    public void checkKey(){
        UI.clearText();
        String key = UI.askString("Key:   ");
        String name = UI.askString("Your name:   ");
        UI.println();
        this.validateKey(key, name);
    }

    /**
     * Report that the key is valid or report ALL the rules that the key failed.
     */
    public void validateKey(String key, String name){
        boolean valid = true;
        if (key.length() < 6 || key.length() > 12){
            UI.println("Key must be between 6 and 12 characters long");
            valid = false;
        }
        if (key.charAt(0) == '&' || key.charAt(0) == '!'){
            UI.println("Key must not start with & or !");
            valid = false;
        }
        if (key.contains("*")){
            UI.println("Key must not contain *");
            valid = false;
        }
        if (!key.matches(".*[A-Z].*")){
            UI.println("Key must contain at least one uppercase letter");
            valid = false;
        }
        if (!key.matches(".*[a-z].*")){
            UI.println("Key must contain at least one lowercase letter");
            valid = false;
        }
        if (key.toLowerCase().contains(name.toLowerCase())){
            UI.println("Key must not contain your name");
            valid = false;
        }
        if (key.contains("&") && key.contains("!")){
            UI.println("Key must not contain both & and !");
            valid = false;
        }
        if (!key.contains("&") && !key.contains("!")){
            UI.println("Key must contain either & or !");
            valid = false;
        }
        if (valid){
            UI.println("Key is valid");
        }
    }


    /**
     * Setup GUI and buttons
     */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Check Key", this::checkKey );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }

    /**
     * Create object and call setupGUI on it
     */ 
    public static void main(String[] args){
        KeyValidator kv = new KeyValidator();
        kv.setupGUI();
    }
}
