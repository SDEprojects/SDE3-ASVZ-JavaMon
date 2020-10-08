package com.capstone;


public class GameEngine {


    public static void main(String[] args) {
        InitXML game = new InitXML();
        game.initNPCs();
        game.initRooms();
        Player player1 = new Player("Subash");
        Room startingRoom = game.getRoom("Oak's Lab");
        player1.setCurrentRoom(startingRoom);

        //actual loop for gamez
        while (true) {
            //promp user for input
            player1.getCurrentRoom().displayOutput();
            String userInput = "What do you wanna do? ";

            //for the movement
            if (userInput == "move or something") {
                String direction = "some user input direction";
                if (player1.validMove(direction)) ;
                switch (direction) {
                    case "north":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getNorthTile()));
                    case "east":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getEastTile()));
                    case "south":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getSouthTile()));
                    case "West":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getWestTile()));
                }
            }
            else if (userInput == "interact ") {
                String interact = "some user interact object";
                if (player1.validMove(direction));
                switch (direction) {
                    case "north":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getNorthTile()));
                    case "east":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getEastTile()));
                    case "south":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getSouthTile()));
                    case "West":
                        player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getWestTile()));
                }
            }

        }



        //everything below is hardcoded for testing

        //for getting dialog of a npc, do these calls...
        System.out.println(game.listOfNpcs);
        System.out.println(game.npcDialog("Nurse Didi"));


        //must setCurrentRoom(room) first before calling move and other stuff)

        System.out.println(player1.getName());



        startingRoom.displayOutput();

        //System.out.println(player1.getCurrentRoom()); //room class needs a toString to display itself... if needed, prob not needed
        System.out.println("===============================================");

        if (player1.validMove("north")) {
            System.out.println("PLayer moved north");
            System.out.println("===============================================");
            Room roomNorth = game.getRoom(player1.getCurrentRoom().getNorthTile());
            player1.setCurrentRoom(roomNorth);
            roomNorth.displayOutput();


            //ASK TOM.... For implementation of NPCs in a Room... should we do a HAS-A relationship, or just query NPC Dialog when you interact as long as the current room contains the NPC.


        }
    }

}
