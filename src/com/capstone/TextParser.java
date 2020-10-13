package com.capstone;

import java.util.Scanner;

public class TextParser {
    private String userInput;

    public void move(String direction, Room room) {

    }

    public String getUserInput() {
        String inputString = "";

        // Exception Handling
        try {
            System.out.println("Enter a command: ");

            // Create a Scanner object
            Scanner scanner = new Scanner(System.in);
            inputString = scanner.nextLine();

            //Input Validation
            while (!inputValidation(inputString)) {
                System.out.println("=====================================================");
                System.out.println("Enter a command: ");
                inputString = scanner.nextLine();
            }


            /* Future work: create a holder for valid moves to be parsed in and
            verify presence in holder using less conditionals.
            This will make a more robust and less run-time demanding code*/

//            // conditions validate input parsed by scanner class above from the console
//            if (inputString.isEmpty() ) {
//                System.out.println("Your input cannot be empty");
//                isValidString();
//                //throw new IllegalArgumentException("You have not entered a move");
//            }
//            else if (inputString.matches("[-+]?[0-9]*\\.?[0-9]+")) {
//                System.out.println("You have entered an invalid move");
//                isValidString();
//            }
//
//            //conditions for actual action... use populated xml page for synonyms?
//            if (inputString.split(" ")[0].toLowerCase().equals("go")) {
//
//            }
//            else if (inputString.split(" ")[0].toLowerCase().equals("talk")) {
//
//            }
//
//            else if (inputString.split(" ")[0].toLowerCase().equals("interact")) {
//
//            }
        }
        catch (IllegalArgumentException e){
            getUserInput();
        }
        return inputString;
        //System.out.println("Printed String: "+inputString);   for testing purposes

    }

    private boolean inputValidation(String input) {
        if (input.isEmpty() ) {
            System.out.println("Your input cannot be empty");
            return false;
            //throw new IllegalArgumentException("You have not entered a move");
        }
        else if (input.split(" ").length < 2) {
            System.out.println("You have entered an invalid move, what can you do with only one word?");
            return false;
        }
        else if (input.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            System.out.println("You have entered an invalid move");
            return false;
        }
        return true;
    }

}


