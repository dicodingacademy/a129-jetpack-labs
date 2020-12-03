package com.dicoding.academies.ui.home;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.dicoding.academies.R;
import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.utils.DataDummy;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest {

    private ArrayList<CourseEntity> dummyCourse = DataDummy.generateDummyCourses();

    @Rule
    public ActivityScenarioRule activityRule = new ActivityScenarioRule<>(HomeActivity.class);

    @Test
    public void loadCourses() {

        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.scrollToPosition(dummyCourse.size()));
    }

    @Test
    public void loadDetailCourse() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse.get(0).getTitle())));
        onView(withId(R.id.text_date)).check(matches(isDisplayed()));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Deadline %s", dummyCourse.get(0).getDeadline()))));
    }

    @Test
    public void loadModule() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.btn_start)).perform(click());
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
    }

    @Test
    public void loadDetailModule() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.btn_start)).perform(click());
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.web_view)).check(matches(isDisplayed()));
    }

    @Test
    public void loadBookmarks() {
        onView(withText("Bookmark")).perform(click());
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.scrollToPosition(dummyCourse.size()));
    }
}