package com.example.numberadder.util;

import androidx.annotation.NonNull;

public class CalculationUtil {

    private final EvenOddUtil evenOddUtil;

    public CalculationUtil(@NonNull EvenOddUtil evenOddUtil) {
        this.evenOddUtil = evenOddUtil;
    }

    public String generateResult(int number1, int number2) {
        int sum = number1 + number2;

        if (evenOddUtil.isEven(sum)) {
            return "Sum is an even number -> " + sum;
        } else {
            return "Sum is an odd number -> " + sum;
        }
    }
}
