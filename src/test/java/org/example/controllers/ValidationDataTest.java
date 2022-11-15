package org.example.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ValidationDataTest {

    @Test
    void checkFields() {
        boolean empty;
        String username = "Pepe";
        String password = "123";
        if (username.isEmpty() || password.isEmpty()) {
            empty = false;
        } else {
            empty = true;
        }
        Assertions.assertTrue(empty);
    }

    @Test
    void testCheckFields() {
        boolean empty;
        String username = "Pepe";
        String password = "123";
        String surname = "pepe";
        String name = "hola";
        String email = "p@gmail.com";
        String confirmPassword = "123";
        if (username.isEmpty() || password.isEmpty() || surname.isEmpty() || name.isEmpty() || email.isEmpty() || confirmPassword.isEmpty()) {
            empty = false;
        } else {
            empty = true;
        }
        Assertions.assertTrue(empty);
    }

    @Test
    void checkEmail() {
        String email = "fbernabe160903@gmail.com";
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        Assertions.assertTrue(matcher.find());
    }

    @Test
    void checkPassword() {
        String pass1 = "Admin123";
        String pass2 = "Admin123";
        boolean equals = pass1.equals(pass2);
        Assertions.assertTrue(equals);
    }

//    @Test
//    void checkPassword() {
//        String pass1 = "Admin123";
//        String pass2 = "Admin123";
//        Assertions.assertEquals(pass1,pass2);
//    }
}