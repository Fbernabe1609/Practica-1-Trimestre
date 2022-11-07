package org.example.views;

import javax.swing.*;
import java.awt.*;

public class Home {
    JPanel bodyPanel;
    private JLabel imageLabel;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel buttonsPanel;
    private JButton exitButton;

    public Home() {
        loginButton.addActionListener(e -> Login.start());
        registerButton.addActionListener(e -> Register.start());
        exitButton.addActionListener(e -> System.exit(0));
    }

}
