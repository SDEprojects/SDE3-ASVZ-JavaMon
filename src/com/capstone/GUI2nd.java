package com.capstone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.*;


public class GUI2nd {
    private ActionListener titleScreenHandler;

    private JFrame window;
    private JPanel titleNamePanel;
    private JPanel startButtonPanel;
    private JTextArea mainTextArea;
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);

    private String[] choiceDisplayArr = {"Bulbasaur (Grass-Type)", "Charmander (Fire-Type)", "Squirtle (Water-Type)"};
    private String[] choiceActionCommandArr = {"bulbasaur", "charmander", "squirtle"};
    private JComboBox<String> choiceCombo = new JComboBox(choiceDisplayArr);
    private String selectedChoice = null;

    JTextArea output = new JTextArea(20,25);
    JTextArea pokemonDisplay = new JTextArea(20,10);
    JTextArea mapDisplay = new JTextArea(20,10);



    private GameEngine gameEngine = new GameEngine();
    private String starter;
    private String roomName;
    private Player player1 = new Player();
    private InitXML game = new InitXML();
    private TextParserGUI parser = new TextParserGUI();

    public static void main(String[] args) {
        GUI2nd gui = new GUI2nd();
        gui.game.initRooms();
        gui.game.initItems();
        gui.game.initNPCs();
        gui.game.initPokemon();
        gui.initFrame();
        gui.chooseStarter(gui.game, gui.player1);
    }
    private void initFrame() {

        // Initializing JFrame Window
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
//        window.setLayout(null);


        //Initializing Title Name Panel
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.BLACK);


        JLabel titleNameLabel = new JLabel("");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 60));

        titleNamePanel.add(titleNameLabel);

        //Initializing Start Button Panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.BLACK);


        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.RED);
        startButton.setFont(normalFont);
        startButton.addActionListener(titleScreenHandler);

        startButtonPanel.add(startButton);

        window.add(titleNamePanel);
//        window.add(startButtonPanel);

        window.setVisible(true);


    }


    public void chooseStarter(InitXML game, Player player) {
        JPanel starterPokemonPanel = new JPanel();
        starterPokemonPanel.setLayout(new BoxLayout(starterPokemonPanel, BoxLayout.PAGE_AXIS)); //center the layout here later
        starterPokemonPanel.add(new JLabel("You're in OakRoom"));
        starterPokemonPanel.add(new JLabel("Professor Oak: Hey! You're finally here, I've been waiting for you.\nI'm going on vacation soon... and the flight I'm going on has a strict 1 Pokemon carry on limit.\nI'm going to need you to look after one while I'm gone! I'll even let you choose who you want to take!\n"));

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        ActionListener radioButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                starter = event.getActionCommand();
                System.out.println("actionCommand: " + starter);
            }
        };

        starterPokemonPanel.add(new JLabel("Choose One:"));
        for (int i = 0; i < choiceDisplayArr.length; i++) {
            JRadioButton radio = new JRadioButton(choiceDisplayArr[i]);
            radio.setActionCommand(choiceActionCommandArr[i]);
            radio.addActionListener(radioButtonListener);
            group.add(radio);
            starterPokemonPanel.add(radio);
        }

        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.RED);
        startButton.setFont(normalFont);

        starterPokemonPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("selected starter: " + starter);
                for (Pokemon pokemon : game.listOfPokemon) {
                    if (pokemon.getName().equalsIgnoreCase(starter)) {
                        player.playersPokemon.add(pokemon);
                        System.out.println("You chose: " + starter);
                        for (Pokemon playersFirstPokemon : player.playersPokemon) {
//	    					playersFirstPokemon.displayOutStatsAndAll();
                            displayOutStatsAndAll(playersFirstPokemon, player);
                        }
                    }
                }
            }
        });


        window.getContentPane().removeAll();
        window.setLayout(new BorderLayout());
        window.getContentPane().add(starterPokemonPanel);
        window.revalidate();
    }

    public void displayOutStatsAndAll(Pokemon pokemon, Player player) {

        output.setText("");
        output.append("=====================================================" + "\n");
        output.append("You're in OakRoom" + "\n");
        output.append("Pokemon: " + pokemon.getName() + "\n");
        output.append("Pokemon Type: " + pokemon.getType() + "\n");
        output.append("Pokemon Level: " + pokemon.getLevel() + "\n");
        output.append("Pokemon HP: " + "[" + pokemon.getCurrentHealth() + "/" + pokemon.getMaxHealth() + "]" + "\n");
        output.append("Pokemon Attack: " + pokemon.getAttack() + "\n");
        output.append("=====================================================" + "\n");

        JScrollPane scroll = new JScrollPane (output,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        String roomName = "Oak's Lab";
        Room startingRoom = game.getRoom(roomName);
        player1.setCurrentRoom(startingRoom);

//        player1.getCurrentRoom().displayOutput();
        Room currentRoom = player1.getCurrentRoom();
        JPanel roomP = getRoomDetailsPanel(currentRoom);


        JPanel southP = new JPanel();
        southP.setLayout(new BoxLayout(southP, BoxLayout.PAGE_AXIS));
        JTextField inputTF = new JTextField(20);
        southP.add(new JLabel("Enter your command: "));
        southP.add(inputTF);

        JButton submitB = new JButton("Submit");
        southP.add(submitB);
        submitB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PrintStream printStream = new PrintStream(new CustomOutputStream(output));
                System.setOut(printStream);
                parser.checkPlayerCommand(game, gameEngine, player1, inputTF.getText());
                System.setOut(System.out);
                setCurrentRoomDetails(player.getCurrentRoom());
            }
        });

        //this is for the static displays of pokemon status and map
        pokemonDisplay = getPokemonDisplay();
        mapDisplay = getMapDisplay();

