package com.capstone;


public class GameEngine {


    public static void main(String[] args) {
        InitXML game = new InitXML();
        game.initNPCs();

        //for getting dialog of a npc, do these calls...
        System.out.println(game.listOfNpcs);
        System.out.println(game.npcDialog("Nurse Didi"));


        //must setCurrentRoom(room) first before calling move and other stuff)
        Player player1 = new Player("Subash");


        //for moving rooms, call player.validMove(direction) for the boolean, and if true... then do player.setCurrentRoom(room)

    }

}
