package com.dicoding.picodiploma.myidleresource;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourcey());
    }

    @Test
    public void checkText() {
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.getActivity().getString(R.string.prepare))));

        onView(withText(mActivityRule.getActivity().getString(R.string.start))).perform(click());

        //onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.getActivity().getString(R.string.delay1))));

        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.getActivity().getString(R.string.delay2))));
    }
}