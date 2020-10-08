package com.capstone;

import java.util.Scanner;

public class TextParser {
    private String userInput;

    public String getUserInput() {
        String inputString = null;
        try {
            System.out.println("Enter a String: ");
            Scanner scanner = new Scanner(System.in);
            inputString = scanner.nextLine();

            //input validation
            if (!inputValidation(inputString)) {
                inputValidation(inputString);
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

            //conditions for actual action
            if (inputString.split(" ")[0].toLowerCase().equals("go")) {

            }
            else if (inputString.split(" ")[0].toLowerCase().equals("talk")) {

            }

            else if (inputString.split(" ")[0].toLowerCase().equals("interact")) {

            }

        }
        catch (IllegalArgumentException e){
            getUserInput();
        }
        System.out.println("Printed String: "+inputString);
        return inputString;
    }

    private boolean inputValidation(String input) {
        if (input.isEmpty() ) {
            System.out.println("Your input cannot be empty");
            return false;
            //throw new IllegalArgumentException("You have not entered a move");
        }
        else if (input.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            System.out.println("You have entered an invalid move");
            return false;
        }
        return true;
    }

}


