package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalcController implements Initializable {
    @FXML
    private TextField equationDisplay;
    @FXML
    private TextField resultDisplay;
    private int decimalClick = 0;
    private String operator;
    private String controller;
    private double firstNumber;

    @FXML
    public void handleController(ActionEvent actionEvent) {
        controller = ((Button) actionEvent.getSource()).getText();

        switch (controller) {
            case "AC":
                equationDisplay.setText("");
                resultDisplay.setText("");
                decimalClick = 0;
                break;
            case "Clear":
                String oldText = resultDisplay.getText();
                String newText = oldText.substring(0, oldText.length() - 1);
                resultDisplay.setText(newText);
                break;
        }
    }

    @FXML
    public void handleNumbers(ActionEvent actionEvent) {
        String num = ((Button) actionEvent.getSource()).getText();
        String oldText = resultDisplay.getText();
        String newText = oldText + num;
        resultDisplay.setText(newText);
    }

    @FXML
    public void handleDecimal(ActionEvent actionEvent) {
        if (decimalClick == 0) {
            String decimalObject = ((Button) actionEvent.getSource()).getText();
            String oldText = resultDisplay.getText();
            String newText = oldText + decimalObject;
            resultDisplay.setText(newText);
            decimalClick = 1;
        }
    }

    @FXML
    public void handleOperators(ActionEvent actionEvent) {
        operator = ((Button) actionEvent.getSource()).getText();
        String currentText = resultDisplay.getText();

        switch (operator) {
            case "+/-":
                double plusMinus = Double.parseDouble(currentText);
                plusMinus = plusMinus * (-1);
                resultDisplay.setText(String.valueOf(plusMinus));
                break;
            case "+":
            case "-":
            case "X":
            case "/":
            case "%":
                firstNumber = Double.parseDouble(currentText);
                equationDisplay.setText(currentText + " " + operator);
                resultDisplay.setText("");
                decimalClick = 0;
                break;
            default:
        }
    }

    @FXML
    public void handleAnswers(ActionEvent actionEvent) {
        double secondNumber;
        double result = 0;
        String secText = resultDisplay.getText();
        secondNumber = Double.parseDouble(secText);

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "X":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
            case "%":
                result = firstNumber % secondNumber;
                break;
            default:
        }

        equationDisplay.setText(firstNumber + " " + operator + " " + secondNumber);
        resultDisplay.setText(String.valueOf(result));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}