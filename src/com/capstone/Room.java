package com.capstone;

public class Room {

    //Class Fields
    private String name;
    private String description;
    private String npcName; //interactable npc list
    private String itemName; //interactable item list, can be used for tall grass etc...
    private String northTile;
    private String southTile;
    private String eastTile;
    private String westTile;


    //Constructor(s)
    public Room(String roomName, String roomDescription, String adjNorthTile, String adjSouthTile, String adjEastTile, String adjWestTile) {
        name = roomName;
        description = roomDescription;
        northTile = adjNorthTile;
        southTile = adjSouthTile;
        eastTile = adjEastTile;
        westTile = adjWestTile;

    }

    //Getters and Setters
    public String getNorthTile() {
        return northTile;
    }

    public String getSouthTile() {
        return southTile;
    }

    public String getEastTile() {
        return eastTile;
    }

    public String getWestTile() {
        return westTile;
    }


    //Business Methods

    //This method displays room information to the user.
    void displayOutput(){
        System.out.println("Your current location: " + name);
        System.out.println("Room Description: " + description);

        //Check if npcName is null or empty, if not print out the npc name.
        if(npcName != null && !npcName.trim().isEmpty()){
            System.out.println("You see " + npcName);
        } else {
            System.out.println("No one is here.");
        }
        //Check if itemList is empty
        if(itemName != null && !itemName.trim().isEmpty()){
            System.out.println("You observe the room and see a " + itemName); //singular item for first iteration
        } else {
            System.out.println("You look around and find nothing of interest here.");
        }




        //check if npc list is empty, if not, print out the list of npcs in the room.
        /*if (npcList.isEmpty()){
            System.out.println("There's no one else here.");
        } else if (!npcList.isEmpty()){

            System.out.println("People in the room: ");
            for(String person : npcList){
                System.out.println(person);
            }
        }

        //check if itemList is empty, if not , print out the list of interactable items.
        if (itemList.isEmpty()){

        }*/
    }
}
