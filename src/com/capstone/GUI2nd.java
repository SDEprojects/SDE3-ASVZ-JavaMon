package com.capstone;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*; // *Sanju*

// Main GUI. // *Zack*
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
    private final Font startGameText = new Font("Sans Serif", Font.BOLD, 20); // Start screen text. *Zack*
    private final Font generalFont = new Font("Futura", Font.PLAIN, 16); // *Sanju*

    // String Arrays. *Zack*
    private String[] choiceDisplayArr = {"Bulbasaur (Grass-Type)", "Charmander (Fire-Type)", "Squirtle (Water-Type)"};
    private String[] choiceActionCommandArr = {"bulbasaur", "charmander", "squirtle"};

    // Radio Button. *Zack*
    private JRadioButton radio;

    // Initialize Text Area. *Zack*
    JTextArea commonDisplay = new JTextArea(20,25);
    JTextArea pokemonDisplay = new JTextArea(20,10);
    JTextArea mapDisplay = new JTextArea(20,10);
    JTextArea roomDisplay = new JTextArea(6,50);

    // PrintStream Fields. *Zack*
    private PrintStream roomDisplayOut = new PrintStream(new CustomOutputStream(roomDisplay));
    private PrintStream commonDisplayOut = new PrintStream(new CustomOutputStream(commonDisplay));
    private PrintStream mapDisplayOut = new PrintStream(new CustomOutputStream(mapDisplay));
    PrintStream pokemonDisplayOut = new PrintStream(new CustomOutputStream(pokemonDisplay));

    // Everything After the 'PLAY' Button. *Zack*
    private Typewriter tWriter; // *Sanju*
    private GameEngine gameEngine = new GameEngine();
    private CombatEngineGui combatEngine = new CombatEngineGui();
    private String starter;
    private Player player1 = new Player();
    private InitXML game = new InitXML();
    private TextParserGUI parser = new TextParserGUI();
    private JPanel mainPanel;

    // Pokemon Image Icons. *Zack*
    private ImageIcon balbasaurIcon;
    private ImageIcon charmanderIcon;
    private ImageIcon squirtleIcon;

    // Pokemon Image Label. *Zack*
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

    // Select the Starter Pokemon and Initialize the Game. *Zack*
    public void chooseStarter(InitXML game, Player player) {

        setGeneralFont(); // *Sanju*
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

        pokemonImageLabel = getPokemonImageLabel();
        startScreenTextPanel.add(pokemonImageLabel);

        startScreenTextPanel.setFont(startGameText); // Bold font. *Zack*
        startScreenTextPanel.add(new JLabel(""));
        startScreenTextPanel.add(new JLabel(""));
        startScreenTextPanel.add(new JLabel("Professor Oak: Hey! You're finally here, I've been waiting for you."));
        startScreenTextPanel.add(new JLabel("I'm going on vacation soon, and the flight I'm going on has a strict \"1 Pokemon carry on limit\"."));
        startScreenTextPanel.add(new JLabel("I'm going to need you to look after one while I'm gone! I'll even let you choose who you want to take!"));
        startScreenTextPanel.add(new JLabel("..."));

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        ActionListener radioButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                starter = event.getActionCommand();
                // System.out.println("actionCommand: " + starter); *Zack*
            }
        };

        // *Sanju*
        // tWriter = new Typewriter(startLineTextArea, startLine);
        // tWriter.start();

        // Hover Event (start screen Pokemon image and stats prior to clicking 'PLAY'). *Sanju*
        MouseAdapter radioButtonMouseListener = new MouseAdapter() {
            JDialog dialog = new JDialog();
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                System.out.println("mouse entered");
               /* if(e.getComponent().getName().equals("Bulbasaur") ||
                        e.getComponent().getName().equals("Charmander")||
                        e.getComponent().getName().equals("Squirtle"))
                {*/
                //JOptionPane.showMessageDialog(null,e.getComponent().getName());
                final JOptionPane optionPane = new JOptionPane(e.getComponent().getName(), JOptionPane.INFORMATION_MESSAGE
                        ,JOptionPane.DEFAULT_OPTION, getPokemonImageLabel().getIcon(), new Object[]{},null);
                //}

                dialog.setTitle("Pokemon Stats"); // *Zack*
                dialog.setModal(false);//setting this to false allows us to access the other parts of the program
                dialog.setResizable(false);
                dialog.setLocation(e.getComponent().getX()+25,e.getComponent().getY()+600);
                dialog.setSize(300,300);
                dialog.setContentPane(optionPane);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                //dialog.pack(); *Zack*
                dialog.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                System.out.println("mouse exited");
                dialog.dispose();
                dialog.setVisible(false);
            }
        };

        startScreenTextPanel.add(new JLabel("..."));
        startScreenTextPanel.add(new JLabel("Choose One:"));

        for (int i = 0; i < choiceDisplayArr.length; i++) {
            radio = new JRadioButton(choiceDisplayArr[i]);
            radio.setName(choiceDisplayArr[i].split(" ", 2)[0]); // *Sanju*
            System.out.println(radio.getName()); // *Sanju*
            radio.setActionCommand(choiceActionCommandArr[i]);
            radio.addActionListener(radioButtonListener);
            radio.addMouseListener(radioButtonMouseListener);
            radio.setForeground(Color.RED);
            radio.setFont(startGameText);
            radio.setBorder(null);
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
                // System.out.println("selected starter: " + starter); *Sanju*
                for (Pokemon pokemon : game.listOfPokemon) {
                    if (pokemon.getName().equalsIgnoreCase(starter)) {
                        player.playersPokemon.add(pokemon);
                        // System.out.println("You chose: " + starter); *Sanju*
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

    // Font for Text Areas. *Sanju*
    private void setGeneralFont() {
        roomDisplay.setFont(generalFont);
        commonDisplay.setFont(generalFont);
        mapDisplay.setFont(generalFont);
        pokemonDisplay.setFont(generalFont);
    }

    // Make Display Text Area Non-Editable. *Zack*
    private void setDisplayNonEditable() { // *Zack* Moved method here for neatness and readability.
        roomDisplay.setEditable(false);
        commonDisplay.setEditable(false);
        mapDisplay.setEditable(false);
        pokemonDisplay.setEditable(false);
    }

    // This method forces JFrame into a size that's a percentage of the user's screen and centers it (nice and neat). *Zack*
    private void MakeFrameNinetyFivePercent(JFrame frame) {
        Toolkit toolKit = Toolkit.getDefaultToolkit(); // Toolkit. *Zack*
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int ySize = ((int) toolKit.getScreenSize().getHeight()); // Initial height. *Zack*
        int xSize = ((int) toolKit.getScreenSize().getWidth()); // Initial width. *Zack*
        int windowHeight = (int) (Math.round(ySize * 0.93)); // Screen reduced to 95% height. *Zack*
        int windowWidth = (int) (Math.round(xSize * 0.95)); // Screen reduced to 95% width. *Zack*
        frame.setSize(new Dimension(windowWidth, windowHeight)); // Setting that screen size based on calculations. *Zack*
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2); // Set the JFrame to the center. *Zack*
    }

    // Create Images for Pokemon Types. *Zack*
    private void createPokemonTypeImages() {
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

    // Pokemon Image Label
    private JLabel getPokemonImageLabel() {
        Image img = transformImage(createImageIcon("images", ""), 120, 120);
        ImageIcon icon = new ImageIcon(img);  // transform it back
        JLabel imageLabel = new JLabel("", icon, JLabel.CENTER);
        return imageLabel;
    }

    // Change the Pokemon Image Label (based on name). *Zack*
    protected void setPokemonImageLabel(Pokemon pokemon) {
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

    // Transforms the Icon's Image to Scaled Instance (based on height and width). *Zack*
    private Image transformImage(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return newimg;
    }

    // Player Details. *Zack*
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

    // Perform An Action. *Zack*
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

    // Add Given Component to JPanel w/ Border. *Zack*
    private JPanel getBorderedPanel(JComponent comp) {
        JPanel p = new JPanel();
        Border blueLine = BorderFactory.createLineBorder(Color.RED);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        CompoundBorder compound = BorderFactory.createCompoundBorder(blueLine, emptyBorder);
        p.setBorder(compound);
        p.add(comp);
        return p;
    }

    // Current Room (player details). *Zack*
    private void showRoomDetails(Player player) {
        roomDisplayOut.flush();
        System.setOut(roomDisplayOut);
        player.showRoomDetails();
        System.setOut(System.out);
    }

    // *Sanju*
    public Player getPlayer1() {
        return player1;
    }
}