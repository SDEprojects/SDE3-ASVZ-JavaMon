package com.capstone;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

// Main GUI. *Zack*
public class GUI2nd extends JFrame { // Added 'extends JFrame'. *Zack*

    // Main JFrame. *Zack*
    private JFrame gameFrame; // *Zack*

    // Start Screen JPanels. *Zack*
    private JPanel startScreenTextPanel; // *Zack*
    private JPanel startScreenBackgroundPanel; // *Zack*

//    // Start Screen JLabels. *Zack*
//    private JLabel backgroundLabel; // *Zack*

    // *Sanju*
    private JFrame window;
    private JPanel titleNamePanel, backgroundPanel;
    private Container con;
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private final Font startLineFont = new Font("Times New Roman", Font.BOLD, 25);
    private final Font generalFont = new Font("Futura", Font.PLAIN, 16); // Changes the main page font.
    // *Sanju*

//    // Start Screen Image Icons. *Zack*
//    private ImageIcon backgroundIcon; // *Zack*

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

    private Typewriter  tWriter; // *Sanju*
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
    private ImageIcon backgroundIcon; // *Sanju*

    //Pokemon Image Label
    private JLabel pokemonImageLabel;
    private JLabel backgroundLabel; // *Sanju*


    //Path of the starting screen image
    private String startPageImagePath = "images/pokemon.gif";
    private JRadioButton radio; // *Sanju*

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

    // Main Method. *Sanju*
//    public static void main(String[] args) {
//        GUI2nd gui = new GUI2nd();
//        gui.game.initAttacks(); //must be initialized before pokemon
//        gui.game.initPokemon(); //must be initialized before npcs
//        gui.game.initNPCs(); //must be initialized before rooms
//        gui.game.initRooms();
//        gui.game.initItems();
//        gui.initFrame();
//        gui.chooseStarter(gui.game, gui.player1);
//    }
    // *Sanju*

    // This method forces JFrame into a size that's a percentage of the user's screen and centers it (nice and neat). *Zack*
    private void MakeFrameNinetyFivePercent(JFrame frame) {
        Toolkit toolKit = Toolkit.getDefaultToolkit(); // Toolkit. *Zack*
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int ySize = ((int) toolKit.getScreenSize().getHeight()); // Initial height. *Zack*
        int xSize = ((int) toolKit.getScreenSize().getWidth()); // Initial width. *Zack*
        int windowHeight = (int) (Math.round(ySize * 0.93)); // Screen reduced to 93% height. *Zack*
        int windowWidth = (int) (Math.round(xSize * 0.95)); // Screen reduced to 95% width. *Zack*
        frame.setSize(new Dimension(windowWidth, windowHeight)); // Setting that screen size based on calculations. *Zack*
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2); // Set the JFrame to the center. *Zack*
    }

// InitFrame *Sanju*
    private void initFrame() {

        setDisplayNonEditable();
        setGeneralFont();
        createPokemonTypeImages();

        // Initializing JFrame Window
        window = new JFrame();
        window.setSize(1200, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        con = window.getContentPane();
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0,0,window.getWidth(),window.getHeight());

        backgroundLabel = new JLabel();
        backgroundLabel.setSize(backgroundPanel.getWidth(), backgroundPanel.getHeight());
        backgroundLabel.setIcon(backgroundIcon);

        backgroundPanel.add(backgroundLabel);
        con.add(backgroundLabel);

        window.setVisible(true);
    }
