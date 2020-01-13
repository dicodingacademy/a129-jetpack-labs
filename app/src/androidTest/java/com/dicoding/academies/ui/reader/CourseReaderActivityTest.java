package com.dicoding.academies.ui.reader;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.utils.EspressoIdlingResource;
import com.dicoding.academies.utils.FakeDataDummy;
import com.dicoding.academies.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CourseReaderActivityTest {
    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses().get(0);

    @Rule
    public ActivityTestRule<CourseReaderActivity> activityRule = new ActivityTestRule<CourseReaderActivity>(CourseReaderActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, CourseReaderActivity.class);
            result.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, dummyCourse.getCourseId());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadModules() {
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).check(new RecyclerViewItemCountAssertion(7));
    }

    @Test
    public void clickModule() {
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.web_view)).check(matches(isDisplayed()));
    }
}