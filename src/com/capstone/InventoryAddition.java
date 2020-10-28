package com.capstone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryAddition {
    private GUI2nd gui;

    public JTextArea textAreaInventory = new JTextArea();
    private List<String> list = new ArrayList<>();

    Typewriter tr;
    String output;
    Container con;

    /*void initInventoryFrame(){
        JFrame window = new JFrame();
        window.setSize(400,400);
        con = window.getContentPane();

        JPanel newPanel = new JPanel();
        newPanel.setBounds(con.getBounds());
        newPanel.add(textAreaInventory);

        textAreaInventory.setEditable(false);
        con.add(newPanel);
        window.setVisible(true);
    }*/

    public void updateTextAreaInventory(List<String> inventory){
        list = new ArrayList<>();
        list.addAll(inventory);
    }

    public JTextArea getTextAreaInventory() {
        return textAreaInventory;
    }

    public static void main(String[] args) {
        InventoryAddition nia = new InventoryAddition();
        //nia.updateTextAreaInventory();
    }
}
