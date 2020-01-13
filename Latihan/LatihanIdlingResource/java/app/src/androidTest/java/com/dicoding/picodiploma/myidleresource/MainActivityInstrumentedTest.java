package com.dicoding.picodiploma.myidleresource;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void checkText() {
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.getActivity().getString(R.string.prepare))));

        onView(withText(mActivityRule.getActivity().getString(R.string.start))).perform(click());

        //onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.getActivity().getString(R.string.delay1))));

        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.getActivity().getString(R.string.delay2))));
    }
}