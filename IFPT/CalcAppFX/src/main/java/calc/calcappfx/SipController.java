package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SipController {

    @FXML private TextField monthlyInvestmentField;
    @FXML private TextField investmentPeriodField;
    @FXML private TextField expectedReturnRateField;
    @FXML private Label resultLabel;
    @FXML private Label investedAmountLabel;
    @FXML private PieChart pieChart;
    @FXML private AnchorPane sipRoot;

    @FXML
    public void initialize() {
        sipRoot.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculateSIP(ActionEvent event) {
        try {
            double monthlyInvestment = Double.parseDouble(monthlyInvestmentField.getText());
            int period = Integer.parseInt(investmentPeriodField.getText());
            double rate = Double.parseDouble(expectedReturnRateField.getText());

            int months = period * 12;
            double monthlyRate = rate / 12 / 100;

            double futureValue = monthlyInvestment * ((Math.pow(1 + monthlyRate, months) - 1) / monthlyRate) * (1 + monthlyRate);
            double investedAmount = monthlyInvestment * months;
            double returns = futureValue - investedAmount;

            resultLabel.setText("Future Value: ₹" + String.format("%.2f", futureValue));
            investedAmountLabel.setText("Total Invested: ₹" + String.format("%.2f", investedAmount));

            pieChart.getData().clear();
            pieChart.getData().add(new PieChart.Data("Invested Amount", investedAmount));
            pieChart.getData().add(new PieChart.Data("Estimated Returns", returns));
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers.");
            investedAmountLabel.setText("");
        }
    }


    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }
}
