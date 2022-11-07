package org.example.views;

import javax.swing.*;

public class StartViews {

    static JFrame appFrame = new JFrame("SuperApp");
    static JFrame dataViewerFrame = new JFrame("SuperApp");

    public static void startViews() {

        appFrame.setContentPane(new Home().bodyPanel);
        appFrame.pack();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setVisible(true);
        appFrame.setResizable(false);
        appFrame.setSize(500,500);
        appFrame.setLocationRelativeTo(null);
    }

    public static void startDataViewer() {

        dataViewerFrame.setContentPane(new DataViewer().bodyPanel);
        dataViewerFrame.pack();
        dataViewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dataViewerFrame.setVisible(true);
//        dataViewerFrame.setResizable(false);
        dataViewerFrame.setSize(900,300);
        dataViewerFrame.setLocationRelativeTo(null);
        appFrame.dispose();
    }
}
