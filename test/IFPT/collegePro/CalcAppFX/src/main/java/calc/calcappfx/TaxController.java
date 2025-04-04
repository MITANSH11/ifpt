package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class TaxController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField incomeField, deductionsField;
    @FXML
    private Label taxLabel;
    @FXML
    private ChoiceBox<String> regimeChoice;
    @FXML
    private Button calculateButton, backButton;

    @FXML
    public void initialize() {
        root.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
        regimeChoice.getItems().addAll("Old Regime", "New Regime AY 2025-26", "New Regime AY 2026-27");
        regimeChoice.setValue("Old Regime");
    }

    @FXML
    private void calculateTax() {
        try {
            double income = Double.parseDouble(incomeField.getText());
            double deductions = Double.parseDouble(deductionsField.getText());
            double taxableIncome = income - deductions;
            String regime = regimeChoice.getValue();

            double tax = 0;

            if (regime.equals("Old Regime")) {
                if (taxableIncome <= 250000) tax = 0;
                else if (taxableIncome <= 500000) tax = (taxableIncome - 250000) * 0.05;
                else if (taxableIncome <= 1000000) tax = 12500 + (taxableIncome - 500000) * 0.2;
                else tax = 112500 + (taxableIncome - 1000000) * 0.3;
            } else if (regime.equals("New Regime AY 2025-26")) {
                if (taxableIncome <= 300000) tax = 0;
                else if (taxableIncome <= 600000) tax = (taxableIncome - 300000) * 0.05;
                else if (taxableIncome <= 900000) tax = 15000 + (taxableIncome - 600000) * 0.1;
                else if (taxableIncome <= 1200000) tax = 45000 + (taxableIncome - 900000) * 0.15;
                else if (taxableIncome <= 1500000) tax = 90000 + (taxableIncome - 1200000) * 0.2;
                else tax = 150000 + (taxableIncome - 1500000) * 0.3;
            } else { // AY 2026-27
                if (taxableIncome <= 250000) tax = 0;
                else if (taxableIncome <= 500000) tax = (taxableIncome - 250000) * 0.05;
                else if (taxableIncome <= 750000) tax = 12500 + (taxableIncome - 500000) * 0.1;
                else if (taxableIncome <= 1000000) tax = 37500 + (taxableIncome - 750000) * 0.15;
                else if (taxableIncome <= 1250000) tax = 75000 + (taxableIncome - 1000000) * 0.2;
                else if (taxableIncome <= 1500000) tax = 125000 + (taxableIncome - 1250000) * 0.25;
                else tax = 187500 + (taxableIncome - 1500000) * 0.3;
            }

            taxLabel.setText(String.format("₹ %.2f", Math.max(tax, 0)));

        } catch (NumberFormatException e) {
            taxLabel.setText("Invalid input!");
        }
    }

    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }
}
