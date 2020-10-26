/*
package com.capstone;

//import org.junit.Test;

//import static org.junit.Assert.*;

// Unit Tests written by Amanda

public class TextParserGUITest {
    TextParserGUI parser = new TextParserGUI();

    // Possible inputs
    String noInput = "";
    String oneValidWord = "check";
    String oneInvalidWord = "dog";
    String help = "get help";
    String validInput = "go west";
    String twoValidButIncompatibleWords = "buy west";
    String randomWords = "dog cat";

    @Test
    public void inputValidation_noInput_returnsFalse() {
        // No input returns false
        assertFalse(parser.inputValidation(noInput));
    }

    @Test
    public void inputValidation_oneValidWord_returnsFalse() {
        // One valid word returns false
        assertFalse(parser.inputValidation(oneValidWord));
    }

    @Test
    public void inputValidation_oneInvalidWord_returnsFalse(){
        // One invalid word returns false
        assertFalse(parser.inputValidation(oneInvalidWord));
    }

    @Test
    public void inputValidation_help_returnsTrue() {
        // Help -- Help is a valid input, but what happens when we input help?
        // Should print out a list of commands or something--check GUI
        assertTrue(parser.inputValidation(help));
    }

    @Test
    public void inputValidation_validInput_returnsTrue() {
        // Valid Input
        assertTrue(parser.inputValidation(validInput));
    }

    @Test
    public void inputValidation_invalidInput_returnsFalse(){
        // Invalid Input -- This is valid because it's two words,
        // But where is the validation for words that work together?
        assertTrue(parser.inputValidation(twoValidButIncompatibleWords));
    }

    @Test
    public void inputValidation_randomWords_returnsTrue(){
        // Two random words--valid because it is two words (just how the code was written)
        assertTrue(parser.inputValidation(randomWords));
    }
}*/
