package com.capstone;

import java.util.Hashtable;

public class Pokemon {

    //Class fields
    private String name;
    private String type;
    private int health;
    private int level;
    private int attack; //attack is the damage threshold. calculated by RNG range between (base attack from move) and (attack stat).
    private int currentExp; //Current exp until the next level up


    Hashtable<String, Integer> movesDict = new Hashtable<String, Integer>();

    //Constructors
    public Pokemon(String pokeName, String pokeType){
        name = pokeName;
        type = pokeType;
    }
    public Pokemon(String pokeName, String pokeType, int hp, int pokeLevel, int attackStat){
        this(pokeName,pokeType);
        health = hp;
        level = pokeLevel;
        attack = attackStat;
    }


    //Getters and Setters


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public int getAttack() {
        return attack;
    }

    //Class methods

    public void displayOutPokeBelt(){
        System.out.println("Pokemon: " + getName());
        System.out.println("Pokemon Type: " + getType());
    }


}
