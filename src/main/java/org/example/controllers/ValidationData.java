package org.example.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationData {

    public static boolean checkFields(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkFields(String name, String surname, String username, String email, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || surname.isEmpty() || name.isEmpty() || email.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find()) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
            return false;
        }
    }

    public static boolean checkPassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
