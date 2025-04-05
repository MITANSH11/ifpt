package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TaxController {

    @FXML private AnchorPane taxRoot;
    @FXML private ChoiceBox<String> regimeChoice;
    @FXML private TextField incomeField;
    @FXML private TextField deduction80CField, deduction80DField, deduction80EField, deduction80GField, otherDeductionField;
    @FXML private TextArea resultArea;
    @FXML private PieChart deductionChart;

    @FXML
    public void initialize() {
        taxRoot.getStylesheets().add(getClass().getResource("/CSSS/sip.css").toExternalForm());
        regimeChoice.getItems().addAll("Old Regime", "New Regime AY 2025-26", "New Regime AY 2026-27");
        regimeChoice.setValue("Old Regime");
    }

    @FXML
    private void calculateTax() {
        try {
            double income = Double.parseDouble(incomeField.getText().trim());
            double d80C = parse(deduction80CField.getText());
            double d80D = parse(deduction80DField.getText());
            double d80E = parse(deduction80EField.getText());
            double d80G = parse(deduction80GField.getText());
            double other = parse(otherDeductionField.getText());

            double totalDeduction = d80C + d80D + d80E + d80G + other;
            double taxableIncome = Math.max(0, income - totalDeduction - 50000); // 50k standard deduction

            double tax = calculateBasedOnRegime(taxableIncome);
            double rebate = 0;
            if (taxableIncome <= 700000) rebate = Math.min(tax, 25000);

            tax -= rebate;
            double cess = tax * 0.04;
            double totalTax = tax + cess;

            resultArea.setText("Gross Income: ₹ " + income +
                    "\nStandard Deduction: ₹ 50000" +
                    "\nTotal Other Deductions: ₹ " + totalDeduction +
                    "\nTaxable Income: ₹ " + taxableIncome +
                    "\nCalculated Tax: ₹ " + String.format("%.2f", tax) +
                    "\nRebate: ₹ " + String.format("%.2f", rebate) +
                    "\nHealth & Education Cess (4%): ₹ " + String.format("%.2f", cess) +
                    "\n\nTotal Tax Payable: ₹ " + String.format("%.2f", totalTax));

            deductionChart.getData().clear();
            deductionChart.getData().add(new PieChart.Data("Deductions", totalDeduction));
            deductionChart.getData().add(new PieChart.Data("Rebate", rebate));

        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid values.");
        }
    }

    private double calculateBasedOnRegime(double income) {
        String regime = regimeChoice.getValue();
        double tax = 0;

        if (regime.equals("Old Regime")) {
            if (income <= 250000) return 0;
            else if (income <= 500000) tax = (income - 250000) * 0.05;
            else if (income <= 1000000) tax = 12500 + (income - 500000) * 0.2;
            else tax = 112500 + (income - 1000000) * 0.3;
        } else {
            if (income <= 300000) return 0;
            else if (income <= 600000) tax = (income - 300000) * 0.05;
            else if (income <= 900000) tax = 15000 + (income - 600000) * 0.1;
            else if (income <= 1200000) tax = 45000 + (income - 900000) * 0.15;
            else tax = 90000 + (income - 1200000) * 0.2;
        }

        return tax;
    }

    private double parse(String text) {
        try {
            return Double.parseDouble(text.trim());
        } catch (Exception e) {
            return 0;
        }
    }
    @FXML
    private void backToMain() throws IOException {
        mainApp.changePage("first-frame.fxml", "Calculators");
    }
}
