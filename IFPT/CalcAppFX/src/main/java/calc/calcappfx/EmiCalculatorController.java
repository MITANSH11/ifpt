package calc.calcappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class EmiCalculatorController {

    @FXML private AnchorPane emiRoot;
    @FXML private TextField principalField;
    @FXML private TextField rateField;
    @FXML private TextField tenureField;
    @FXML private Label emiLabel;
    @FXML private Label totalPaymentLabel;
    @FXML private Label interestLabel;
    @FXML private PieChart emiPieChart;

    @FXML
    public void initialize() {
        emiRoot.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculateEMI(ActionEvent event) {
        try {
            double principal = Double.parseDouble(principalField.getText());
            double annualRate = Double.parseDouble(rateField.getText());
            int months = Integer.parseInt(tenureField.getText());

            double monthlyRate = annualRate / 12 / 100;
            double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                    (Math.pow(1 + monthlyRate, months) - 1);

            double totalPayment = emi * months;
            double totalInterest = totalPayment - principal;

            DecimalFormat df = new DecimalFormat("##,##,###.##");

            emiLabel.setText("Monthly EMI: ₹ " + df.format(emi));
            totalPaymentLabel.setText("Total Payment: ₹ " + df.format(totalPayment));
            interestLabel.setText("Total Interest: ₹ " + df.format(totalInterest));

            emiPieChart.getData().clear();
            emiPieChart.getData().add(new PieChart.Data("Principal", principal));
            emiPieChart.getData().add(new PieChart.Data("Interest", totalInterest));

        } catch (Exception e) {
            emiLabel.setText("Invalid input!");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("first-frame.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) emiRoot.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
