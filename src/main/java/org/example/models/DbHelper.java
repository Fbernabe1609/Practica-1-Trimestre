package org.example.models;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.Objects;

public class DbHelper {

    String dbUsername = "Fbernabe";
    String dbPassword = "pPZXijuT4UaLaL80";
    String databaseName = "Clase";
    String collectionName = "people";
    ConnectionString connectionString = new ConnectionString("mongodb+srv://" + dbUsername + ":" + dbPassword + "@cluster0.dmu6ezu.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase(databaseName);
    MongoCollection<Document> collection = database.getCollection(collectionName);

//    public void viewDocument() {
//        Document doc = collection.find(eq("title", "Back to the Future")).first();
//        //Lo muestra en formato json
//        System.out.println(Objects.requireNonNull(doc).toJson());
//        //extrae algo del json, no hace falta poner .toString()
//        System.out.println(Objects.requireNonNull(doc).get("plot"));
//    }

    public User viewDocument(String username, String password) {
        Bson filter = Filters.and(Filters.gt("nombre_de_usuario", username), Filters.lt("contraseña", password));
        Document doc = collection.find(filter).first();
        System.out.println("Cuenta encontrada");
//        Objects.requireNonNull(doc).toJson()
        return new User(Objects.requireNonNull(doc).get("nombre").toString(),
                Objects.requireNonNull(doc).get("apellidos").toString(),
                Objects.requireNonNull(doc).get("nombre_de_usuario").toString(),
                Objects.requireNonNull(doc).get("email").toString(),
                Objects.requireNonNull(doc).get("contraseña").toString());
    }

//    public void addDataDocument() {
//        try {
//            InsertOneResult result = collection.insertOne(new Document()
//                    .append("_id", new ObjectId())
//                    .append("title", "Ski Bloopers")
//                    .append("genres", Arrays.asList("Documentary", "Comedy")));
//            System.out.println("Success! Inserted document id: " + result.getInsertedId());
//        } catch (MongoException e) {
//            System.err.println("Unable to insert due to an error: " + e);
//        }
//    }

    public void addDataDocument(String name, String surname, String username, String email, String password) {
        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("nombre", name)
                    .append("apellidos", surname)
                    .append("nombre_de_usuario", username)
                    .append("email", email)
                    .append("contraseña", password)
            );
            System.out.println("¡Éxito! Id documento: " + result.getInsertedId());
        } catch (MongoException e) {
            System.err.println("No se ha podido guardar los datos, error: " + e);
        }
    }
}
