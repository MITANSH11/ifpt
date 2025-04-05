package calc.calcappfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstFrameController implements Initializable {
    @FXML
    private AnchorPane root; // Ensure this matches the root element in FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String css = getClass().getResource("/CSSS/style.css").toExternalForm();
        root.getStylesheets().add(css);
    }
    @FXML
    private void loadNetWorth() {
        try {
            mainApp.changePage("networth.fxml", "Net Worth Calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadSIPCalculator() {
        try {
            mainApp.changePage("sip-calculator.fxml", "SIP Calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadTaxCalculator() {
        try {
            mainApp.changePage("tax-calculator.fxml", "Income Tax Calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadInsurance() {
        try {
            mainApp.changePage("lifeInsurance.fxml", "Life Insurance Calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openAssetAllocation() {
        try {
            mainApp.changePage("asset-allocation-view.fxml", "Asset Allocation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openEmiCalculator() {
        try {
            mainApp.changePage("emi-calculator.fxml", "EMI calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openLumpsumCalculator() {
        try {
            mainApp.changePage("lumpsum.fxml", "Lumpsum calculator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void logoutAction(){
        try {
            mainApp.changePage("login-form.fxml", "Login - IFPT");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
