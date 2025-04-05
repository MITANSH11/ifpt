package calc.calcappfx;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private TextField usernameField, passwordField;

    @FXML
    private Button loginButton, signUpButton;

    @FXML
    private Label signUpLabel, emailLabel, loginMessageLabel;

    private MongoCollection<Document> userCollection;

    public loginController() {
        // Get the MongoDB user collection
        userCollection = MongoDBUtil.getDatabase().getCollection("users");
    }

    // Action to handle user login
    public void loginButtonOnAction(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Validate input fields
        if (username.isBlank() || password.isBlank()) {
            loginMessageLabel.setText("Please enter username and password");
            return;
        }

        // Check if the user exists in MongoDB
        Document userDoc = userCollection.find(Filters.eq("username", username)).first();
        if (userDoc != null) {
            String storedHashedPassword = userDoc.getString("password");

            if (BCrypt.checkpw(password, storedHashedPassword)) {
                loginMessageLabel.setText("Login successful!");
                try {
                    mainApp.changePage("first-frame.fxml", "IFPT");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else {
                loginMessageLabel.setText("Incorrect username or password");
            }
        } else {
            loginMessageLabel.setText("Incorrect username or password");
        }
    }

    public void loadSignUp() {
        try {
            mainApp.changePage("sign-up.fxml", "Sign-Up page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
