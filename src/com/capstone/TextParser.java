package com.capstone;

import java.util.Scanner;

public class TextParser {
    private String userInput;

    public static void main(String[] args) {
        // An instance of the TextParser class can be instantiated to call non static method
        // TextParser parse = new TextParser();
        isValidString();
    }

    private static String isValidString() {
        String inputString = null;
        try {
            System.out.println("Enter a String: ");
            Scanner scanner = new Scanner(System.in);
            inputString = scanner.nextLine();

            /* Future work: create a holder for valid moves to be parsed in and
            verify presence in holder using less conditionals.
            This will make a more robust and less run-time demanding code*/

            // conditions validate input parsed by scanner class above from the console
            if (inputString.isEmpty() ) {
                System.out.println("Your input cannot be empty");
                isValidString();
                //throw new IllegalArgumentException("You have not entered a move");
            }else {
                boolean numTrue = inputString.matches("[-+]?[0-9]*\\.?[0-9]+");
                if (numTrue) {
                    System.out.println("You have entered an invalid move");
                    isValidString();
                }
            }
        }
        catch (IllegalArgumentException e){
            isValidString();
        }
        System.out.println("Printed String: "+inputString);
        return inputString;
    }

    public String getUserInput() {
        return userInput;
    }
}


