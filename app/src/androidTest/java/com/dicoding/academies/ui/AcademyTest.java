package com.dicoding.academies.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.dicoding.academies.R;
import com.dicoding.academies.ui.home.HomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AcademyTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void toDetailActivityTest() {
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText("Menjadi Android Developer Expert")));
    }

    @Test
    public void toReaderActivityTest() {
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.btn_start)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_start)).perform(click());

        onView(withId(R.id.frame_container)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.web_view)).check(matches(isDisplayed()));
    }
}

