package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class InflationCalculatorController {

    @FXML private TextField currentAmountField;
    @FXML private TextField yearsField;
    @FXML private TextField inflationRateField;
    @FXML private Label resultLabel;
    @FXML private PieChart inflationChart;
    @FXML private AnchorPane inflationRoot;

    @FXML
    public void initialize() {
        inflationRoot.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculateInflation() {
        try {
            double currentAmount = Double.parseDouble(currentAmountField.getText());
            int years = Integer.parseInt(yearsField.getText());
            double inflationRate = Double.parseDouble(inflationRateField.getText()) / 100;

            double futureCost = currentAmount * Math.pow(1 + inflationRate, years);
            double inflationImpact = futureCost - currentAmount;

            resultLabel.setText("Future expenses: ₹" + String.format("%.2f", futureCost));

            inflationChart.getData().clear();
            inflationChart.getData().add(new PieChart.Data("Current expenses", currentAmount));
            inflationChart.getData().add(new PieChart.Data("Inflation Impact", inflationImpact));
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    @FXML
    private void backToMain() {
        try {
            mainApp.changePage("first-frame.fxml", "Calculators");
        } catch (Exception e) {
            resultLabel.setText("Unable to go back.");
        }
    }
}
