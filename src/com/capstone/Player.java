package com.capstone;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    //fields, name and inventory
    private String name = "Emeke";
    private Collection<String> inventory = new ArrayList<>();

    //this will probably be in main game engine to load all rooms
    //private Collection<Room> roomsList = new ArrayList<>();

    //current room field
    private Room currentRoom;

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

//    public void setName(String name) {
//        this.name = name;
//    }

    public Collection<String> getInventory() {
        return inventory;
    }

    public void addInventory(String item) {
        this.inventory.add(item);
    }

   /* public void useItem(String item) {
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

        //replace sout with text parser output call later on
        System.out.println("You are currently in: " + this.currentRoom.getName());
        System.out.println("To the North of you is: " + this.currentRoom.getNorthTile());
        System.out.println("To the East of you is: " + this.currentRoom.getEastTile());
        System.out.println("To the South of you is: " + this.currentRoom.getSouthTile());
        System.out.println("To the West of you is: " + this.currentRoom.getWestTile());
    }

    public boolean validMove(String direction){
        direction = direction.toLowerCase();
        switch (direction) {
            case "north":
            case "up":
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
