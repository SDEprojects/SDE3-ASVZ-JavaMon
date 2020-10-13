package com.capstone;


import java.util.Collection;

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

            //for checking your inventory
            else if ((userInput.toLowerCase().equals("check bag")) || (userInput.toLowerCase().equals("check inventory"))) {
                player1.checkInventory();
            }

            //for checking item use
            else if (userInput.split(" ")[0].toLowerCase().equals("use")) {
                String item = userInput.split(" ",2)[1].toLowerCase();
                player1.useItem(item);
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
                String npc = userInput.split(" ",2)[1];


                //simple check to see if the NPC name in the input is actually in the current room
                if (player1.getCurrentRoom().getNpcName().toLowerCase().equals(npc.toLowerCase())) {
                    //if they are in the room, display their dialog
                    System.out.println('"' + game.npcDialog(npc) + '"');

                    //when you talk to the npc, if they have an item, they give it to you!
                    Collection<String> npcItems = game.npcItem(npc);
                    if (npcItems != null) {
                        for (String item: npcItems) {
                            System.out.println(player1.getCurrentRoom().getNpcName() + " gave you a " + item + "!");
                            player1.addInventory(item);
                        }
                        //sets the NPC's inventory to null so they don't give you the items again
                        game.clearNPCInventory(npc);
                    }

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

                    //shop interface! Will probably move somewhere and make it a method so that it's not so CLUNKY
                    if (interactable.equals("shop counter")) {
                        System.out.println("--------PokeMart--------");
                        System.out.println("Potion              $100");
                        System.out.println("Super Potion        $500");
                        System.out.println("Full Heal          $1000");
                        System.out.println("Revive             $2500");
                        System.out.println("------------------------");
                        System.out.println("To purchase an item: buy <item>!");
                        System.out.println("To exit shop: exit shop!");
                        boolean exit = false;
                        while (!exit) {
                            String shopInput = parser.getUserInput().toLowerCase();
                            if (shopInput.split(" ")[0].equals("buy")) {
                                String item = shopInput.split(" ",2)[1];
                                switch (item) {
                                    case "potion":
                                        player1.buyItem("potion",100);
                                        break;
                                    case "super potion":
                                        player1.buyItem("super potion",500);
                                    case "full heal":
                                        player1.buyItem("full heal",1000);
                                        break;
                                    case "revive":
                                        player1.buyItem("revive",2500);
                                        break;
                                }
                            }
                            else if (shopInput.equals("exit shop")) {
                                System.out.println("Thank you for your patronage!");
                                exit = true;
                            }
                        }
                    }
                    else {
                        System.out.println("You try to interact with " + interactable);
                        System.out.println("We need to implement an interactables class <_>");
                    }
                }
                else System.out.println("Theres no " + interactable + " here to interact with!");

            }
            System.out.println("=====================================================");
        }
    }
}



