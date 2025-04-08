package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ValuationCalculatorController {

    @FXML private TextField presentAmountField;
    @FXML private TextField inflationRateField;
    @FXML private TextField yearsField;
    @FXML private Label resultLabel;
    @FXML private BarChart<String, Number> barChart;
    @FXML private AnchorPane valuationRoot;

    @FXML
    public void initialize() {
        valuationRoot.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
    }

    @FXML
    private void calculateValuation() {
        try {
            double presentAmount = Double.parseDouble(presentAmountField.getText());
            double inflationRate = Double.parseDouble(inflationRateField.getText());
            int years = Integer.parseInt(yearsField.getText());

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Yearly Value");

            double value = presentAmount;
            for (int i = 1; i <= years; i++) {
                value /= (1 + inflationRate / 100);
                series.getData().add(new XYChart.Data<>("Year " + i, value));
            }

            barChart.getData().clear();
            barChart.getData().add(series);

            resultLabel.setText("Value after " + years + " years: ₹" + String.format("%.2f", value));
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid inputs.");
        }
    }

    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }
}
