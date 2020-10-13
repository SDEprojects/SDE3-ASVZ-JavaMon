package com.capstone;


import java.util.Optional;
import java.util.Scanner;

public class GameEngine {

    public GameEngine() {
    }

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();


        InitXML game = new InitXML();
        game.initNPCs();
        game.initRooms();
        game.initPokemon();
        game.initItems();
        TextParser parser = new TextParser();


        Player player1 = new Player();

        gameEngine.chooseStarter(game, player1);//This method takes the game(initXML for access to the pokemon list, and player1 for access to their pokemon invite.)
        Room startingRoom = game.getRoom("Oak's Lab");
        player1.setCurrentRoom(startingRoom);

        player1.getCurrentRoom().displayOutput();
        System.out.println("=====================================================");

        //actual loop for gamez
        while (true) {
            //promp user for input

            String userInput = parser.getUserInput();
            System.out.println("=====================================================");

            //for checking the map
            if ((userInput.toLowerCase().equals("check map")) || (userInput.toLowerCase().equals("map"))) {
                player1.checkMap();
            }

            //for getting help menu (Fix input parser to allow for help as a single input later)
            else if ((userInput.toLowerCase().equals("get help")) || (userInput.toLowerCase().equals("help"))) {
                player1.showHelp();
            }

            //for checking your inventory
            else if ((userInput.toLowerCase().equals("check bag")) || (userInput.toLowerCase().equals("check inventory"))) {
                player1.checkInventory();
            }

            //for checking item use
            else if (userInput.split(" ")[0].toLowerCase().equals("use")) {
                String item = userInput.split(" ",2)[1].toLowerCase();
                player1.useItem(item,gameEngine);
            }

            //for the movement
            else if (userInput.split(" ")[0].toLowerCase().equals("go")) {
                String direction = userInput.split(" ",2)[1].toLowerCase();

                if (player1.validMove(direction)) {
                    System.out.println("You moved " + direction);
                    switch (direction) {
                        case "north":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getNorthTile()));
                            break;
                        case "east":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getEastTile()));
                            break;
                        case "south":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getSouthTile()));
                            break;
                        case "west":
                            player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getWestTile()));
                            break;
                    }
                    player1.getCurrentRoom().displayOutput();
                }
                else System.out.println("You can't go in that direction!");


            } else if (userInput.split(" ")[0].toLowerCase().equals("talk")) {
                String npc = userInput.split(" ",2)[1].toLowerCase();
                if (player1.getCurrentRoom().getNpcName().toLowerCase().equals(npc)) {
                    System.out.println('"' + game.npcDialog(npc) + '"');
                }
                else System.out.println("Theres nobody named that here to talk to!");
            } else if (userInput.split(" ")[0].toLowerCase().equals("interact")) {
                String interactable = userInput.split(" ",2)[1].toLowerCase();
                if (player1.getCurrentRoom().getInteractableItem().toLowerCase().equals(interactable)) {

                    //shop interface! Will probably move somewhere and make it a method so that it's not so CLUNKY
                    if (interactable.equals("shop counter")) {
                        System.out.println("--------PokeMart--------");
                        System.out.println("Potion              $100");
                        System.out.println("Super Potion        $500");
                        System.out.println("Full Heal          $1000");
                        System.out.println("Revive             $2500");
                        System.out.println("------------------------");
                        System.out.println("To purchase an item: buy <item>!");
                        System.out.println("To exit shop: exit shop!");
                        boolean exit = false;
                        while (!exit) {
                            String shopInput = parser.getUserInput().toLowerCase();
                            if (shopInput.split(" ")[0].equals("buy")) {
                                String item = shopInput.split(" ", 2)[1];
                                switch (item) {
                                    case "potion":
                                        player1.buyItem("potion", 100);
                                        break;
                                    case "super potion":
                                        player1.buyItem("super potion", 500);
                                    case "full heal":
                                        player1.buyItem("full heal", 1000);
                                        break;
                                    case "revive":
                                        player1.buyItem("revive", 2500);
                                        break;
                                }
                            } else if (shopInput.equals("exit shop")) {
                                System.out.println("Thank you for your patronage!");
                                exit = true;
                            }
                        }
                    }
                    System.out.println("You try to interact with " + interactable);
                    System.out.println("We need to implement an interactables class <_>");
                }
                else System.out.println("Theres no " + interactable + " here to interact with!");

            }
            System.out.println("=====================================================");
        }
    }
    //Choose pokemon starter method.
    //TODO - complete the choose starter pokemon method.
    void chooseStarter(InitXML game, Player player){

        System.out.println("Professor Oak: Hey! You're finally here, I've been waiting for you.\nI'm going on vacation soon... and the flight I'm going on has a strict 1 Pokemon carry on limit.\nI'm going to need you to look after one while I'm gone! I'll even let you choose who you want to take!\nChoose one: (Bulbasaur (Grass-Type), Charmander (Fire-Type), Squirtle (Water-Type))");

        Scanner scanner = new Scanner(System.in);
        String starter = scanner.nextLine();

        for(Pokemon pokemon: game.listOfPokemon){
            if (pokemon.getName().equalsIgnoreCase(starter)){
                player.playersPokemon.add(pokemon);
                System.out.println("You chose: ");
                for(Pokemon playersFirstPokemon: player.playersPokemon){
                    playersFirstPokemon.displayOutPokeBelt();
                }
            }
            else {
                System.out.println("Invalid entry.");
                chooseStarter(game,player);
            }
        }
    }

    public boolean useItem(String item, Optional<Pokemon> pokemon) {
        item = item.toLowerCase();
        if (item.equals("potion")){
            if (pokemon.getCurrentHealth != 0) {
                pokemon.setCurrentHealth = pokemon.getCurrentHealth + 20;
                if (pokemon.getCurrentHealth > pokemon.getMaxHealth) {
                    pokemon.setCurrentHealth = pokemon.getMaxHealth;
                }
                System.out.println(pokemon.getName() + " recovered 20 hp!");
                return true;
            }
            else {
                System.out.println(pokemon.getName() + " has fainted! You can only use a revive or a PokeCenter!");
                return false;
            }

        }
        else if (item.equals("super potion")){
            if (pokemon.getCurrentHealth != 0) {
                pokemon.setCurrentHealth = pokemon.getCurrentHealth + 50;
                if (pokemon.getCurrentHealth > pokemon.getMaxHealth) {
                    pokemon.setCurrentHealth = pokemon.getMaxHealth;
                }
                System.out.println(pokemon.getName() + " recovered 50 hp!");
                return true;
            }
            else {
                System.out.println(pokemon.getName() + " has fainted! You can only use a revive or a PokeCenter!");
                return false;
            }

        }
        else if (item.equals("full heal")){
            if (pokemon.getCurrentHealth != 0) {
                pokemon.setCurrentHealth = pokemon.getMaxHealth;
                System.out.println(pokemon.getName() + " recovered to max hp!");
                return true;
            }
            else {
                System.out.println(pokemon.getName() + " has fainted! You can only use a revive or a PokeCenter!");
                return false;
            }

        }
        else if (item.equals("revive")){
            if (pokemon.getCurrentHealth = 0) {
                pokemon.setCurrentHealth = pokemon.getMaxHealth / 2;
                System.out.println(pokemon.getName() + " revived with 1/2 Max HP!");
                return true;
            }
            else {
                System.out.println(pokemon.getName() + " isn't fainted! You can't use a revive!");
                return false;
            }
        }
        else {
            System.out.println(item + " hasn't been implemented yet :<");
            return false;
        }

    }
}
