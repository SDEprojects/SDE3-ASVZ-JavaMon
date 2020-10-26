package com.capstone;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.border.*;

// Main GUI.
public class GUI2nd extends JFrame { // Added 'extends JFrame'. *Zack*

    // Main JFrame. *Zack*
    private JFrame gameFrame; // *Zack*

    // Start Screen JPanels. *Zack*
    private JPanel startScreenTextPanel; // *Zack*
    private JPanel startScreenBackgroundPanel; // *Zack*

    // Start Screen JLabels. *Zack*
    private JLabel backgroundLabel; // *Zack*

    // Start Screen Image Icons. *Zack*
    private ImageIcon backgroundIcon; // *Zack*

    // Start Screen Fonts. *Zack*
    private final Font startButtonFont = new Font("Times New Roman", Font.PLAIN, 25); // Start button. *Zack*
    private final Font startGameText = new Font("Bold", Font.BOLD, 20); // Start screen text. *Zack*

    // String Arrays. *Zack*
    private String[] choiceDisplayArr = {"Bulbasaur (Grass-Type)", "Charmander (Fire-Type)", "Squirtle (Water-Type)"};
    private String[] choiceActionCommandArr = {"bulbasaur", "charmander", "squirtle"};

    // Creates and Initializes the text area.
    JTextArea commonDisplay = new JTextArea(20,25);
    JTextArea pokemonDisplay = new JTextArea(20,10);
    JTextArea mapDisplay = new JTextArea(20,10);
    JTextArea roomDisplay = new JTextArea(6,50);

    private PrintStream roomDisplayOut = new PrintStream(new CustomOutputStream(roomDisplay));
    private PrintStream commonDisplayOut = new PrintStream(new CustomOutputStream(commonDisplay));
    private PrintStream mapDisplayOut = new PrintStream(new CustomOutputStream(mapDisplay));
    PrintStream pokemonDisplayOut = new PrintStream(new CustomOutputStream(pokemonDisplay));

    private GameEngine gameEngine = new GameEngine();
    private CombatEngineGui combatEngine = new CombatEngineGui();
    private String starter;
    private Player player1 = new Player();
    private InitXML game = new InitXML();
    private TextParserGUI parser = new TextParserGUI();
    private JPanel mainPanel;

    //Pokemon Image Icons
    private ImageIcon balbasaurIcon;
    private ImageIcon charmanderIcon;
    private ImageIcon squirtleIcon;

    //Pokemon Image Label
    private JLabel pokemonImageLabel;

    // Main Method. *Zack*
    public static void main(String[] args) {
        GUI2nd gui = new GUI2nd();
        gui.game.initAttacks(); //must be initialized before pokemon
        gui.game.initPokemon(); //must be initialized before npcs
        gui.game.initNPCs(); //must be initialized before rooms
        gui.game.initRooms();
        gui.game.initItems();
        gui.chooseStarter(gui.game, gui.player1);
    }

