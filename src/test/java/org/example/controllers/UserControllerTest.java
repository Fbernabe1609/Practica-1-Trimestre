package org.example.controllers;

import org.example.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserControllerTest {

    @Test
    void createUser() {
        String name = "nombre";
        String surname = "apellidos";
        String username = "nombre_de_usuario";
        String email = "email";
        String password = "contraseña";
        User user = new User(name,surname,username,email,password);
        Assertions.assertNotNull(user);
    }
}