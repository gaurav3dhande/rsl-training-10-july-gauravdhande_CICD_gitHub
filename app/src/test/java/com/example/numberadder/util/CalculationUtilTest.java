package com.example.numberadder.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalculationUtilTest {

    @Mock
    private EvenOddUtil evenOddUtil;
    private CalculationUtil calculationUtil;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        calculationUtil = new CalculationUtil(evenOddUtil);
    }

    @Test
    public void testGenerateResultEven() {
        // Mock the behavior of isEven() method to return true
        doReturn(true).when(evenOddUtil).isEven(6);

        // Call the method under test
        String result = calculationUtil.generateResult(2, 4);

        // Verify the result
        assertEquals("Sum is an even number -> 6", result);
    }

    @Test
    public void testGenerateResultOdd() {
        // Mock the behavior of isEven() method to return false
        doReturn(false).when(evenOddUtil).isEven(7);

        // Call the method under test
        String result = calculationUtil.generateResult(3, 4);

        // Verify the result
        assertEquals("Sum is an odd number -> 7", result);
    }
}