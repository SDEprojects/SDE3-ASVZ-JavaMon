package com.capstone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {
    
    //private ActionListener titleScreenHandler;


    private JFrame window;
    private JPanel titleNamePanel;
    private JPanel startButtonPanel;
    private JTextArea mainTextArea;
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private JButton startButton;

    TitleScreenHandler titleScreenHandler = new TitleScreenHandler();


    public static void main(String[] args) {

        new GUI();

    }




//    public GUI(ActionListener titleScreenHandler) {
//
//        this.titleScreenHandler = titleScreenHandler;
//    }

    public GUI() {

        // Initializing JFrame Window
        window = new JFrame();
        window.setSize(800, 600);
        //window.add(titleNamePanel, BorderLayout.CENTER);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);


        //Initializing Title Name Panel
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.BLACK);


        JLabel titleNameLabel = new JLabel("POKEMON");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 60));

        titleNamePanel.add(titleNameLabel);

        // Initializing Start Button Panel
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.BLACK);


        JButton startButton = new JButton("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(normalFont);
        startButton.addActionListener(titleScreenHandler);

        startButtonPanel.add(startButton);

        window.add(titleNamePanel);
        window.add(startButtonPanel);

        window.setVisible(true);


    }

    public void createGameScreen()  {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        // Main Text Panel
        JPanel mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.BLACK);


        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        mainTextPanel.add(mainTextArea);

        window.add(mainTextPanel);

    }
    public class TitleScreenHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

        }
    }

}

