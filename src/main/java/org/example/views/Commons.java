package org.example.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URL;
import java.util.Objects;

public interface Commons {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    URL url = classloader.getResource("error.gif");
    ImageIcon icono = new ImageIcon(Objects.requireNonNull(url));

    default void focusEvent(JTextField textField, Border border, Border borderDefault) {

        FocusAdapter listener = new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                super.focusGained(e);
                textField.setBorder(border);
            }

            @Override
            public void focusLost(FocusEvent e) {

                super.focusLost(e);
                textField.setBorder(borderDefault);
            }
        };

        textField.addFocusListener(listener);
    }
}
