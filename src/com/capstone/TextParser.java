package com.capstone;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

/*The TextParser class parses and validates the user (console) inputs*/
public class TextParser {
//    private String userInput;

//    public void move(String direction, Room room) {
//
//    }

    public String getUserInput() {
        String inputString = "";

        // parse text from the console/keyboard
        try {
            System.out.println("Enter a command: ");

            //say you had a separate class that has a scanner not seen here
            // replace the lines below with input.getText()?
            // scanner can be initialized externally

            //go north


            Scanner scanner = new Scanner(System.in);
            inputString = scanner.nextLine();

            //input validation
            while (!inputValidation(inputString)) {
                System.out.println("===================================");
                System.out.println("Enter a command: ");
                inputString = scanner.nextLine();
            }

        } catch (IllegalArgumentException e) {
            getUserInput();
        }
        return inputString;
        //System.out.println("Printed String: "+inputString);   for testing purposes

    }

    public void checkPlayerCommand() {
        try {
            String userInput = getUserInput();
            String userActions = userInput.split(" ")[0];
            String userArgument = userInput.split(" ", 2)[1];

            File inputFile = new File("data", "keyWords.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // prints the root element of the file which is "keyWords" using getNodeName()
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                /* Insert if statement for "action item by checking if the first word corresponds to items in
                action group - need to initialize reference"*/
            // creates and populates a list of nodes tag items by the tag name "action"
            NodeList nList = doc.getElementsByTagName("action");
            System.out.println("----------------------------");

            // iterates over node list of tag names "action"
            for (int temp = 0; temp < nList.getLength(); temp++) {
                // fetches node item from list by their index position
                Node nNode = nList.item(temp);
                System.out.println("Node list length is: " + nList.getLength());
                // prints current node name
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // for(int a = 0; a < userActions.length; a++)
                    if (eElement.getElementsByTagName("engage").item(0).getTextContent().contains(userActions)) {
                        System.out.println("Player interacts");
                    } else if (eElement.getElementsByTagName("communicate").item(0).getTextContent().contains(userActions)) {
                        System.out.println("Player talks");
                    } else if (eElement.getElementsByTagName("utilize").item(0).getTextContent().contains(userActions)) {
                        System.out.println("Player uses");
                    } else if (eElement.getElementsByTagName("purchase").item(0).getTextContent().contains(userActions)) {
                        System.out.println("Player buys");
                    } else if (eElement.getElementsByTagName("go").item(0).getTextContent().contains(userActions)) {
                        if (eElement.getElementsByTagName("up").item(0).getTextContent().contains(userArgument)) {
                            System.out.println("Player goes north");
                        } else if (eElement.getElementsByTagName("down").item(0).getTextContent().contains(userArgument)) {
                            System.out.println("Player goes south");
                        } else if (eElement.getElementsByTagName("left").item(0).getTextContent().contains(userArgument)) {
                            System.out.println("Player goes west");
                        } else if (eElement.getElementsByTagName("right").item(0).getTextContent().contains(userArgument)) {
                            System.out.println("Player goes east");
                        } else {
                            System.out.println("Invalid command");
                            checkPlayerCommand();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // change to package private?
    // make public for unit-testing purpose? APIs available to text private methods but may not be best practice
    private boolean inputValidation(String input) {
        if (input.isEmpty()) {
            System.out.println("You have not entered any text");
            return false;
            //throw new IllegalArgumentException("You have not entered a move");
        } else if (input.split(" ").length < 2) {
            System.out.println("You have entered an invalid move, what can you do with only one word?");
            return false;
        } else if (input.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            System.out.println("You have entered an invalid move");
            return false;
        }
        return true;
    }

    // Parse XML to confirm user input is a valid game calls

}