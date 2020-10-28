//package com.capstone;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.awt.*;
//import javax.swing.*;
//
//// This class tests the customizeFrame method in the GUI2nd class. *Zack*
//
//public class GUI2ndTest {
//
//    private JFrame gameFrame;
//    private JPanel testPanel;
//
//    public static void main(String[] args) {
//        GUI2ndTest guiTest = new GUI2ndTest();
//        guiTest.startMe();
//    }
//    @Before
//    // Quick Method (to initialize). *Zack*
//    public void startMe() {
//        gameFrame = new JFrame("Gotta Code 'Em All... JavaMon!");
//        testPanel = new JPanel();
//        customizeFrame(gameFrame);
//        gameFrame.setVisible(true);
//        gameFrame.setResizable(false);
//        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        testPanel.setBackground(Color.BLUE);
//        gameFrame.add(testPanel);
//    }
//
//    @Test
//    // This method forces JFrame into a size that's a percentage of the user's screen and centers it (nice and neat). *Zack*
//    private void customizeFrame(JFrame frame) {
//        Toolkit toolKit = Toolkit.getDefaultToolkit(); // Toolkit. *Zack*
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//        int ySize = ((int) toolKit.getScreenSize().getHeight()); // Initial height. *Zack*
//        int xSize = ((int) toolKit.getScreenSize().getWidth()); // Initial width. *Zack*
//        int windowHeight = (int) (Math.round(ySize * 0.93)); // Screen reduced to 95% height. *Zack*
//        int windowWidth = (int) (Math.round(xSize * 0.95)); // Screen reduced to 95% width. *Zack*
//        frame.setSize(new Dimension(windowWidth, windowHeight)); // Setting that screen size based on calculations. *Zack*
//        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2); // Set the JFrame to the center. *Zack*
//    }
//}