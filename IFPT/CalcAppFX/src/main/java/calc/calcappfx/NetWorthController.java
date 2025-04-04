package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NetWorthController {

    @FXML private TextField bankField, fdField, mutualFundField, realEstateField, goldField;
    @FXML private TextField homeLoanField, carLoanField, personalLoanField, creditCardField, otherLiabilityField;

    @FXML private Label totalAssetsLabel, totalLiabilitiesLabel, netWorthLabel;
    @FXML private PieChart netWorthPieChart;
    @FXML private VBox resultBox;
    @FXML private AnchorPane root;
    @FXML
    public void initialize() {
        root.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }
    @FXML
    private void calculateNetWorth() {
        double bank = parse(bankField.getText());
        double fd = parse(fdField.getText());
        double mf = parse(mutualFundField.getText());
        double realEstate = parse(realEstateField.getText());
        double gold = parse(goldField.getText());

        double homeLoan = parse(homeLoanField.getText());
        double carLoan = parse(carLoanField.getText());
        double personalLoan = parse(personalLoanField.getText());
        double creditCard = parse(creditCardField.getText());
        double otherLiability = parse(otherLiabilityField.getText());

        double totalAssets = bank + fd + mf + realEstate + gold;
        double totalLiabilities = homeLoan + carLoan + personalLoan + creditCard + otherLiability;
        double netWorth = totalAssets - totalLiabilities;

        totalAssetsLabel.setText("Total Assets: ₹ " + String.format("%.2f", totalAssets));
        totalLiabilitiesLabel.setText("Total Liabilities: ₹ " + String.format("%.2f", totalLiabilities));
        netWorthLabel.setText("Net Worth: ₹ " + String.format("%.2f", netWorth));

        netWorthPieChart.getData().clear();
        netWorthPieChart.getData().add(new PieChart.Data("Assets", totalAssets));
        netWorthPieChart.getData().add(new PieChart.Data("Liabilities", totalLiabilities));

        resultBox.setVisible(true);
    }

    private double parse(String input) {
        try {
            return Double.parseDouble(input.trim());
        } catch (Exception e) {
            return 0;
        }
    }
    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }

}
