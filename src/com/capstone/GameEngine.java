package com.capstone;


public class GameEngine {


    public static void main(String[] args) {
        InitXML game = new InitXML();
        game.initNPCs();
        game.initRooms();
        TextParser parser = new TextParser();


        Player player1 = new Player();
        Room startingRoom = game.getRoom("Oak's Lab");
        player1.setCurrentRoom(startingRoom);

        player1.getCurrentRoom().displayOutput();
        System.out.println("=====================================================");

        //actual loop for gamez
        while (true) {
            //promp user for input

            String userInput = parser.getUserInput();
            System.out.println("=====================================================");

            //for checking the map
            if ((userInput.toLowerCase().equals("check map")) || (userInput.toLowerCase().equals("map"))) {
                player1.checkMap();
            }


            //for the movement
            else if (userInput.split(" ")[0].toLowerCase().equals("go")) {
                String direction = userInput.split(" ",2)[1].toLowerCase();

                if (player1.validMove(direction)) {
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
                else System.out.println("You can't go in that direction!");


            } else if (userInput.split(" ")[0].toLowerCase().equals("talk")) {
                String npc = userInput.split(" ",2)[1].toLowerCase();
                if (player1.getCurrentRoom().getNpcName().toLowerCase().equals(npc)) {
                    System.out.println('"' + game.npcDialog(npc) + '"');
                }
                else System.out.println("Theres nobody named that here to talk to!");
            } else if (userInput.split(" ")[0].toLowerCase().equals("interact")) {
                String interactable = userInput.split(" ",2)[1].toLowerCase();
                if (player1.getCurrentRoom().getInteractableItem().toLowerCase().equals(interactable)) {
                    System.out.println("You try to interact with " + interactable);
                    System.out.println("We need to implement an interactables class <_>");
                }
                else System.out.println("Theres no " + interactable + " here to interact with!");

            }
            System.out.println("=====================================================");
        }

//        //everything below is hardcoded for testing
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
    //Choose pokemon starter method.
    //TODO - complete the choose starter pokemon method.
    private void chooseStarter(){

        TextParser parser = new TextParser();

        System.out.println("Professor Oak: Hey! You're finally here, I've been waiting for you. I'm going on vacation soon... and the flight I'm going on has a strict 1 Pokemon carry on limit.\nI'm going to need you to look after one while I'm gone! I'll even let you choose who you want to take!\nChoose one: (Bulbasaur (Grass-Type), Charmander (Fire-Type), Squirtle (Water-Type))");

        String starter = parser.getUserInput();

        if(starter.equalsIgnoreCase("Bulbasaur")){
            //Give Player the Starter Pokemon Bulbasaur

        }
        else if(starter.equalsIgnoreCase("Charmander")){
            //Give Player the Starter Pokemon Charmander

        }
        else if(starter.equalsIgnoreCase("Squirtle")){
            //Give Player the Starter Pokemon Squirtle
        }
    }
}
