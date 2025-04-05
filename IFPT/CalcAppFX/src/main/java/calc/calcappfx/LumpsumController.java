package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LumpsumController {

    @FXML private TextField investedAmountField;
    @FXML private TextField yearsField;
    @FXML private TextField returnRateField;
    @FXML private Label maturityValueLabel;
    @FXML private Label totalReturnLabel;
    @FXML private Label totalInvestedLabel;
    @FXML private PieChart pieChart;
    @FXML private AnchorPane lumpsumRoot;

    @FXML
    public void initialize() {
        lumpsumRoot.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculateLumpsum() {
        try {
            double principal = Double.parseDouble(investedAmountField.getText());
            double years = Double.parseDouble(yearsField.getText());
            double rate = Double.parseDouble(returnRateField.getText()) / 100;

            double maturityAmount = principal * Math.pow((1 + rate), years);
            double totalReturn = maturityAmount - principal;

            totalInvestedLabel.setText("Total Invested: ₹" + String.format("%.2f", principal));
            maturityValueLabel.setText("Maturity Value: ₹" + String.format("%.2f", maturityAmount));
            totalReturnLabel.setText("Total Returns: ₹" + String.format("%.2f", totalReturn));

            pieChart.getData().clear();
            pieChart.getData().add(new PieChart.Data("Invested", principal));
            pieChart.getData().add(new PieChart.Data("Returns", totalReturn));

        } catch (NumberFormatException e) {
            showAlert("Please enter valid numeric values.");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }
}
