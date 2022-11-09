package org.example.models;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBHelper {
    private String dbUsername = "Fbernabe";
    private String dbPassword = "pPZXijuT4UaLaL80";
    private String databaseName = "Clase";
    private String collectionName = "people";
    private ConnectionString connectionString = new ConnectionString("mongodb+srv://" + dbUsername + ":" + dbPassword + "@cluster0.dmu6ezu.mongodb.net/?retryWrites=true&w=majority");
    private MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    private MongoClient mongoClient = MongoClients.create(settings);
    private MongoDatabase database = mongoClient.getDatabase(databaseName);
    private MongoCollection<Document> collection = database.getCollection(collectionName);

    public MongoCollection<Document> getCollection() {
        return collection;
    }
}
