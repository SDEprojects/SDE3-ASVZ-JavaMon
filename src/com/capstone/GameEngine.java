package com.capstone;


public class GameEngine {


    public static void main(String[] args) {
        // instantiate the InitXML class so we can have persistant rooms and npcs
        InitXML game = new InitXML();
        game.initNPCs();
        game.initRooms();

        // instantiate TextParser class so we can use the method in it to prompt user for input and validate
        TextParser parser = new TextParser();

        //instantiate the Player... so you can play... and start off in "Oak's Lab"
        Player player1 = new Player();
        Room startingRoom = game.getRoom("Oak's Lab");
        player1.setCurrentRoom(startingRoom);

        //prints out instructions context for playing the game
        player1.showHelp();
        System.out.println("=====================================================");

        //first call to displayOutput to give the player a sense of what is in the room
        player1.getCurrentRoom().displayOutput();
        System.out.println("=====================================================");

        //actual loop for the game
        while (true) {

            //promp user for input
            String userInput = parser.getUserInput();
            System.out.println("=====================================================");

            //for checking the map (Fix input parser to allow for map as a single input later)
            if ((userInput.toLowerCase().equals("check map")) || (userInput.toLowerCase().equals("map"))) {
                player1.checkMap();
            }

            //for getting help menu (Fix input parser to allow for help as a single input later)
            else if ((userInput.toLowerCase().equals("get help")) || (userInput.toLowerCase().equals("help"))) {
                player1.showHelp();
            }

            //for the movement
            //if the first word of the input before a space is read is "go" then execute here
            else if (userInput.split(" ")[0].toLowerCase().equals("go")) {
                //splits the input string into TWO string, split between the FIRST space... and set the direction variable to be the second word
                String direction = userInput.split(" ",2)[1].toLowerCase();

                //calls the validMove method from the player class to check if it's a valid move
                if (player1.validMove(direction)) {
                    //if move is valid, then let user know they moved, set their current room to the new room, and display the output
                    System.out.println("You moved " + direction);
                    switch (direction) {
                        case "north":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getNorthTile()));
                            break;
                        case "east":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getEastTile()));
                            break;
                        case "south":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getSouthTile()));
                            break;
                        case "west":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getWestTile()));
                            break;
                    }
                    player1.getCurrentRoom().displayOutput();
                }
                //TO DO: if its not valid, then print this out... there is already a sout in validMoves so this is redundant! Maybe just delete for future sprints
                else System.out.println("You can't go in that direction!");


            }
            //else if the first word is "talk" then...
            else if (userInput.split(" ")[0].toLowerCase().equals("talk")) {
                //set the second string in input after the first space to be equal to the NPC name
                String npc = userInput.split(" ",2)[1].toLowerCase();

                //simple check to see if the NPC name in the input is actually in the current room
                if (player1.getCurrentRoom().getNpcName().toLowerCase().equals(npc)) {
                    //if they are in the room, display their dialog
                    System.out.println('"' + game.npcDialog(npc) + '"');
                }
                //if npc isn't in the room... tell the user that
                else System.out.println("Theres nobody named that here to talk to!");
            }
            //else if the first word is "interact" then...
            else if (userInput.split(" ")[0].toLowerCase().equals("interact")) {
                //set the second string in input after the first space to be equal to interactable item
                String interactable = userInput.split(" ",2)[1].toLowerCase();
                //check to see if the item is in the room
                if (player1.getCurrentRoom().getInteractableItem().toLowerCase().equals(interactable)) {
                    //actual code to come... probably do something like calling a method to remove it from room after you interact with it.
                    System.out.println("You try to interact with " + interactable);
                    System.out.println("We need to implement an interactables class <_>");
                }
                else System.out.println("Theres no " + interactable + " here to interact with!");

            }
            System.out.println("=====================================================");
        }

//        //everything below was hardcoded for testing
//
//        //for getting dialog of a npc, do these calls...
//        System.out.println(game.listOfNpcs);
//        System.out.println(game.npcDialog("Nurse Didi"));
//
//
//        //must setCurrentRoom(room) first before calling move and other stuff)
//
//        System.out.println(player1.getName());
//
//
//
//        startingRoom.displayOutput();
//
//        //System.out.println(player1.getCurrentRoom()); //room class needs a toString to display itself... if needed, prob not needed
//        System.out.println("===============================================");
//
//        if (player1.validMove("north")) {
//            System.out.println("PLayer moved north");
//            System.out.println("===============================================");
//            Room roomNorth = game.getRoom(player1.getCurrentRoom().getNorthTile());
//            player1.setCurrentRoom(roomNorth);
//            roomNorth.displayOutput();
//
//
//            //ASK TOM.... For implementation of NPCs in a Room... should we do a HAS-A relationship, or just query NPC Dialog when you interact as long as the current room contains the NPC.
//
//
//        }
    }
}
