package calc.calcappfx;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        // Establish connection to MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017"); // Replace with your MongoDB URI
        database = mongoClient.getDatabase("ifpt_DB"); // Replace with your database name
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}


