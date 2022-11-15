package org.example.views;

import javax.swing.*;
import java.awt.*;

public class Home {
    JPanel bodyPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;
    private JLabel bodyLabel;
    private JPanel buttonsPanel;
    private JLabel imageLabel;

    public Home() {
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> Login.start());
        registerButton.addActionListener(e -> Register.start());
        exitButton.addActionListener(e -> System.exit(0));
    }

}
