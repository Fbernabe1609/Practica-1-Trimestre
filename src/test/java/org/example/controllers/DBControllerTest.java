package org.example.controllers;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.models.DBHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.mongodb.client.model.Filters.eq;

class DBControllerTest {

    private static DBHelper db = new DBHelper();

    @Test
    void exportUser() {
        String email = "email";
        String email2 = "fb@gamil.com";
        Assertions.assertTrue(db.getCollection().find(eq(email, email2)).first() != null);
    }

    @Test
    void addDataDocument() {
        String name = "nombre";
        String surname = "apellidos";
        String username = "nombre_de_usuario";
        String email = "email";
        String password = "contraseña";
        String name2 = "Pepe";
        String surname2 = "Pepon";
        String username2 = "Pep";
        String email2 = "Pep@gmail.com";
        String password2 = "123";
        long doc = 0;
        try {
            doc = db.getCollection().countDocuments();
            InsertOneResult result = db.getCollection().insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append(name, name2)
                    .append(surname, surname2)
                    .append(username, username2)
                    .append(email, email2)
                    .append(password, password2)
            );
            System.out.println("¡Éxito! Id documento: " + result.getInsertedId());
        } catch (MongoException e) {
            System.err.println("Unable to insert due to an error: " + e);
        } finally {
            Assertions.assertTrue(doc < db.getCollection().countDocuments());
        }
    }
}