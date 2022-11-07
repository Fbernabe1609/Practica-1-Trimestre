package org.example.controllers;

import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.example.models.DbHelper;
import org.example.models.User;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class DbController {

    private String nameFieldName = "nombre";
    private String surnameFieldName = "apellidos";
    private String usernameFieldName = "nombre_de_usuario";
    private String emailFieldName = "email";
    private String passwordFieldName = "contraseña";

    private DbHelper db = new DbHelper();

    public User viewDocument(String username, String password) {

        Document doc = Objects.requireNonNull(getDoc(username, password));

        return new User(doc.get(nameFieldName).toString(),
                doc.get(surnameFieldName).toString(),
                doc.get(usernameFieldName).toString(),
                doc.get(emailFieldName).toString(),
                doc.get(passwordFieldName).toString());
    }

    public boolean searchUser(String username, String password) {
        boolean isnull;
        if (getDoc(username, password) == null) {
            isnull = false;
        } else {
            System.out.println("Cuenta encontrada");
            isnull = true;
        }
        return isnull;
    }

    public Document getDoc(String username, String password) {

        Bson filter = Filters.and(Filters.eq("nombre_de_usuario", username), Filters.eq("contraseña", password));
        return db.getCollection().find(filter).first();
    }

    public boolean searchIfExist(String fieldName, String data) {
        boolean isnull;
        Document doc = db.getCollection().find(eq(fieldName, data)).first();
        if (doc == null) {
            isnull = true;
        } else {
            isnull = false;
        }
        return isnull;
    }

    public int checkRegister(String username, String email) {
        boolean usernameExist = searchIfExist(usernameFieldName, username);
        boolean emailExist = searchIfExist(emailFieldName, email);
        int num;
        if (emailExist && usernameExist) {
            num = 0;
        } else if (emailExist && !usernameExist) {
            num = 1;
        } else if (!emailExist && usernameExist) {
            num = 2;
        } else {
            num = 3;
        }
        return num;
    }

    public void addDataDocument(String name, String surname, String username, String email, String password) {
        try {
            InsertOneResult result = db.getCollection().insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append(nameFieldName, name)
                    .append(surnameFieldName, surname)
                    .append(usernameFieldName, username)
                    .append(emailFieldName, email)
                    .append(passwordFieldName, password)
            );
            System.out.println("¡Éxito! Id documento: " + result.getInsertedId());
        } catch (MongoException e) {
            System.err.println("Unable to insert due to an error: " + e);
        }
    }
}
