package com.capstone;

import javax.swing.*;
import java.awt.*;

public class CommandListGUI {
    //Designing a map frame
   JFrame commandWindow = new JFrame("POKEMANDS");
    Font fontTitle = new Font("Times New Roman",Font.BOLD,25);
    Font fontCommand = new Font("Times New Roman",Font.PLAIN,23);
   public void displayCommandList(JButton btn){
       commandWindow.setSize(600, 300);
       commandWindow.setResizable(false);
       commandWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       commandWindow.getContentPane().setBackground(Color.BLACK);
       commandWindow.setLayout(null);

       Point popUpLocation = btn.getLocationOnScreen();
       commandWindow.setLocation((int)popUpLocation.getX()-commandWindow.getWidth()
               ,(int)popUpLocation.getY()-commandWindow.getHeight());
       //Instantiating a container for command list
       Container comContainer = commandWindow.getContentPane();

       //creating labels
       JLabel commandHeaderLabel = new JLabel("List Of Useful Commands");
        commandHeaderLabel.setFont(fontTitle);
       JPanel headerPanel = new JPanel();
       headerPanel.setBounds(10,10,565, 50);
       headerPanel.setBackground(Color.green);
       headerPanel.add(commandHeaderLabel);

       String newLine = System.getProperty("line.separator");
       String commands = String.join(newLine,
               "To move: go <north,east,south, or west",
               "To talk: talk <NPC's name>",
               "To interact: interact <interactable object>",
               "To check the map: check map",
               "To check your inventory and wallet: check bag/inventory",
               "To display this help prompt again: get help");

       JPanel commandPanel = new JPanel();
       commandPanel.setBounds(10, 65, 565,190);
       commandPanel.setBackground(Color.GREEN);

       JTextArea commandTextArea = new JTextArea();
       commandTextArea.setFont(fontCommand);
       commandTextArea.setBackground(Color.LIGHT_GRAY);
       //commandTextArea.setBounds(15, 100,550,300);
       commandTextArea.setText(commands);
       commandPanel.add(commandTextArea);

       comContainer.add(headerPanel);
       comContainer.add(commandPanel);
       commandWindow.setVisible(true);
   }

    public static void main(String[] args) {
        CommandListGUI display = new CommandListGUI();
        //display.displayCommandList();
    }
}
