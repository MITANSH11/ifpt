package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class AssetAllocationController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField monthlyIncomeField, investmentPercentageField, ageField;

    @FXML
    private Label equityLabel, debtLabel, goldLabel, emergencyLabel, totalInvestmentLabel;

    @FXML
    public void initialize() {
        root.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculateAllocation() {
        try {
            double monthlyIncome = Double.parseDouble(monthlyIncomeField.getText());
            double investmentPercentage = Double.parseDouble(investmentPercentageField.getText());
            int age = Integer.parseInt(ageField.getText());

            if (investmentPercentage < 0 || investmentPercentage > 100) {
                totalInvestmentLabel.setText("Invalid investment %!");
                clearLabels();
                return;
            }

            double totalInvestment = (investmentPercentage / 100.0) * monthlyIncome;
            double[] allocations = calculateAssetAllocation(age, totalInvestment);

            totalInvestmentLabel.setText(String.format("₹ %.2f", totalInvestment));
            equityLabel.setText(String.format("Equity: ₹ %.2f", allocations[0]));
            debtLabel.setText(String.format("Debt: ₹ %.2f", allocations[1]));
            goldLabel.setText(String.format("Gold/Real Estate: ₹ %.2f", allocations[2]));
            emergencyLabel.setText(String.format("Emergency Fund: ₹ %.2f", allocations[3]));

        } catch (NumberFormatException e) {
            totalInvestmentLabel.setText("Invalid input!");
            clearLabels();
        }
    }

    private double[] calculateAssetAllocation(int age, double totalInvestment) {
        double goldRealEstate = totalInvestment * 0.10;
        double emergencyFund = totalInvestment * 0.10;
        double remaining = totalInvestment - goldRealEstate - emergencyFund;
        double equity = remaining * ((100 - age) / 100.0);
        double debt = remaining * (age / 100.0);

        return new double[]{equity, debt, goldRealEstate, emergencyFund};
    }

    private void clearLabels() {
        equityLabel.setText("");
        debtLabel.setText("");
        goldLabel.setText("");
        emergencyLabel.setText("");
    }

    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }
}
