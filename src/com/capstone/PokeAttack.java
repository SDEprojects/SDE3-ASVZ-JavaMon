package com.capstone;

import java.util.Random;

public class PokeAttack {
    private String attackName;
    private int damage; //base damage set from AttackMoves.txt xml.
    private int currentEnergy;
    private int maxEnergy;

    public PokeAttack(String attackName, int damage, int maxEnergy) {
        this.attackName = attackName;
        this.damage = damage;
        this.maxEnergy = maxEnergy;
    }

    //Getters and Setters


    public String getAttackName() {
        return attackName;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    void displayOutAttackStats(int pokemonAttackStat){
        int potentialDamage = damage + pokemonAttackStat;
        System.out.println(attackName + ": damage: (" + damage + "-" + potentialDamage + ") energy: [" + currentEnergy + "/" + maxEnergy + "]" );
    }

    void attackUsed(){
        currentEnergy--;
    }

    int attack(int attackStat){
        int potentialDamage = damage + attackStat;
        return (int)(Math.random() * (potentialDamage - damage + 1) + damage);
    }




}
