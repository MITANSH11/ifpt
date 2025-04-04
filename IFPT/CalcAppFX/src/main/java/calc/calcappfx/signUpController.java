package calc.calcappfx;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class signUpController implements Initializable {

    @FXML
    private TextField usernameField, passwordField;

    @FXML
    private Button signUpButton, loginButton;

    private MongoCollection<Document> userCollection;

    // Constructor
    public signUpController() {
        // Get the MongoDB user collection
        userCollection = MongoDBUtil.getDatabase().getCollection("users");
    }

    // Action to go back to the login page
    public void backToLogin() {
        try {
            mainApp.changePage("login-form.fxml", "Login page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Action to handle user sign-up
    public void signUpAction() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty!", AlertType.ERROR);
            return;
        }

        // Check if username already exists in MongoDB
        long count = userCollection.countDocuments(Filters.eq("username", username));
        if (count > 0) {
            showAlert("Error", "Username already exists. Please choose another one.", AlertType.ERROR);
            return;
        }

        // Hash the password before storing it
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Insert the new user into the MongoDB users collection
        Document newUser = new Document("username", username)
                .append("password", hashedPassword);

        userCollection.insertOne(newUser);

        // Show success message
        showAlert("Success", "User successfully registered!", AlertType.INFORMATION);

        // Clear the input fields
        usernameField.clear();
        passwordField.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // This method can be used to initialize things if needed when the FXML file is loaded
    }

    // Helper method to show an alert message
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
