package com.capstone;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

public class CombatEngineGui {

    //Class Fields
    private GUI2nd gui;
    private Codex codex = new Codex();

    //Constructor
    public CombatEngineGui() {
    }

    //Class Methods

    // This method checks for a critical hit. If true, display a critical hit message. *Zack*
    public void checkForCritical() {
        PokeAttack check = new PokeAttack();
        if (check.rollCriticalChance() == 2) {
            System.out.println("A critical hit!");
        }
    }

    //Action Phase - this is the phase where the player gets to choose between fight, use item, or run (They cant run from trainer battles)
    //This method is not yet used. Stubbed out and reserved for the wild pokemon encounter.
//    String actionPhaseChoiceWildPoke(){
////        //The userChoice here is what the user chooses to do
////        Scanner scanner = new Scanner(System.in);
////        String userChoice = scanner.nextLine();
////
////        if (!userChoice.equalsIgnoreCase("attack") && !userChoice.equalsIgnoreCase("item") && !userChoice.equalsIgnoreCase("run")){
////            System.out.println("You can't do that.");
////            actionPhaseChoiceWildPoke();
////        }
////        return userChoice;
////    }

    //This method is for taking in user input if they want to attack or use item.
    //Basic input validation is used here to limit choices to attack or item.
    String actionPhaseChoiceTrainerBattle(){
        String[] options = { "Attack","Use item" };
        String res = (String) JOptionPane.showInputDialog(GUI2nd.commonDisplay, "What would you like to do? <attack> or <item>", "Attack or use items",
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        return res;
    }

    //Combat loop : encounter with trainer - this is the main combat loop that is called in the game engine.
    String combatLoopTrainer(Player player, NPCFactory npc, GameEngine game, PrintStream commonDisplayOut, PrintStream pokeDisplayOut, JTextArea pokeDisplay){

        String result = "";
        pokeDisplay.setText("");
        System.setOut(pokeDisplayOut);
        player.getPlayersPokemon().get(0).displayOutStatsAndAll();
        System.setOut(commonDisplayOut);

        //Runs until the player or the opponent is defeated.
        while (true){
            gui.commonDisplay.setText("");
            if(player.playersPokemon.get(0).getCurrentHealth() <= 0){
                result = "Player Lost";
                break;
            } else if (npc.npcPokemonList.get(0).getCurrentHealth() <= 0){

                //Reward exp to player's pokemon when the enemy pokemon is defeated.
                double expReward = npc.npcPokemonList.get(0).getLevel() * 10; //Current xp reward scales with level and is hard coded.
                player.playersPokemon.get(0).rewardEXP(expReward);

                //Reward Player money for winning.
                System.out.println("You received: 1000 for winning!");//hard coded

                codex.updateCodex("You received: 1000 for winning!");
                player.addMoney(1000); //hard coded 1000 money to add as reward.
                result = "NPC Lost";
                break;
            } else {
                player.playersPokemon.get(0).displayOutStatsAndAll();
                codex.updateCodex(player.playersPokemon.get(0).displayOutStatsAndAll_ReturnString());
                npc.npcPokemonList.get(0).displayOutStatsAndAll();
                codex.updateCodex(npc.npcPokemonList.get(0).displayOutStatsAndAll_ReturnString());

                String userChoice = actionPhaseChoiceTrainerBattle();

                //This processes user choice and applies attack from user's choice
                processActionPhase(userChoice,player,npc,game);
                //If opponent's pokemon's hp reaches 0, break out of combat loop.
                if (npc.npcPokemonList.get(0).getCurrentHealth() <= 0){
                    System.out.println("The opponent's Pokemon fainted!");
                    codex.updateCodex("The opponent's Pokemon fainted!");
                    double expReward = npc.npcPokemonList.get(0).getLevel() * 10; //Current xp reward scales with level and is hard coded.
                    player.playersPokemon.get(0).rewardEXP(expReward);
                    System.out.println("You received: 1000 for winning!"); //hard coded
                    codex.updateCodex("You received 1000 for winning!");
                    player.addMoney(1000); //hard coded 1000 money to add as reward.
                    result = "NPC Lost";
                    break;
                }

                //If player's pokemon's hp reaches 0, break out of combat loop
                opponentAttack(player,npc,game);
                pokeDisplay.setText("");
                System.setOut(pokeDisplayOut);
                player.getPlayersPokemon().get(0).displayOutStatsAndAll();
                System.setOut(commonDisplayOut);
                codex.updateCodex(player.getPlayersPokemon().get(0).displayOutStatsAndAll_ReturnString());

                if (player.playersPokemon.get(0).getCurrentHealth() <= 0){
                    System.out.println("Your Pokemon fainted!");
                    codex.updateCodex("Your Pokemon fainted!");
                    result = "Player Lost";
                    break;
                }
            }
        }
        return result;
    }

    //This method is used in the main combat loop , runs after your attack move
    void opponentAttack(Player player, NPCFactory npc, GameEngine game){
        int opponentAttack;
        int opponentAttackChoice;

        //New arraylist object to hold the Player's Pokemon and separate one for npc for combat phase
        ArrayList<Pokemon> playersPokemon = new ArrayList<>();
        ArrayList<Pokemon> npcPokemon = new ArrayList<>();

        //Set the arrayList to equal to the current player's pokemon
        playersPokemon = player.playersPokemon;
        npcPokemon = npc.npcPokemonList;

        //Get the first Pokemon from the arrayList
        Pokemon playerFirstPoke = playersPokemon.get(0);
//        playerFirstPoke.displayOutStatsAndAll(); //Redundant

        //Get the npc's first Pokemon
        Pokemon npcFirstPoke = npcPokemon.get(0);
//        npcFirstPoke.displayOutStatsAndAll(); //Redundant

        Random random = new Random();

        //Randomize opponent's move choice.
        opponentAttackChoice = random.nextInt(2);

        System.out.println("Opponent attacks!");
        codex.updateCodex("Opponent attacks!");

        if (opponentAttackChoice == 0){
            opponentAttack = npcFirstPoke.getMove1().attack(npcFirstPoke.getAttack());
            System.out.println("The opposing " + npcFirstPoke.getName() + " uses " + npcFirstPoke.getMove1().getAttackName());
            codex.updateCodex("The opposing " + npcFirstPoke.getName() + " uses " + npcFirstPoke.getMove1().getAttackName());
            playerFirstPoke.takeDamage(opponentAttack);

        } else if (opponentAttackChoice == 1){
            opponentAttack = npcFirstPoke.getMove2().attack(npcFirstPoke.getAttack());
            System.out.println("The opposing " + npcFirstPoke.getName() + " uses " + npcFirstPoke.getMove2().getAttackName());
            codex.updateCodex("The opposing " + npcFirstPoke.getName() + " uses " + npcFirstPoke.getMove2().getAttackName());
            playerFirstPoke.takeDamage(opponentAttack);
        }
    }

    //used in the main combat loop to process the action phase. does the damage calc, energy usage etc etc.
    void processActionPhase(String userChoice, Player player, NPCFactory npc, GameEngine game){

        int playerPokeAttack;

        //New arraylist object to hold the Player's Pokemon and seperate one for npc for combat phase
        ArrayList<Pokemon> playersPokemon = new ArrayList<>();
        ArrayList<Pokemon> npcPokemon = new ArrayList<>();

        //Set the arrayList to equal to the current player's pokemon
        playersPokemon = player.playersPokemon;
        npcPokemon = npc.npcPokemonList;

        //Get the first Pokemon from the arrayList
        Pokemon playerFirstPoke = playersPokemon.get(0);
//        playerFirstPoke.displayOutStatsAndAll(); //Redundant

        //Get the npc's first Pokemon
        Pokemon npcFirstPoke = npcPokemon.get(0);
//        npcFirstPoke.displayOutStatsAndAll(); //Redundant

        //If userChoice is attack
        if (userChoice == null){
            System.out.println("You have forfeited your turn.");
            codex.updateCodex("You have forfeited your turn.");
        }

        else if (userChoice.equalsIgnoreCase("Attack")){
            System.out.println("Which attack would you like to use?");
            codex.updateCodex("Which attack would you like to use?");
            playerFirstPoke.getMove1().displayOutAttackStats(playerFirstPoke.getLevel());
            // Add here
            playerFirstPoke.getMove2().displayOutAttackStats(playerFirstPoke.getLevel());
            // Add here
            String[] attacks = {playerFirstPoke.getMove1().getAttackName() + " [Damage: " + playerFirstPoke.getMove1().getDamage() + "-" + playerFirstPoke.getMove1().getPotentialDamage() + "]",
                    playerFirstPoke.getMove2().getAttackName() + " [Damage: " + playerFirstPoke.getMove2().getDamage() + "-" + playerFirstPoke.getMove2().getPotentialDamage() + "]"
                    , "back"};

            String res = (String) JOptionPane.showInputDialog(GUI2nd.commonDisplay, "Which attack would you like to use?", "Attacks",
                    JOptionPane.PLAIN_MESSAGE, null, attacks, attacks[0]);

            // Created strippedAttacks and this for loop because attacks were invalid due to additional option description
            String strippedAttacks ="";

            for(Character resA : res.toCharArray()){
                if(resA == '['){
                    break;
                }
                strippedAttacks += resA;
            }
//            System.out.println(strippedAttacks);
            strippedAttacks = strippedAttacks.trim();

            if (strippedAttacks != null) {
                //String attackChoice = scanner.nextLine();
                //If user between attack move one or two
                if (strippedAttacks.equalsIgnoreCase(playerFirstPoke.getMove1().getAttackName())) {
                    System.out.println(playerFirstPoke.getName() + " uses " + playerFirstPoke.getMove1().getAttackName());
                    checkForCritical();
                    codex.updateCodex(playerFirstPoke.getName() + " uses " + playerFirstPoke.getMove1().getAttackName());
                    System.out.println(playerFirstPoke.getName() + " uses " + playerFirstPoke.getMove1().getAttackName());
                    codex.updateCodex(playerFirstPoke.getName() + " uses " + playerFirstPoke.getMove1().getAttackName());
                    playerPokeAttack = playerFirstPoke.getMove1().attack(playerFirstPoke.getAttack());
                    playerFirstPoke.getMove1().attackUsed();
                    npcFirstPoke.takeDamage(playerPokeAttack);
                } else if (strippedAttacks.equalsIgnoreCase(playerFirstPoke.getMove2().getAttackName())) {
                    System.out.println(playerFirstPoke.getName() + " uses " + playerFirstPoke.getMove2().getAttackName());
                    checkForCritical();
                    codex.updateCodex(playerFirstPoke.getName() + " uses " + playerFirstPoke.getMove2().getAttackName());
                    playerPokeAttack = playerFirstPoke.getMove2().attack(playerFirstPoke.getAttack());
                    playerFirstPoke.getMove2().attackUsed();
                    npcFirstPoke.takeDamage(playerPokeAttack);

                } else if (userChoice.equalsIgnoreCase("back")) {
                    processActionPhase(userChoice, player, npc, game);
                } else {
                    System.out.println("Invalid entry.");
                    codex.updateCodex("Invalid entry.");
                }
            } else {
                System.out.println("You have forfeited your turn.");
                codex.updateCodex("You have forfeited your turn.");
            }
        }

        //If user choice is use item
        else if (userChoice.equalsIgnoreCase("Use item")){
            //Display to player their inventory
            player.checkInventory();

            if (player.getInventory().isEmpty()) {
                System.out.println("You don't have any items to use!");
                codex.updateCodex("You don't have any items to use!");
            }
            else {
                String itemString[] = new String[player.getInventory().size()];

                // ArrayList to Array Conversion
                for (int j = 0; j < player.getInventory().size(); j++) {
                    // Assign each value to String array
                    itemString[j] = player.getInventory().get(j);
                }

                System.out.println("Which item would you like to use?");
                codex.updateCodex("Which item would you like to use?");

                String res = (String) JOptionPane.showInputDialog(null, "Which item would you like to use?", "Items",
                        JOptionPane.PLAIN_MESSAGE, null, itemString, itemString[0]);

                //String itemChoice = scanner.nextLine();
                game.useItem(res, playerFirstPoke);
            }
        }
    }
}