package com.capstone;

import java.util.Hashtable;

public class Pokemon {

    //Class fields
    private String name;
    private String type;
    private int currentHealth;
    private int maxHealth;
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
        maxHealth = hp;
        level = pokeLevel;
        attack = attackStat;
        generateStats();
        currentHealth = maxHealth;
    }


    //Getters and Setters


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getAttack() {
        return attack;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    //Class methods

    public void displayOutPokeBelt(){
        System.out.println("========================================");
        System.out.println("Pokemon: " + getName());
        System.out.println("Pokemon Type: " + getType());
        System.out.println("========================================");
    }
    //Temp method for demo purpose. May be used as main implementation of displayOutput for all pokemon stats
    public void displayOutStatsAndAll(){
        System.out.println("========================================");
        System.out.println("Pokemon: " + getName());
        System.out.println("Pokemon Type: " + getType());
        System.out.println("Pokemon Level: " + getLevel());
        System.out.println("Pokemon HP: " + "[" + getCurrentHealth() + "/" + getMaxHealth() + "]" );
        System.out.println("Pokemon Attack: " + getAttack());
        System.out.println("========================================");

    }

    //Stat Generator Method, gets called upon instantiation of Pokemon.
    public void generateStats(){
        attack += level; //ex level = 5, attack = 3 -> new attack = 8
        maxHealth += level * 2; //ex level = 5, maxHealth = 12 -> new maxHealth = 22 or ex level = 10, maxHealth = 22 -> new maxHealth = 32.


    }


}
