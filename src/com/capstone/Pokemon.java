package com.capstone;

import java.util.ArrayList;
import java.util.Collection;
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

    public ArrayList<PokeAttack> attacksList = new ArrayList<>();

    private String move1Name;
    private String move2Name;

    private PokeAttack move1;
    private PokeAttack move2;


    //Constructors
    public Pokemon(String pokeName, String pokeType){
        name = pokeName;
        type = pokeType;
    }
    public Pokemon(String pokeName, String pokeType, int hp, int pokeLevel, int attackStat, String move1, String move2, Collection<PokeAttack> attacksList){
        this(pokeName,pokeType);
        maxHealth = hp;
        level = pokeLevel;
        attack = attackStat;
        move1Name = move1;
        move2Name = move2;
        generateStats();
        currentHealth = maxHealth;
        processMoves(attacksList);


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

    public PokeAttack getMove1() {
        return move1;
    }

    public void setMove1(PokeAttack move1) {
        this.move1 = move1;
    }

    public PokeAttack getMove2() {
        return move2;
    }

    public void setMove2(PokeAttack move2) {
        this.move2 = move2;
    }

    //Class methods

    public void displayOutPokeBelt(){
        System.out.println("=====================================================");
        System.out.println("Pokemon: " + getName());
        System.out.println("Pokemon Type: " + getType());
        System.out.println("=====================================================");
    }
    //Temp method for demo purpose. May be used as main implementation of displayOutput for all pokemon stats
    public void displayOutStatsAndAll(){
        //System.out.println("=====================================================");
        System.out.println("Pokemon: " + getName());
        System.out.println("Pokemon Type: " + getType());
        System.out.println("Pokemon Level: " + getLevel());
        System.out.println("Pokemon HP: " + "[" + getCurrentHealth() + "/" + getMaxHealth() + "]" );
        System.out.println("Pokemon Attack: " + getAttack());
        //System.out.println("=====================================================");

    }

    //Stat Generator Method, gets called upon instantiation of Pokemon.
    public void generateStats(){
        attack += level; //ex level = 5, attack = 3 -> new attack = 8
        maxHealth += level * 2; //ex level = 5, maxHealth = 12 -> new maxHealth = 22 or ex level = 10, maxHealth = 22 -> new maxHealth = 32.


    }

    public void takeDamage(int incomingDamage){
        currentHealth = currentHealth - incomingDamage;
    }

    void processMoves(Collection<PokeAttack> dataList){

        for(PokeAttack attacks : dataList){
            if (attacks.getAttackName().equalsIgnoreCase(move1Name)){
                move1 = attacks;
            } else if (attacks.getAttackName().equalsIgnoreCase(move2Name)){
                move2 = attacks;
            } else {
                System.out.println("Finished processing.");
            }
        }
    }

}
