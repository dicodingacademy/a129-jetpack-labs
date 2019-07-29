package com.dicoding.picodiploma.myunittest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private String dummyVolume = "504.0";
    private String dummyCircumference = "2016.0";
    private String dummySurfaceArea = "396.0";
    private String dummyLength = "12.0";
    private String dummyWidth = "7.0";
    private String dummyHeight = "6.0";
    private String emptyInput = "";
    private String fieldEmpty = "Field ini tidak boleh kosong";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void getCircumference() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard());
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard());
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.btn_calculate_circumference)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_calculate_circumference)).perform(click());

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_result)).check(matches(withText(dummyCircumference)));
    }

    @Test
    public void getSurfaceArea() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard());
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard());
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.btn_calculate_surface_area)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_calculate_surface_area)).perform(click());

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_result)).check(matches(withText(dummySurfaceArea)));
    }

    @Test
    public void getVolume() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard());
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard());
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.btn_calculate_volume)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_calculate_volume)).perform(click());

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_result)).check(matches(withText(dummyVolume)));
    }

    //Pengecekan untuk empty input
    @Test
    public void emptyInput() {
        // pengecekan input untuk length
        onView(withId(R.id.edt_length)).perform(typeText(emptyInput), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.edt_length)).check(matches(hasErrorText(fieldEmpty)));
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard());

        // pengecekan input untuk width
        onView(withId(R.id.edt_width)).perform(typeText(emptyInput), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.edt_width)).check(matches(hasErrorText(fieldEmpty)));
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard());

        // pengecekan input untuk height
        onView(withId(R.id.edt_height)).perform(typeText(emptyInput), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).perform(click());

        onView(withId(R.id.edt_height)).check(matches(hasErrorText(fieldEmpty)));
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard());

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_save)).perform(click());
    }
}