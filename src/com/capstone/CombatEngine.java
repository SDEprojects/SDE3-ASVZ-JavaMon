package com.capstone;

import java.util.ArrayList;
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




    void processActionPhase(String userChoice, Player player, NPCFactory npc){

        //

        Scanner scanner = new Scanner(System.in);


        //New arraylist object to hold the Player's Pokemon and seperate one for npc for combat phase
        ArrayList<Pokemon> playersPokemon = new ArrayList<>();
        ArrayList<Pokemon> npcPokemon = new ArrayList<>();

        //Set the arrayList to equal to the current player's pokemon
        playersPokemon = player.playersPokemon;
        npcPokemon = npc.npcPokemon;

        //Get the first Pokemon from the arrayList
        Pokemon playerFirstPoke = playersPokemon.get(0);
        playerFirstPoke.displayOutStatsAndAll();

        //Get the npc's first Pokemon
        Pokemon npcFirstPoke = npc.npcPokemon.get(0);
        npcFirstPoke.displayOutStatsAndAll();
        System.out.println(npc.getName() + " sends out " + npcFirstPoke.getName());

        if (userChoice.equalsIgnoreCase("attack")){
            System.out.println("Which attack would you like to use?");
            playerFirstPoke.getMove1().displayOutAttackStats(playerFirstPoke.getLevel());
            playerFirstPoke.getMove2().displayOutAttackStats(playerFirstPoke.getLevel());
            String attackChoice = scanner.nextLine();
            //If user between attack move one or two
            if (attackChoice.equalsIgnoreCase(playerFirstPoke.getMove1().getAttackName())){

            }
        }

    }
}
