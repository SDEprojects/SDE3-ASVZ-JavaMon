package com.capstone;

public class Items {

    //fields
    private String name;
    private String effect;
    private String description;
    private int price;

    //ctors
    public Items(String name, String effect, String description, int price) {
        this.name = name;
        this.effect = effect;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }


}
