package com.capstone;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    //fields, name and inventory
    private String name = "Emeke"; //default player name
    private Collection<String> inventory = new ArrayList<>(); //inventory
    private int money = 9001; //initialize with 100 monies



    //current room field
    private Room currentRoom;  //to keep track of the current room the player is in

    //ctors
    public Player(){
    }

    public Player(String name,int money){
        this.name=name;
        this.money=money;
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

    public void checkInventory(){
        System.out.println("Items you currently have in your inventory: ");
        for (String item: this.inventory) {
            System.out.println(item);
        }
        System.out.println("You currently have " + money + " dollars.");
    }

    public void clearInventory(){
        inventory = null;
    }

    public void buyItem(String item, int price){
        if (this.money > price){
            this.inventory.add(item);
            this.money -= price;
            System.out.println("You bought a " + item + " with " + price + " dollars! " + money + " remains in your wallet!");
        }
        else {
            System.out.println("You only have " + money + " dollars! You can't afford that :<");
        }
    }

    public void useItem(String item){
        if (inventory.contains(item)){
            System.out.println("You used a " + item + "!");
            //stuff about item effects would go here later
            inventory.remove(item);
        }
        else {
            System.out.println("You don't have a " + item + " in your inventory!");
        }
    }

    public void addMoney(int amount){
        money += amount;
    }


    public void showHelp(){
        //displays a help menu
        System.out.println("~~~~~ Instructions for playing the game! ~~~~~~");
        System.out.println("To move: go <north,east,south, or west");
        System.out.println("To talk: talk <NPC's name>");
        System.out.println("To interact: interact <interactable object>");
        System.out.println("To check the map: check map");
        System.out.println("To check your inventory and wallet: check bag/inventory");
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
