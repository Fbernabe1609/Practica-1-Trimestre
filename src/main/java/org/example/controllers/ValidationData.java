package org.example.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationData {

    public static boolean checkFields(String username, String password) {
        boolean empty;
        if (username.isEmpty() || password.isEmpty()) {
            empty = false;
        } else {
            empty = true;
        }

        return empty;
    }

    public static boolean checkFields(String name, String surname, String username, String email, String password, String confirmPassword) {
        boolean empty;
        if (username.isEmpty() || password.isEmpty() || surname.isEmpty() || name.isEmpty() || email.isEmpty() || confirmPassword.isEmpty()) {
            empty = false;
        } else {
            empty = true;
        }
        return empty;
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean checkPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
