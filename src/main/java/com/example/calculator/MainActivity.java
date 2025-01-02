package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder input = new StringBuilder();
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        setupButtons();
    }

    private void setupButtons() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDecimal,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonEquals, R.id.buttonClear
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                clear();
                break;
            case "=":
                calculate();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(buttonText);
                break;
            default:
                appendInput(buttonText);
                break;
        }
    }

    private void clear() {
        input.setLength(0);
        operator = "";
        firstOperand = 0;
        resultTextView.setText("0");
    }

    private void appendInput(String value) {
        input.append(value);
        resultTextView.setText(input.toString());
    }

    private void setOperator(String op) {
        if (input.length() > 0) {
            firstOperand = Double.parseDouble(input.toString());
            operator = op;
            input.setLength(0);
        }
    }

    private void calculate() {
        if (input.length() > 0 && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(input.toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }

            input.setLength(0);
            input.append(result);
            resultTextView.setText(result + "");
            operator = "";
        }
    }
}