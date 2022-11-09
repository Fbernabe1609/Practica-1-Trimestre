package org.example.controllers;

import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.example.models.DBHelper;

import static com.mongodb.client.model.Filters.eq;

public class DBController implements FieldVariables {

    private static DBHelper db = new DBHelper();

    public static boolean searchUser(String username, String password) {
        boolean isnull;
        if (getDoc(username, password) == null) {
            isnull = false;
        } else {
            System.out.println("Cuenta encontrada");
            isnull = true;
        }
        return isnull;
    }

    public static Document exportUser(String email2) {
        return db.getCollection().find(eq(email, email2)).first();
    }

    public static Document getDoc(String username2, String password2) {

        Bson filter = Filters.and(Filters.eq(username, username2), Filters.eq(password, password2));
        return db.getCollection().find(filter).first();
    }

    public static boolean searchIfExist(String fieldName, String data) {
        boolean isnull;
        Document doc = db.getCollection().find(eq(fieldName, data)).first();
        if (doc == null) {
            isnull = true;
        } else {
            isnull = false;
        }
        return isnull;
    }

    public static int checkRegister(String username2, String email2) {
        boolean usernameExist = searchIfExist(username, username2);
        boolean emailExist = searchIfExist(email, email2);
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

    public static void addDataDocument(String name2, String surname2, String username2, String email2, String password2) {
        try {
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
        }
    }
}
