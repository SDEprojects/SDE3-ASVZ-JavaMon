package com.capstone;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CodexGUI {
    JFrame codexWindow = new JFrame("Codex");
    Font titleFont = new Font("Futura", Font.BOLD, 18);
    Font generalFont = new Font("Futura", Font.PLAIN, 16);
    Codex cdx = new Codex();
    public JTextArea codexTextArea = new JTextArea(15,26);


    final int width = 450;
    final int height = 500;
    final int borderSize = 25;
    final int panelWidth = width - borderSize*2;
    final int headerHeight = 50;
    final int gap = 15;
    final int codexY = borderSize + headerHeight + gap;
    final int textHeight = height - borderSize*2 - codexY; //500-115=385

    public void displayLog(JButton CodexButton){
//        Codex log = new Codex();
// Using a string temporarily
        String log = cdx.readCodex();

        codexWindow.setSize(width, height);
        codexWindow.setResizable(false);
        codexWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        codexWindow.getContentPane().setBackground(Color.green);
        codexWindow.setLayout(null);

        Point popupLocation = CodexButton.getLocationOnScreen();
        codexWindow.setLocation((int)popupLocation.getX()-codexWindow.getWidth(),
                (int)popupLocation.getY()-codexWindow.getHeight());

        // Instantiating a container for Codex
        Container codexContainer = codexWindow.getContentPane();

        // Header Label
        JLabel codexHeader = new JLabel("Codex of Past Turns");
        codexHeader.setFont(titleFont);
        codexHeader.setForeground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(borderSize, borderSize, panelWidth, headerHeight);
        headerPanel.add(codexHeader);
        headerPanel.setBackground(Color.red);

        // Codex Panel
        JPanel codexPanel = new JPanel();
        codexPanel.setBounds(borderSize, codexY, panelWidth, textHeight);
        codexPanel.setBackground(Color.WHITE);

        // Codex Text Area
//        JTextArea codexTextArea = new JTextArea(16,22);
        codexTextArea.setFont(generalFont);
        codexTextArea.setText(log);
        codexTextArea.setEditable(false);

        codexPanel.add(codexTextArea);

        // Scroll Bar
        JScrollPane scroll = new JScrollPane(codexTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        codexPanel.add(scroll);

        codexContainer.add(headerPanel);
        codexContainer.add(codexPanel);
        codexWindow.setVisible(true);
    }

    public static void main(String[] args){
        CodexGUI codex = new CodexGUI();
//        codex.displayLog();
    }
}
