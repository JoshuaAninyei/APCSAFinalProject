package com.FirstJavaProject;

import javax.swing.*;
//import java.awt.event.*;

class FirstJavaProject {
public static void main(String[] args) {
  System.out.println("Game is starting.");
  JFrame window = new JFrame();
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  window.setResizable(false);
  window.setTitle("2d gaem");

  GamePanel gamePanel = new GamePanel();
  window.add(gamePanel);

  window.pack();

  window.setLocationRelativeTo(null);
  window.setVisible(true);
  gamePanel.setUpGame();
  gamePanel.startGameThread();

}
}