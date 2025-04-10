package calc.calcappfx;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        // Replace <password> with your actual MongoDB Atlas password
        String uri = "mongodb+srv://ifptuser:mitansh12980@cluster0.cqa8qoq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("ifpt_DB"); // Your database name
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