//        JPanel leftPokemonDisplayPanel = new JPanel();
//        //leftPokemonDisplayPanel.setLayout(new BoxLayout(pokemonDisplay, BoxLayout.PAGE_AXIS));
//        leftPokemonDisplayPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//
//        JPanel rightMapDisplay = new JPanel();
//        //rightMapDisplay.setLayout(new BoxLayout(mapDisplay, BoxLayout.PAGE_AXIS));
//        rightMapDisplay.setAlignmentX(Component.RIGHT_ALIGNMENT);

        window.getContentPane().removeAll();
        window.setLayout(new BorderLayout());
        window.getContentPane().add(roomP, BorderLayout.NORTH);
//        window.getContentPane().add(leftPokemonDisplayPanel, BorderLayout.LINE_START);
        window.getContentPane().add(scroll, BorderLayout.CENTER);
//        window.getContentPane().add(rightMapDisplay, BorderLayout.LINE_END);
        window.getContentPane().add(southP, BorderLayout.SOUTH);
        window.revalidate();


    }

    public JTextArea getMapDisplay() {
        JTextArea mapDisplay = new JTextArea();
        PrintStream printStream = new PrintStream(new CustomOutputStream(mapDisplay));
        System.setOut(printStream);
        player1.checkMap();
        System.setOut(System.out);
        return  mapDisplay;
    }

    public JTextArea getPokemonDisplay() {
        JTextArea pokemonDisplay = new JTextArea();
        PrintStream printStream = new PrintStream(new CustomOutputStream(pokemonDisplay));
        System.setOut(printStream);
        player1.checkPokemon();
        System.setOut(System.out);
        return  pokemonDisplay;
    }

    private void setCurrentRoomDetails(Room currentRoom) {
        JPanel roomP = getRoomDetailsPanel(currentRoom);
//        JTextArea pokemonDisplay = getPokemonDisplay();
//        JTextArea mapDisplay = getMapDisplay();
        window.getContentPane().add(roomP, BorderLayout.NORTH);
//        window.getContentPane().add(pokemonDisplay, BorderLayout.LINE_START);
//        window.getContentPane().add(mapDisplay, BorderLayout.LINE_END);
//        window.repaint();
        window.revalidate();
    }

    private void getDisplayPokemonPanel() {

    }

    private JPanel getRoomDetailsPanel(Room room) {
        JPanel vboxP = new JPanel();
        vboxP.setLayout(new BoxLayout(vboxP, BoxLayout.PAGE_AXIS));
        vboxP.setAlignmentX(Component.CENTER_ALIGNMENT);

        vboxP.add(new JLabel("Your current location: " +  room.getName()));
        vboxP.add(new JLabel("Location Description: " + room.getDescription()));

        //Check if npcName is null or empty, if not print out the npc name.
        String npcName = room.getNpcName();
        if(npcName != null && !npcName.trim().isEmpty()){
            vboxP.add(new JLabel("You see " + npcName));
        } else {
            vboxP.add(new JLabel("No one is here."));
        }
        //Check if itemList is empty
        String interactableItem = room.getInteractableItem();
        if(interactableItem != null && !interactableItem.trim().isEmpty()){
            vboxP.add(new JLabel("You observe the area and see " + interactableItem)); //singular item for first iteration
        } else {
            vboxP.add(new JLabel("You look around and find nothing of interest here."));
        }

        JPanel p = new JPanel();
        p.add(vboxP);

        return p;
    }


}
