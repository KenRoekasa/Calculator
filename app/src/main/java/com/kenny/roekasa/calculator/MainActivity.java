package com.kenny.roekasa.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView numberText;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button divisionButton;
    private Button mutltiplyButton;
    private Button additionButton;
    private Button subtractButton;
    private Button clearButton;
    private boolean operatorCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numberText = findViewById(R.id.numberView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);


        Button numberButtons[] = {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9};

        divisionButton = findViewById(R.id.division_button);
        mutltiplyButton = findViewById(R.id.multiply_button);
        additionButton = findViewById(R.id.add_button);
        subtractButton = findViewById(R.id.minus_button);
        clearButton = findViewById(R.id.clear_button);


        if (savedInstanceState == null) {
            //it is the first time the fragment is being called
            numberText.setText("");
        } else {
            //not the first time so we will check SavedInstanceState bundle
            numberText.setText(savedInstanceState.getString("value", ""));
        }


        for (int i = 0; i < numberButtons.length; i++) {
            final int finalI = i;
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    write(finalI);
                    operatorCheck = false;
                }
            });
        }


    }

    private void write(int i) {
        numberText.setText(numberText.getText() + String.valueOf(i));
    }


    public void clear(View view) {
        numberText.setText("");
    }

    public void delete(View view) {
        if (numberText.length() > 0) {
            numberText.setText(numberText.getText().toString().substring(0, numberText.getText().length() - 1));
        }
    }


    public void add(View view) {


        if(operatorCheck){
            numberText.setText(numberText.getText().subSequence(0,numberText.getText().length()-3) + " + ");
        } else {
            numberText.setText(numberText.getText() + " + ");
            operatorCheck = true;
        }
    }

    public void subtract(View view) {
        if(operatorCheck){
            numberText.setText(numberText.getText().subSequence(0,numberText.getText().length()-3) + " - ");
        } else {
            numberText.setText(numberText.getText() + " - ");
            operatorCheck = true;
        }

    }

    public void divide(View view) {
        if(operatorCheck){
            numberText.setText(numberText.getText().subSequence(0,numberText.getText().length()-3) + " ÷ ");
        } else {
            numberText.setText(numberText.getText() + " ÷ ");
            operatorCheck = true;
        }

    }

    public void multiply(View view) {
        if(operatorCheck){
            numberText.setText(numberText.getText().subSequence(0,numberText.getText().length()-3) +" × ");
        } else {
            numberText.setText(numberText.getText() + " × ");
            operatorCheck = true;
        }

    }

    public void equal(View view) {
        int result = Calculator.calculate(numberText.getText().toString());
        Log.d("result", String.valueOf(result));
        numberText.setText(String.valueOf(result));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", numberText.getText().toString());
    }
}
