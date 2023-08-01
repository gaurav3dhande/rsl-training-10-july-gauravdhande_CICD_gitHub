package com.example.numberadder;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private EditText firstNumberView;
    private EditText secondNumberView;
    private TextView resultView;
    private TextView titleTextView;
    private TextView additionSymbol;
    private Button additionButton;

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> {
            firstNumberView = activity.findViewById(R.id.first_number);
            secondNumberView = activity.findViewById(R.id.second_number);
            resultView = activity.findViewById(R.id.display_result_view);
            titleTextView = activity.findViewById(R.id.title_text_view);
            additionSymbol = activity.findViewById(R.id.addition_symbol);
            additionButton = activity.findViewById(R.id.perform_addition_button);
        });
    }

    @Test
    public void testViewsVisibility_init_isVisible() {
        // Assert : the visibility of title_text_view
        Espresso.onView(ViewMatchers.withId(titleTextView.getId()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Assert : the visibility of first_number
        Espresso.onView(ViewMatchers.withId(firstNumberView.getId()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Assert : the visibility of addition_symbol
        Espresso.onView(ViewMatchers.withId(additionSymbol.getId()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Assert : the visibility of second_number
        Espresso.onView(ViewMatchers.withId(secondNumberView.getId()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Assert : the visibility of perform_addition_button
        Espresso.onView(ViewMatchers.withId(additionButton.getId()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void inputAddition_validInputAndOddResult_resultAsExpected() {
        // Arrange : Input two valid numbers
        Espresso.onView(withId(firstNumberView.getId()))
                .perform(ViewActions.typeText("2"));
        Espresso.onView(withId(secondNumberView.getId()))
                .perform(ViewActions.typeText("3"));
        Espresso.onView(ViewMatchers.withId(titleTextView.getId()))
                .perform(ViewActions.closeSoftKeyboard());
        // Act : Click the addition button
        Espresso.onView(withId(additionButton.getId()))
                .perform(ViewActions.click());

        // Assert : Verify the result text
        Espresso.onView(withId(resultView.getId()))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .check(matches(withText("Sum is an odd number -> 5")));
    }

    @Test
    public void inputAddition_validInputAndEvenResult_resultAsExpected() {
        // Arrange : Input two valid numbers
        Espresso.onView(withId(firstNumberView.getId()))
                .perform(ViewActions.typeText("7"));
        Espresso.onView(withId(secondNumberView.getId()))
                .perform(ViewActions.typeText("1"));
        Espresso.onView(ViewMatchers.withId(titleTextView.getId()))
                .perform(ViewActions.closeSoftKeyboard());

        // Act : Click the addition button
        Espresso.onView(withId(additionButton.getId()))
                .perform(ViewActions.click());

        // Assert : Verify the result text
        Espresso.onView(withId(resultView.getId()))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .check(matches(withText("Sum is an even number -> 8")));
    }

    @Test
    public void verify_testAdditionWithEmptyFields_EmptyFieldErrorIsShown() {
        // Arrange : Input first number and leave second number empty
        Espresso.onView(withId(firstNumberView.getId()))
                .perform(ViewActions.typeText("2"));
        Espresso.onView(ViewMatchers.withId(titleTextView.getId()))
                .perform(ViewActions.closeSoftKeyboard());

        // Act : Click the addition button
        Espresso.onView(withId(additionButton.getId()))
                .perform(ViewActions.click());

        // Assert : Verify the error message for the second number field
        Espresso.onView(withId(secondNumberView.getId()))
                .check(matches(hasErrorText("This is a required field")));

        // Arrange : Clear the first number field and inputs second number and leave first number empty.
        Espresso.onView(withId(firstNumberView.getId()))
                .perform(ViewActions.clearText());
        Espresso.onView(withId(secondNumberView.getId()))
                .perform(ViewActions.typeText("3"));
        Espresso.onView(ViewMatchers.withId(titleTextView.getId()))
                .perform(ViewActions.closeSoftKeyboard());

        // Act : Click the addition button
        Espresso.onView(withId(additionButton.getId()))
                .perform(ViewActions.click());

        // Assert : Verify the error message for the first number field
        Espresso.onView(withId(firstNumberView.getId()))
                .check(matches(hasErrorText("This is a required field")));
    }
}

