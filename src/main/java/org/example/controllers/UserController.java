package org.example.controllers;

import org.bson.Document;
import org.example.models.User;

public class UserController implements FieldVariables {

    private static User user;


    public static void createUser(String username2, String password2) {

        Document doc = DbController.getDoc(username2, password2);
        user = new User(doc.get(name).toString(),
                doc.get(surname).toString(),
                doc.get(username).toString(),
                doc.get(email).toString(),
                doc.get(password).toString());
    }

    public static User getUser() {
        return user;
    }
}