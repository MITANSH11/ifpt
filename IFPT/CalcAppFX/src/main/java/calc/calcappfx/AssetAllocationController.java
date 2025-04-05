package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AssetAllocationController {

    @FXML private AnchorPane assetRoot;
    @FXML private TextField incomeField;
    @FXML private TextField percentToInvestField;
    @FXML private TextField ageField;
    @FXML private TextArea resultArea;
    @FXML private PieChart pieChart;

    @FXML
    public void initialize() {
        assetRoot.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculateAllocation() {
        try {
            double income = Double.parseDouble(incomeField.getText());
            double investPercent = Double.parseDouble(percentToInvestField.getText());
            int age = Integer.parseInt(ageField.getText());

            double totalInvest = (income * investPercent) / 100;

            double stocks = 0, bonds = 0, mutualFunds = 0, realEstate = 0, gold = 0, cash = 0;

            if (age < 30) {
                stocks = totalInvest * 0.40;
                mutualFunds = totalInvest * 0.30;
                realEstate = totalInvest * 0.15;
                gold = totalInvest * 0.05;
                bonds = totalInvest * 0.05;
                cash = totalInvest * 0.05;
            } else if (age <= 45) {
                stocks = totalInvest * 0.30;
                mutualFunds = totalInvest * 0.30;
                realEstate = totalInvest * 0.20;
                gold = totalInvest * 0.10;
                bonds = totalInvest * 0.05;
                cash = totalInvest * 0.05;
            } else {
                stocks = totalInvest * 0.20;
                mutualFunds = totalInvest * 0.25;
                realEstate = totalInvest * 0.20;
                gold = totalInvest * 0.15;
                bonds = totalInvest * 0.10;
                cash = totalInvest * 0.10;
            }

            resultArea.setText(
                    "Total Investment Amount: ₹ " + String.format("%.2f", totalInvest) + "\n\n" +
                            "Recommended Allocation:\n" +
                            "Stocks: ₹ " + String.format("%.2f", stocks) + "\n" +
                            "Mutual Funds: ₹ " + String.format("%.2f", mutualFunds) + "\n" +
                            "Real Estate: ₹ " + String.format("%.2f", realEstate) + "\n" +
                            "Gold: ₹ " + String.format("%.2f", gold) + "\n" +
                            "Bonds: ₹ " + String.format("%.2f", bonds) + "\n" +
                            "Cash: ₹ " + String.format("%.2f", cash)
            );

            pieChart.getData().clear();
            pieChart.getData().add(new PieChart.Data("Stocks", stocks));
            pieChart.getData().add(new PieChart.Data("Mutual Funds", mutualFunds));
            pieChart.getData().add(new PieChart.Data("Real Estate", realEstate));
            pieChart.getData().add(new PieChart.Data("Gold", gold));
            pieChart.getData().add(new PieChart.Data("Bonds", bonds));
            pieChart.getData().add(new PieChart.Data("Cash", cash));

        } catch (Exception e) {
            resultArea.setText("Please enter valid inputs.");
        }
    }

    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }
}
