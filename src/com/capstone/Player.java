package com.capstone;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    //fields, name and inventory
    private String name = "Emeke"; //default player name
    private Collection<String> inventory = new ArrayList<>(); //inventory for future sprints

    //current room field
    private Room currentRoom;  //to keep track of the current room the player is in

    //ctors
    public Player(){
    }

    public Player(String name){
        this.name=name;
    }


    //methods

    public String getName() {
        return name;
    }

    public Collection<String> getInventory() {
        return inventory;
    }

    public void addInventory(String item) {
        this.inventory.add(item);
    } //for later sprints

   /* public void useItem(String item) {  //for later sprints
        if (this.inventory.contains(item))
    }*/

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void checkMap(){
        //displays rooms in the 4 cardinal directions and your current room
        System.out.println("You are currently in: " + this.currentRoom.getName());
        System.out.println("To the North of you is: " + this.currentRoom.getNorthTile());
        System.out.println("To the East of you is: " + this.currentRoom.getEastTile());
        System.out.println("To the South of you is: " + this.currentRoom.getSouthTile());
        System.out.println("To the West of you is: " + this.currentRoom.getWestTile());
    }

    public void showHelp(){
        //displays a help menu
        System.out.println("~~~~~ Instructions for playing the game! ~~~~~~");
        System.out.println("To move: go <north,east,south, or west");
        System.out.println("To talk: talk <NPC's name>");
        System.out.println("To interact: interact <interactable object>");
        System.out.println("To check the map: check map");
        System.out.println("To display this help prompt again: get help");
    }

    //calling this method to makes sure the passed direction is possible to move to
    public boolean validMove(String direction){
        //switch cases for the directions
        switch (direction) {
            case "north":
            case "up":
                //if the direction tiles are not "nothing" (what we hardcoded in the ROOM XML for not having a room) then it's a valid move
                if (!currentRoom.getNorthTile().equals("nothing")) {
                    return true;
                }
                break;
            case "east":
            case "right":
                if (!currentRoom.getEastTile().equals("nothing")) {
                    return true;
                }
                break;
            case "south":
            case "down":
                if (!currentRoom.getSouthTile().equals("nothing")) {
                    return true;
                }
                break;
            case "west":
            case "left":
                if (!currentRoom.getWestTile().equals("nothing")) {
                    return true;
                }
                break;
        }
        //if it never returned true (the direction was not valid)... then this executes
        System.out.println("There's nothing there! You can't go that way!");
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", inventory=" + inventory +
                ", currentRoom=" + currentRoom +
                '}';
    }
}
