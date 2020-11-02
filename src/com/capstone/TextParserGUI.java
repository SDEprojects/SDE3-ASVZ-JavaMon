package com.capstone;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.PrintStream;
import java.util.*;
import java.util.List;

/*The TextParser class parses and validates the user (console) inputs*/
public class TextParserGUI {
    public Set<String> visitedRoom = new HashSet<>();
    public void checkPlayerCommand(InitXML game, GameEngine gameEngine, CombatEngineGui combatEngine, Player player1, String userInput, PrintStream commonDisplayOut, PrintStream mapDisplayOut, PrintStream roomDisplayOut, PrintStream pokeDisplayOut, JTextArea pokeDisplay) {
        System.setOut(commonDisplayOut);
        try {
            if (inputValidation(userInput)) {
                String userActions = userInput.split(" ")[0];
                String userArgument = userInput.split(" ", 2)[1];

                NodeList nList = TextParser.getCommandList();

                // iterates over node list of tag names "action"
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    // fetches node item from list by their index position
                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        // for(int a = 0; a < userActions.length; a++)
                        if (eElement.getElementsByTagName("buy").item(0).getTextContent().contains(userActions)) {
                            if (player1.getCurrentRoom().getInteractableItem().toLowerCase().equals("shop counter")) {
                                switch (userArgument) {
                                    case "potion":
                                        playActionSound( "buy", eElement);
                                        player1.buyItem("potion", 100);
                                        break;
                                    case "super potion":
                                        playActionSound( "buy", eElement);
                                        player1.buyItem("super potion", 500);
                                        break;
                                    case "full heal":
                                        playActionSound( "buy", eElement);
                                        player1.buyItem("full heal", 1000);
                                        break;
                                    case "revive":
                                        playActionSound( "buy", eElement);
                                        player1.buyItem("revive", 2500);
                                        break;
                                    case "pokedex":
                                        if (player1.getPlayersPokemon().get(0).getLevel() >= 10) { // *Zack*
                                            player1.buyItem("Pokedex", 5000);
                                            break;
                                        }
                                        else {
                                            System.out.println("You can't purchase the Pokedex until you have more experience! (level 10)");
                                        }
                                        break;
                                    default: {
                                        playActionSound(null, eElement);
                                        System.out.println("No such item here to buy!");
                                    }
                                }
                            }
                            else {
                                playActionSound(null, eElement);
                                System.out.println("There's no shop here! You can't buy anything!");
                            }
                            System.out.println("in buy");
                        }
                        else if (eElement.getElementsByTagName("engage").item(0).getTextContent().contains(userActions)) {
                            //System.out.println("in engage");
                            playActionSound("engage", eElement);
                            String npcName = player1.getCurrentRoom().getNpcName();
                            NPCFactory npcActual = game.getNPC(npcName);
                            playerInteracts(player1, npcActual, gameEngine, combatEngine,userArgument,commonDisplayOut, pokeDisplayOut, pokeDisplay);
                            //System.out.println("out engage");
                        } else if (eElement.getElementsByTagName("communicate").item(0).getTextContent().contains(userActions)) {
                            playActionSound("communicate", eElement);
                            playerTalks(player1, game, userArgument);
                        } else if (eElement.getElementsByTagName("utilize").item(0).getTextContent().contains(userActions)) {
                            if (userInput.split(" ").length <= 2) {
                                playActionSound("hint", eElement);
                                System.out.println("Please include which Pokemon you want to use it on");
                            }
                            else {
                                playActionSound("utilize", eElement);
                                String pokemon = userArgument.substring(userArgument.lastIndexOf(" ") + 1);
                                String item = userArgument.substring(0,userArgument.lastIndexOf(" "));
                                useItem(player1, gameEngine, item,pokemon);
                            }
                        }
                        else if (eElement.getElementsByTagName("check").item(0).getTextContent().contains(userActions)) {
                            if (eElement.getElementsByTagName("bag").item(0).getTextContent().contains(userArgument)) {
                                playActionSound("bag", eElement);
                                player1.checkInventory();
                            } else if (eElement.getElementsByTagName("pokemon").item(0).getTextContent().contains(userArgument)) {
                                playActionSound("pokemon", eElement);
                                player1.checkPokemon();
                            } else if (eElement.getElementsByTagName("map").item(0).getTextContent().contains(userArgument)) {
                                mapDisplayOut.flush();
                                System.setOut(mapDisplayOut);
                                player1.checkMap();
                                System.setOut(commonDisplayOut);
                            } else if (eElement.getElementsByTagName("tallgrass").item(0).getTextContent().contains(userArgument)
                                    || eElement.getElementsByTagName("boulder").item(0).getTextContent().contains(userArgument)
                                    || eElement.getElementsByTagName("holeinthewall").item(0).getTextContent().contains(userArgument)
                                    || eElement.getElementsByTagName("pileofrocks").item(0).getTextContent().contains(userArgument)
                                    || eElement.getElementsByTagName("caveinrubble").item(0).getTextContent().contains(userArgument)
                                    ) {
                                if (!visitedRoom.contains(player1.getCurrentRoom().getName())) {
                                    String[] foundItems = {"Wild Pokemon", "Gold Coins", "Rare Candy", "Berries"};
                                    String res = (String) JOptionPane.showInputDialog(GUI2nd.commonDisplay, "You found some items", "Items",
                                            JOptionPane.PLAIN_MESSAGE, null, foundItems, foundItems[0]);
                                    List<String> wildPokemonNameSet = new ArrayList<>();
                                    for (Pokemon pk : game.listOfPokemon
                                    ) {
                                        wildPokemonNameSet.add(pk.getName());
                                    }
                                    Random rand = new Random();
                                    int luckyNumber = rand.nextInt(wildPokemonNameSet.size() - 3) + 3;
                                    if (res != null) {
                                        switch (res) {
                                            case "Wild Pokemon":
                                                player1.addInventory(wildPokemonNameSet.get(luckyNumber));
                                                System.out.println(wildPokemonNameSet.get(luckyNumber) + " Pokeman is added to you inventory.");wildPokemonNameSet.remove(luckyNumber);
                                                break;
                                            case "Gold Coins":
                                                player1.addMoney(500);
                                                System.out.println("$500 added to your wallet");
                                                break;
                                            case "Rare Candy":
                                                int maxHealth = player1.getPlayersPokemon().get(0).getMaxHealth();
                                                int currHealth = player1.getPlayersPokemon().get(0).getCurrentHealth();

                                                if ((currHealth + 2) <= maxHealth) {
                                                    player1.getPlayersPokemon().get(0).setCurrentHealth(currHealth + 2);
                                                    System.out.println("You have chosen broccoli candy. Your HP increased by 2..");
                                                } else {
                                                    player1.getPlayersPokemon().get(0).setCurrentHealth(currHealth);
                                                }
                                                break;
                                            case "Berries":
                                                player1.getPlayersPokemon().get(0).takeDamage(2);
                                                System.out.println("You ate wild poisonous berries. Your HP decreased by 2..");
                                                break;
                                        }
                                    } else {
                                        System.out.println("You've lost everything you found");
                                    }
                                } else {
                                    System.out.println("Nothing is there anymore..");
                                }
                            } else {

                                playActionSound("hint", eElement);
                                System.out.println("You don't have that... you can't check it!");
                                //System.out.println("----------------------------");
                            }

                        }

                        else if (eElement.getElementsByTagName("get").item(0).getTextContent().contains(userActions)) {
                            if (eElement.getElementsByTagName("help").item(0).getTextContent().contains(userArgument)) {
                                playActionSound("help", eElement);
                                player1.showHelp();
                            }
                            else {
                                playActionSound("hint", eElement);
                                System.out.println("Did you mean to type: get help?");
                                //System.out.println("----------------------------");
                            }
                        }
                        else if (eElement.getElementsByTagName("go").item(0).getTextContent().contains(userActions)) {
                            //adding visited rooms to the visitedRoom Set..

                            visitedRoom.add(player1.getCurrentRoom().getName());

                            System.setOut(System.out);
                            if (eElement.getElementsByTagName("up").item(0).getTextContent().contains(userArgument) && player1.validMove("north")) {
                                playActionSound("up", eElement);
                                System.out.println("You go North");
                                //System.out.println("----------------------------");
                                System.setOut(roomDisplayOut);
                                roomDisplayOut.flush();
                                player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getNorthTile()));
                                player1.showRoomDetails();
                            }
                            else if (eElement.getElementsByTagName("down").item(0).getTextContent().contains(userArgument) && player1.validMove("south")) {
                                playActionSound( "down", eElement);
                                System.out.println("You go South");
                                //System.out.println("----------------------------");
                                System.setOut(roomDisplayOut);
                                roomDisplayOut.flush();
                                player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getSouthTile()));
                                player1.showRoomDetails();
                            }
                            else if (eElement.getElementsByTagName("left").item(0).getTextContent().contains(userArgument) && player1.validMove("west")) {
                                playActionSound( "left", eElement);
                                System.out.println("You go West");
                                //System.out.println("----------------------------");
                                System.setOut(roomDisplayOut);
                                roomDisplayOut.flush();
                                player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getWestTile()));
                                player1.showRoomDetails();
                            }
                            else if (eElement.getElementsByTagName("right").item(0).getTextContent().contains(userArgument) && player1.validMove("east")) {
                                playActionSound( "right", eElement);
                                System.out.println("You go East");
                                //System.out.println("----------------------------");
                                System.setOut(roomDisplayOut);
                                roomDisplayOut.flush();
                                player1.setCurrentRoom(game.getRoom(player1.getCurrentRoom().getEastTile()));
                                player1.showRoomDetails();
                            }
                            else {
                                playActionSound( null, eElement);
                                System.out.println("Invalid direction, please try again :<");
                                //System.out.println("----------------------------");
                            }
                            if(!visitedRoom.contains(player1.getCurrentRoom().getName())){
                                //roomDisplayOut.flush();
                                player1.getCurrentRoom().processAcceptItems(player1.getCurrentRoom().getInteractableItem());
                            }
                        }
                    }
                    break;
                }
            }
            else {
                playActionSound( null, null);
                System.out.println("Invalid input.");
                System.out.println("----------------------------");
            }

        } catch (Exception e) {
            System.out.println("There was an error in the text parser");
            System.out.println(e.getMessage());

            System.out.println(e.getStackTrace());
        }
        System.setOut(System.out);
    }

    public static void playActionSound(String action, Element eElement) {
        if(action != null && eElement != null) {
            if(action == "hint")
            {
                GUI2nd.music.PlaySounds("hintAction.wav");
            }
            else {
                NodeList nodes = eElement.getElementsByTagName(action);
                GUI2nd.music.PlaySounds(((Element) nodes.item(0)).getElementsByTagName("sound").item(0).getTextContent());
            }
        }
        else
        {
            GUI2nd.music.PlaySounds("errorAction.wav");
        }
    }

    // change to package private?
    // make public for unit-testing purpose? APIs available to text private methods but may not be best practice
    public boolean inputValidation(String input) {
        if (input.isEmpty()) {
            System.out.println("You have not entered any text");
            return false;
        } else if (input.split(" ").length < 2) {
            System.out.println("You have entered an invalid move, what can you do with only one word?");
            return false;
        } else if (input.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            System.out.println("You have entered an invalid move");
            return false;
        }
        return true;
    }

    private void playerInteracts(Player player1, NPCFactory npc, GameEngine gameEngine, CombatEngineGui combatEngine, String interactable, PrintStream commonDisplayOut, PrintStream pokeDisplayOut, JTextArea pokeDisplay) {
        //for the shop interface
        if (player1.getCurrentRoom().getInteractableItem().toLowerCase().equals(interactable) && interactable.toLowerCase().equals("shop counter")) {
            //shop interface! Will probably move somewhere and make it a method so that it's not so CLUNKY
            if (interactable.equals("shop counter")) {
                System.out.println("--------PokeMart--------");
                System.out.println("Potion              $100");
                System.out.println("Super Potion        $500");
                System.out.println("Full Heal          $1000");
                System.out.println("Revive             $2500");
                System.out.println("Pokedex            $5000");
                System.out.println("------------------------");
                System.out.println("To purchase an item: buy <item>!");
            }
            else {
                System.out.println("You try to interact with " + interactable);
                System.out.println("We need to implement an interactables class <_>");
            }
        }
        //for the combat with other trainers
        else if (interactable.equalsIgnoreCase(npc.getName())) {
            System.out.println('"' + npc.getDialog() + '"');
            if (!npc.npcPokemonList.isEmpty()) {
                System.out.println(npc.getName() + " challenges you to a Pokemon Battle!");
                combatEngine.combatLoopTrainer(player1,npc,gameEngine,commonDisplayOut, pokeDisplayOut, pokeDisplay);
            }
            else {
                System.out.println(npc.getName() + " doesn't have a Pokemon to battle with.");
            }
        }
        //for pokecenter healz
        else if (player1.getCurrentRoom().getInteractableItem().toLowerCase().equals(interactable) && interactable.toLowerCase().equals("healing station")) {
            for (Pokemon pokemon:player1.getPlayersPokemon()) {
                pokemon.setCurrentHealth(pokemon.getMaxHealth());
            }
            System.out.println("All your Pokemon are healed to full HP! Thank you for visiting!");
        }

        else System.out.println("Theres no " + interactable + " here to interact with!");
    }

    public void playerTalks(Player player1, InitXML game, String npc) {
        //simple check to see if the NPC name in the input is actually in the current room
        if (player1.getCurrentRoom().getNpcName().toLowerCase().equals(npc.toLowerCase())) {
            //if they are in the room, display their dialog
            System.out.println('"' + game.npcDialog(npc) + '"');
            //when you talk to the npc, if they have an item, they give it to you!
            Collection<String> npcItems = game.npcItem(npc);
            if (npcItems != null) {
                for (String item: npcItems) {
                    System.out.println(player1.getCurrentRoom().getNpcName() + " gave you a " + item + "!");
                    player1.addInventory(item);
                }
                //sets the NPC's inventory to null so they don't give you the items again
                game.clearNPCInventory(npc);
            }

        }
        //if npc isn't in the room... tell the user that
        else System.out.println("Theres nobody named that here to talk to!");
    }
    public void useItem(Player player1, GameEngine gameEngine, String item, String pokemon){
        //TODO IF THERE EVER IS MORE THAN ONE POKEMON... CHANGE THIS TO A FOR instead of .get(0)
        if (pokemon.toLowerCase().equals(player1.playersPokemon.get(0).getName().toLowerCase())) {
            if (player1.getInventory().contains(item)){
                Pokemon actualPokemon = player1.playersPokemon.get(0);
                System.out.println("You used a " + item + " on " + pokemon + "!");
                if (gameEngine.useItem(item,actualPokemon)) {
                    player1.getInventory().remove(item);
                }
            }
            else {
                System.out.println("You don't have a " + item + " in your inventory!");
            }
        }
        else {
            System.out.println("You don't own that Pokemon!");
        }
    }
}