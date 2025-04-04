package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class LifeInsuranceController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField ageField, coverageField, termField;

    @FXML
    private Label premiumResultLabel;

    @FXML
    public void initialize() {
        root.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculatePremium() {
        try {
            int age = Integer.parseInt(ageField.getText());
            double coverageAmount = Double.parseDouble(coverageField.getText());
            int term = Integer.parseInt(termField.getText());

            double premium = calculatePremium(age, coverageAmount, term);

            premiumResultLabel.setText(String.format("₹ %.2f", premium));
        } catch (NumberFormatException e) {
            premiumResultLabel.setText("Invalid input!");
        }
    }

    @FXML
    private void backtomainpage() throws IOException {
        mainApp.changePage("first-frame.fxml", "Home Page");
    }

    private double calculatePremium(int age, double coverageAmount, int term) {
        double baseRate = 0.05;
        if (age > 50) baseRate += 0.02;
        return (coverageAmount * baseRate) / (term * 12);
    }
}