    // *Zack* This method forces JFrame into a size that's a percentage of the user's screen and centers it (nice and neat).
    private void MakeFrameNinetyFivePercent(JFrame frame) {
        Toolkit toolKit = Toolkit.getDefaultToolkit(); // *Zack* Toolkit
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int ySize = ((int) toolKit.getScreenSize().getHeight()); // *Zack* Initial height.
        int xSize = ((int) toolKit.getScreenSize().getWidth()); // *Zack* Initial width.
        int windowHeight = (int) (Math.round(ySize * 0.93)); // *Zack* Screen reduced to 93% height.
        int windowWidth = (int) (Math.round(xSize * 0.95)); // *Zack* Screen reduced to 95% width.
        frame.setSize(new Dimension(windowWidth, windowHeight)); // *Zack* Setting that screen size based on calculations.
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2); // *Zack* Set the JFrame to the center.
    }

    // Create Images for Pokemon Types
    private void createPokemonTypeImages() { // Moved method here for neatness and readability. *Zack*
        String balbasaurPath = "images/Balbasaur-Pokemon.png";
        String charmanderPath = "images/Charmander-Pokemon.png";
        String squirtlePath = "images/Squirtle-Pokemon.png";

        Image balbasaurImg = transformImage(createImageIcon(balbasaurPath, ""), 120, 120);
        Image charmanderImg = transformImage(createImageIcon(charmanderPath, ""), 120, 120);
        Image squirtleImg = transformImage(createImageIcon(squirtlePath, ""), 120, 120);

        balbasaurIcon = new ImageIcon(balbasaurImg);
        charmanderIcon = new ImageIcon(charmanderImg);
        squirtleIcon = new ImageIcon(squirtleImg);
    }

    // Transforms the Icon's Image to Scaled Instance (based on height and width). *Zack*
    private Image transformImage(ImageIcon icon, int width, int height) { // *Zack* Moved method here for neatness and readability.
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return newimg;
    }

    // Make Display Text Area Non-Editable. *Zack*
    private void setDisplayNonEditable() { // *Zack* Moved method here for neatness and readability.
        roomDisplay.setEditable(false);
        commonDisplay.setEditable(false);
        mapDisplay.setEditable(false);
        pokemonDisplay.setEditable(false);
    }

    // Change the Pokemon Image Label (based on name). *Zack*
    protected void setPokemonImageLabel(Pokemon pokemon) { // *Zack* Moved method here for neatness and readability.
        switch(pokemon.getName()) {
            case "Balbasaur":
                pokemonImageLabel.setIcon(balbasaurIcon);
                break;
            case "Charmander":
                pokemonImageLabel.setIcon(charmanderIcon);
                break;
            case "Squirtle":
                pokemonImageLabel.setIcon(squirtleIcon);
                break;
        }
    }

    // Select the Starter Pokemon and Initialize the Game. *Zack*
    public void chooseStarter(InitXML game, Player player) {

        setDisplayNonEditable();
        createPokemonTypeImages();

        // Main Game Frame Window. *Zack*
        gameFrame = new JFrame("Gotta Code 'Em All... JavaMon!"); // *Zack*
        MakeFrameNinetyFivePercent(gameFrame); // Must stay valid! *Zack*
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Must stay valid! *Zack*
        gameFrame.setResizable(false); // Must stay false! *Zack*

        // Start Screen Background Panel (CENTER). *Zack*
        startScreenBackgroundPanel = new JPanel(new BorderLayout(0, 0)); // *Zack*
        backgroundIcon = new ImageIcon(this.getClass().getResource("images/OakPic.jpg")); // Stores the background picture in an ImageIcon. *Zack*
        backgroundLabel = new JLabel(backgroundIcon); // Sets the stored background picture into a JLabel. *Zack*
        startScreenBackgroundPanel.add(backgroundLabel); // Slaps the JLabel to the JPanel. *Zack*
        startScreenBackgroundPanel.setVisible(true); // Must stay true! *Zack*

        // Start Screen Text Panel (PAGE_END). *Zack*
        startScreenTextPanel = new JPanel(); // *Zack*
        startScreenTextPanel.setPreferredSize(new Dimension(0, 75)); // *Zack*
        startScreenTextPanel.setBackground(Color.WHITE); // *Zack*
        startScreenTextPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true)); // *Zack*
        startScreenTextPanel.setVisible(true); // *Zack*

        JLabel pokemonImageLabel = getPokemonImageLabel();
        startScreenTextPanel.add(pokemonImageLabel);

        startScreenTextPanel.setFont(startGameText); // Bold font. *Zack*
        startScreenTextPanel.add(new JLabel("You're in OakRoom"));
        startScreenTextPanel.add(new JLabel("..."));
        startScreenTextPanel.add(new JLabel("Professor Oak: Hey! You're finally here, I've been waiting for you."));
        startScreenTextPanel.add(new JLabel("I'm going on vacation soon... and the flight I'm going on has a strict 1 Pokemon carry on limit."));
        startScreenTextPanel.add(new JLabel("I'm going to need you to look after one while I'm gone! I'll even let you choose who you want to take!"));
        startScreenTextPanel.add(new JLabel("..."));

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        ActionListener radioButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                starter = event.getActionCommand();
                System.out.println("actionCommand: " + starter);
            }
        };

        startScreenTextPanel.add(new JLabel("..."));
        startScreenTextPanel.add(new JLabel("Choose One:"));
        for (int i = 0; i < choiceDisplayArr.length; i++) {
            JRadioButton radio = new JRadioButton(choiceDisplayArr[i]);
            radio.setActionCommand(choiceActionCommandArr[i]);
            radio.addActionListener(radioButtonListener);
            group.add(radio);
            startScreenTextPanel.add(radio);
            if (i == 0) { // Conditionals to color the radio group. *Zack*
                Color bulbaGreen = new Color(0,128,0);
                radio.setBackground(bulbaGreen);
                radio.setForeground(Color.WHITE);
            }
            else if (i == 1) {
                radio.setBackground(Color.RED);
                radio.setForeground(Color.WHITE);
            }
            else if (i == 2) {
                radio.setBackground(Color.BLUE);
                radio.setForeground(Color.WHITE);
            }
        }

        JButton startButton = new JButton("PLAY");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(startButtonFont);

        startScreenTextPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("selected starter: " + starter);
                for (Pokemon pokemon : game.listOfPokemon) {
                    if (pokemon.getName().equalsIgnoreCase(starter)) {
                        player.playersPokemon.add(pokemon);
                        System.out.println("You chose: " + starter);
                        for (Pokemon playersFirstPokemon : player.playersPokemon) {
                            System.setOut(pokemonDisplayOut);
                            playersFirstPokemon.displayOutStatsAndAll();
                            System.setOut(System.out);

                            displayOutStatsAndAll(playersFirstPokemon, player);
                            setPokemonImageLabel(playersFirstPokemon);
                            parser.checkPlayerCommand(game, gameEngine,combatEngine, player1, "check map", commonDisplayOut, mapDisplayOut, roomDisplayOut,pokemonDisplayOut, pokemonDisplay);
                        }
                    }
                }
            }
        });
        gameFrame.add(startScreenBackgroundPanel, BorderLayout.CENTER); // I wouldn't mess with this line. *Zack*
        gameFrame.add(startScreenTextPanel, BorderLayout.SOUTH); // I wouldn't mess with this line either. *Zack*
        gameFrame.setVisible(true); // Must stay true! *Zack*
    }

    // Pokemon Image Label
    private JLabel getPokemonImageLabel() {
        Image img = transformImage(createImageIcon("images", ""), 120, 120);
        ImageIcon icon = new ImageIcon(img);  // transform it back
        JLabel imageLabel = new JLabel("", icon, JLabel.CENTER);
        return imageLabel;
    }

    // Returns an ImageIcon (or null if path invalid). *Zack*
    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Display details of the player.
     */

    public void displayOutStatsAndAll(Pokemon pokemon, Player player) {

        JScrollPane scroll = new JScrollPane (commonDisplay,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        String roomName = "Oak's Lab";
        Room startingRoom = game.getRoom(roomName);
        player1.setCurrentRoom(startingRoom);

        //Display room details
        showRoomDetails(player);

        //Create input panel
        JPanel inputP = new JPanel();
        inputP.setLayout(new BoxLayout(inputP, BoxLayout.PAGE_AXIS));
        JTextField inputTF = new JTextField(20);
        inputP.add(new JLabel("Enter your command: "));
        inputP.add(inputTF);
        inputTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //JOptionPane.showMessageDialog(null, "hello");
                 //if(e.getKeyCode() == KeyEvent.VK_ENTER){​​
                //mine
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    performAction(inputTF, player);

                }
            }
        });


        JButton submitB = new JButton("Submit");
        inputP.add(submitB);
        submitB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                performAction(inputTF, player);

            }

        });

        //Create room Panel with room details display
        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new BorderLayout());
        roomPanel.add(getBorderedPanel(roomDisplay), BorderLayout.CENTER);

        //the pokemon Details Panel
        JPanel pokemonPanel = new JPanel();
        pokemonPanel.setLayout(new BoxLayout(pokemonPanel, BoxLayout.PAGE_AXIS));
        pokemonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pokemonImageLabel = new JLabel("", balbasaurIcon, JLabel.CENTER);
        pokemonPanel.add(pokemonImageLabel);
        pokemonPanel.add(pokemonDisplay);

        //Create Middle Panel with
        //the pokemon Details Panel,
        //the Output Display Panel and the Bag Panel.

        JPanel middlePanel = new JPanel();


        middlePanel.setLayout(new BorderLayout());

        middlePanel.add(getBorderedPanel(pokemonPanel), BorderLayout.WEST);
        //middlePanel.add(getBorderedPanel(commonDisplay), BorderLayout.CENTER);
        middlePanel.add(getBorderedPanel(scroll), BorderLayout.CENTER);
        middlePanel.add(getBorderedPanel(mapDisplay), BorderLayout.EAST);

        //Setup Main Panel with
        //RoomDetails Panel, the Middle Panel and the Input Panel.
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Add RoomDetails Panel
        mainPanel.add(roomPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(getBorderedPanel(inputP), BorderLayout.SOUTH);

        //Add the mainPanel to the window.
        gameFrame.getContentPane().removeAll();
        gameFrame.setLayout(new BorderLayout());
        gameFrame.getContentPane().add(mainPanel);

        //Revalidate to make sure that the newly added components are shown.
        gameFrame.revalidate();
    }
    private void performAction(JTextField inputTF, Player player) {
        commonDisplay.setText("");
        parser.checkPlayerCommand(game, gameEngine, combatEngine, player1, inputTF.getText(), commonDisplayOut, mapDisplayOut, roomDisplayOut, pokemonDisplayOut, pokemonDisplay);
        showRoomDetails(player);
        parser.checkPlayerCommand(game, gameEngine, combatEngine, player1, "check map", commonDisplayOut, mapDisplayOut, roomDisplayOut, pokemonDisplayOut, pokemonDisplay);
        pokemonDisplay.setText("");
        System.setOut(pokemonDisplayOut);
        player1.getPlayersPokemon().get(0).displayOutStatsAndAll();
        System.setOut(System.out);

        inputTF.setText("");
    }

    /**
     * Adds the given component to a JPanel with border.
     */

    private JPanel getBorderedPanel(JComponent comp) {
        JPanel p = new JPanel();
        Border blueLine = BorderFactory.createLineBorder(Color.blue);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        CompoundBorder compound = BorderFactory.createCompoundBorder(blueLine, emptyBorder);
        p.setBorder(compound);
        //Add component to the panel
        p.add(comp);
        return p;
    }

    /**
     * Show the details of the current room of the given player.
     */
    private void showRoomDetails(Player player) {
        roomDisplayOut.flush();
        System.setOut(roomDisplayOut);
        player.showRoomDetails();
        System.setOut(System.out);
    }
}