// *Sanju*

    // Create Images for Pokemon Types. *Zack*
    private void createPokemonTypeImages() { // Moved method here for neatness and readability. *Zack*
        String balbasaurPath = "images/Balbasaur-Pokemon.png";
        String charmanderPath = "images/Charmander-Pokemon.png";
        String squirtlePath = "images/Squirtle-Pokemon.png";
        String backgroundPath = "images/oak-room.jpg"; // *Sanju*

        Image balbasaurImg = transformImage(createImageIcon(balbasaurPath, ""), 120, 120);
        Image charmanderImg = transformImage(createImageIcon(charmanderPath, ""), 120, 120);
        Image squirtleImg = transformImage(createImageIcon(squirtlePath, ""), 120, 120);
        Image backgroundImg  = transformImage(createImageIcon(backgroundPath, ""), 1200, 600); // *Sanju*

        balbasaurIcon = new ImageIcon(balbasaurImg);
        charmanderIcon = new ImageIcon(charmanderImg);
        squirtleIcon = new ImageIcon(squirtleImg);
        backgroundIcon = new ImageIcon(backgroundImg); // *Sanju*
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

    // Change font on main game screen
    private void setGeneralFont(){
        roomDisplay.setFont(generalFont);
        commonDisplay.setFont(generalFont);
        mapDisplay.setFont(generalFont);
        pokemonDisplay.setFont(generalFont);
    }

//    // Change the Pokemon Image Label (based on name). *Zack*
//    protected void setPokemonImageLabel(Pokemon pokemon) { // Moved method here for neatness and readability. *Zack*
//        switch(pokemon.getName()) {
//            case "Balbasaur":
//                pokemonImageLabel.setIcon(balbasaurIcon);
//                break;
//            case "Charmander":
//                pokemonImageLabel.setIcon(charmanderIcon);
//                break;
//            case "Squirtle":
//                pokemonImageLabel.setIcon(squirtleIcon);
//                break;
//        }
//    }

//    SANJU'S CODE
    public void chooseStarter(InitXML game, Player player) {
        JPanel starterPokemonPanel = new JPanel();
        starterPokemonPanel.setLayout(new BoxLayout(starterPokemonPanel, BoxLayout.PAGE_AXIS)); //center the layout here later

        String newLine = System.getProperty("line.separator");
        String startLine = String.join(newLine,"","","","","",
                "You're in Oak's Lab","",
                "...",
                "Professor Oak: Hey! You're finally here, I've been waiting for you.",
                "I'm going on vacation soon... and the flight I'm going on has a strict 1 Pokemon carry on limit.",
                "I'm going to need you to look after one while I'm gone! I'll even let you choose who you want to take!",
                "...");
/*
        String startLine = "You're in OakRoom;...;Professor Oak: Hey! You're finally here, I've been waiting for you.;I'm going on vacation soon... and the flight I'm going on has a strict 1 Pokemon carry on limit.;I'm going to need you to look after one while I'm gone! I'll even let you choose who you want to take!;...";
*/
        JTextArea startLineTextArea = new JTextArea();
        startLineTextArea.setText(startLine);
        startLineTextArea.setHighlighter(null);
        startLineTextArea.setBorder(null);
        starterPokemonPanel.add(startLineTextArea);
        //startLineTextArea.setSize(1200,300);
        startLineTextArea.setBackground(new Color(0,0,0,0));
        startLineTextArea.setForeground(Color.black);
        startLineTextArea.setFont(startLineFont);
        startLineTextArea.setEditable(false);


        /*tWriter = new Typewriter(startLineTextArea, startLine);
        tWriter.start();*/

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        ActionListener radioButtonListener = event -> {
            starter = event.getActionCommand();
            // System.out.println("actionCommand: " + starter);
        };
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

                dialog.setTitle("POKEMON STATISTICS");
                dialog.setModal(false);//setting this to false allows us to access the other parts of the program
                dialog.setResizable(false);
                dialog.setLocation(e.getComponent().getX(),e.getComponent().getY()-150);
                dialog.setSize(300,200);
                dialog.setContentPane(optionPane);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                //window.requestFocus();
                dialog.pack();

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


        starterPokemonPanel.add(new JLabel("..."));
        starterPokemonPanel.add(new JLabel("Choose One:"));

        for (int i = 0; i < choiceDisplayArr.length; i++) {
            radio = new JRadioButton(choiceDisplayArr[i]);
            radio.setName(choiceDisplayArr[i].split(" ",2)[0]);//get the first word and set it as a name
            System.out.println(radio.getName());
            radio.setActionCommand(choiceActionCommandArr[i]);
            radio.addActionListener(radioButtonListener);
            radio.addMouseListener(radioButtonMouseListener);
            radio.setForeground(Color.red);
            radio.setFont(startLineFont);
            radio.setBorder(null);
            group.add(radio);
            starterPokemonPanel.add(radio);
        }

        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.RED);
        startButton.setFont(normalFont);

        starterPokemonPanel.add(startButton);
        startButton.addActionListener(e -> {
            //  System.out.println("selected starter: " + starter);
            for (Pokemon pokemon : game.listOfPokemon) {
                if (pokemon.getName().equalsIgnoreCase(starter)) {
                    player.playersPokemon.add(pokemon);
                    // System.out.println("You chose: " + starter);
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
        });

        //window.getContentPane().removeAll();
        //con.setLayout(new BorderLayout());
        //starterPokemonPanel.setBackground(new Color(0,0,0,65));


        //JPanel bPanel = getBorderedPanel(starterPokemonPanel);

        //bPanel.setBackground(new Color(0,0,0,100));
        //bp.setOpaque(false);
        con.add(starterPokemonPanel);
        //starterPokemonPanel.setBackground(new Color(0,0,0,0));
        starterPokemonPanel.setOpaque(false);
        //backgroundPanel.setOpaque(false);
        //con.add(bPanel, BorderLayout.CENTER);
        window.revalidate();
    }


    /* ZACK'S CODE
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

     */

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

    // Pokemon Image Label
    private JLabel getPokemonImageLabel() {
        Image img = transformImage(createImageIcon(startPageImagePath, ""), 120, 120);
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
        window.getContentPane().removeAll();
        window.setLayout(new BorderLayout());
        window.getContentPane().add(mainPanel);

        //Revalidate to make sure that the newly added components are shown.
        window.revalidate();
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

    public Player getPlayer1() {
        return player1;
    }
}