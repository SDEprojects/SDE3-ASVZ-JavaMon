package com.capstone;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryAddition {
    private GUI2nd gui;

    public JTextArea textAreaInventory = new JTextArea();
    List<String> list;
    Typewriter tr;
    String output;
    Container con;

    void initInventoryFrame(){
        JFrame window = new JFrame();
        window.setSize(400,400);
        con = window.getContentPane();

        JPanel newPanel = new JPanel();
        newPanel.setBounds(con.getBounds());
        newPanel.add(textAreaInventory);

        textAreaInventory.setEditable(false);
        con.add(newPanel);
        window.setVisible(true);
    }
    public void updateTextAreaInventory(List<String> inventory){
        //System.out.println(list+"------------------------------");
        initInventoryFrame();
        list = new ArrayList<>();
        //Player py = gui.getPlayer1();
        ///System.out.println(py.toString()+"-------Player-------");
        list.addAll(inventory);
        System.out.println(list+"------------+++------------------");
        //System.out.println(py.getInventory()+"---------------------");

        /*output = String.valueOf(list);
        tr  = new Typewriter(textAreaInventory,output);*/
    }

    public JTextArea getTextAreaInventory() {
        return textAreaInventory;
    }

    public static void main(String[] args) {
        InventoryAddition nia = new InventoryAddition();
        //nia.updateTextAreaInventory();
    }
}
