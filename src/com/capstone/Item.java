package com.capstone;

public class Item {
    private String itemName;  // name of item
    private String description; // description of item
    private String location;     // name ot the room item located



    // Constructor that initializes objects name, description, and location
    public Item(String itemName, String description, String location) {
        itemName = itemName;
        description = description;
        location = location;

    }

    // Getter method that returns items name, description and location


    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    // Accessory Method to print out the items description for player to read.

    public void look() {
        System.out.println(description + " of an object.");
    }
}
