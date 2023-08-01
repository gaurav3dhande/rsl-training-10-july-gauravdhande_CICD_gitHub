package com.example.numberadder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.numberadder.util.CalculationUtil;
import com.example.numberadder.util.EvenOddUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstNumberView = findViewById(R.id.first_number);
        EditText secondNumberView = findViewById(R.id.second_number);
        TextView resultView = findViewById(R.id.display_result_view);
        Button performAddButton = findViewById(R.id.perform_addition_button);

        performAddButton.setOnClickListener(v -> {
            if (firstNumberView.getText().toString().length() == 0) {
                firstNumberView.setError("This is a required field");
                return;
            }

            if (secondNumberView.getText().toString().length() == 0) {
                secondNumberView.setError("This is a required field");
                return;
            }

            int num1 = Integer.parseInt(firstNumberView.getText().toString());
            int num2 = Integer.parseInt(secondNumberView.getText().toString());

            CalculationUtil calcUtil = new CalculationUtil(new EvenOddUtil());
            resultView.setText(calcUtil.generateResult(num1, num2));
            resultView.setVisibility(View.VISIBLE);
        });
    }
}