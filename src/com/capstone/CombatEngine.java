package com.capstone;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CombatEngine {
    //Class Fields



    //TODO Initiate combat from grass or trainer

    //TODO Enemy Pokemon level varies depending on location.

    //TODO Action Phase, Player Choice Phase, Player

    //TODO Win/Lose

    //Constructor


    public CombatEngine() {

    }


    //Class Methods

    //Action Phase - this is the phase where the player gets to choose between fight, use item, or run (They cant run from trainer battles)

    String actionPhaseChoiceWildPoke(){
        //The userChoice here is what the user chooses to do
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        if (!userChoice.equalsIgnoreCase("attack") && !userChoice.equalsIgnoreCase("item") && !userChoice.equalsIgnoreCase("run")){
            System.out.println("You can't do that.");
            actionPhaseChoiceWildPoke();
        }
        return userChoice;
    }

    String actionPhaseChoiceTrainerBattle(){
        //The userChoice here is what the user chooses to do
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        if (!userChoice.equalsIgnoreCase("attack") && !userChoice.equalsIgnoreCase("item")){
            System.out.println("You can't do that.");
            actionPhaseChoiceTrainerBattle();
        }
        return userChoice;
    }

    //Combat loop : encounter with trainer

    String combatLoopTrainer(Player player, NPCFactory npc, GameEngine game){

        String result = "";


        //Runs until the player or the opponent is defeated.

        while (true){

            if(player.playersPokemon.get(0).getCurrentHealth() <= 0){
                result = "Player Lost";
                break;
            } else if (npc.npcPokemonList.get(0).getCurrentHealth() <= 0){
                result = "NPC Lost";
                break;
            } else {
                String userChoice = actionPhaseChoiceTrainerBattle();

                //This processes user choice and applies attack from user's choice
                processActionPhase(userChoice,player,npc,game);
                //If opponent's pokemon's hp reaches 0, break out of combat loop.
                if (npc.npcPokemonList.get(0).getCurrentHealth() <= 0){
                    System.out.println("The opponent's Pokemon fainted!");
                    result = "NPC Lost";
                    break;
                }
                //If player's pokemon's hp reaches 0, break out of combat loop
                opponentAttack(player,npc,game);
                if (player.playersPokemon.get(0).getCurrentHealth() <= 0){
                    System.out.println("Your Pokemon fainted!");
                    result = "Player Lost";
                    break;

                }
            }
        }
        return result;
    }


    void opponentAttack(Player player, NPCFactory npc, GameEngine game){
        int opponentAttack;
        int opponentAttackChoice;
        //New arraylist object to hold the Player's Pokemon and seperate one for npc for combat phase
        ArrayList<Pokemon> playersPokemon = new ArrayList<>();
        ArrayList<Pokemon> npcPokemon = new ArrayList<>();

        //Set the arrayList to equal to the current player's pokemon
        playersPokemon = player.playersPokemon;
        npcPokemon = npc.npcPokemonList;

        //Get the first Pokemon from the arrayList
        Pokemon playerFirstPoke = playersPokemon.get(0);
        playerFirstPoke.displayOutStatsAndAll();

        //Get the npc's first Pokemon
        Pokemon npcFirstPoke = npcPokemon.get(0);
        npcFirstPoke.displayOutStatsAndAll();

        Random random = new Random();

        opponentAttackChoice = random.nextInt(1) - 1;

        if (opponentAttackChoice == 0){

            opponentAttack = npcFirstPoke.getMove1().attack(npcFirstPoke.getAttack());
            playerFirstPoke.takeDamage(opponentAttack);

        } else if (opponentAttackChoice == 1){

            opponentAttack = npcFirstPoke.getMove2().attack(npcFirstPoke.getAttack());
            playerFirstPoke.takeDamage(opponentAttack);
        }

    }

    void processActionPhase(String userChoice, Player player, NPCFactory npc, GameEngine game){

        Scanner scanner = new Scanner(System.in);

        int playerPokeAttack;



        //New arraylist object to hold the Player's Pokemon and seperate one for npc for combat phase
        ArrayList<Pokemon> playersPokemon = new ArrayList<>();
        ArrayList<Pokemon> npcPokemon = new ArrayList<>();

        //Set the arrayList to equal to the current player's pokemon
        playersPokemon = player.playersPokemon;
        npcPokemon = npc.npcPokemonList;

        //Get the first Pokemon from the arrayList
        Pokemon playerFirstPoke = playersPokemon.get(0);
        playerFirstPoke.displayOutStatsAndAll();

        //Get the npc's first Pokemon
        Pokemon npcFirstPoke = npcPokemon.get(0);
        npcFirstPoke.displayOutStatsAndAll();

        //If userChoice is attack
        if (userChoice.equalsIgnoreCase("attack")){
            System.out.println("Which attack would you like to use?");
            playerFirstPoke.getMove1().displayOutAttackStats(playerFirstPoke.getLevel());
            playerFirstPoke.getMove2().displayOutAttackStats(playerFirstPoke.getLevel());
            String attackChoice = scanner.nextLine();
            //If user between attack move one or two
            if (attackChoice.equalsIgnoreCase(playerFirstPoke.getMove1().getAttackName())){
                playerPokeAttack = playerFirstPoke.getMove1().attack(playerFirstPoke.getAttack());
                npcFirstPoke.takeDamage(playerPokeAttack);
            } else if (attackChoice.equalsIgnoreCase(playerFirstPoke.getMove2().getAttackName())){
                playerPokeAttack = playerFirstPoke.getMove2().attack(playerFirstPoke.getAttack());
                npcFirstPoke.takeDamage(playerPokeAttack);

            } else if (userChoice.equalsIgnoreCase("back")){
                processActionPhase(userChoice,player,npc, game);
            } else {
                System.out.println("Invalid entry.");
            }

        }
        //If user choice is use item
        else if (userChoice.equalsIgnoreCase("item")){
            //Display to player their inventory
            player.checkInventory();

            System.out.println("Which item would you like to use?");

            String itemChoice = scanner.nextLine();
            game.useItem(itemChoice, playerFirstPoke);

        }



    }
}
