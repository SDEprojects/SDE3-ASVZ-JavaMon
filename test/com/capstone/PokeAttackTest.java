package com.capstone;

import org.junit.Test;

public class PokeAttackTest { // This class is for testing the critical hit method. *Zack*

    private int damage = 5;

    @Test
    // Critical Hit RNG Method. *Zack*
    public int rollCriticalChance() {
        int randomlyGeneratedNumber = (int) Math.floor(Math.random() * 10) + 1; // Returns a random integer from 1 to 10 and casts it to a whole number. *Zack*
        if (randomlyGeneratedNumber == 1) {
            int resultTrue = this.getDamage() * 2;
            System.out.print("A critical hit!" + " " + resultTrue);
            return resultTrue;
        }
        else {
            int resultFalse = this.getDamage();
            System.out.println(resultFalse);
            return resultFalse;
        }
    }

    // Main. *Zack*
    public static void main(String[] args) {
        PokeAttackTest testMe = new PokeAttackTest();
        testMe.rollCriticalChance();
    }

    // Getter. *Zack*
    public int getDamage() {
        return damage;
    }
}