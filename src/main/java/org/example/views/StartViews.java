package org.example.views;

import javax.swing.*;

public class StartViews {

    static JFrame appFrame = new JFrame("SuperApp");

    public static void startViews() {

        appFrame.setContentPane(new Home().bodyPanel);
        appFrame.pack();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setVisible(true);
        appFrame.setResizable(false);
        appFrame.setSize(500,300);
        appFrame.setLocationRelativeTo(null);
    }
}
