package com.dicoding.academies.ui.academy;

import androidx.test.rule.ActivityTestRule;

import com.dicoding.academies.R;
import com.dicoding.academies.testing.SingleFragmentActivity;
import com.dicoding.academies.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AcademyFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private AcademyFragment academyFragment = new AcademyFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(academyFragment);
    }

    @Test
    public void loadCourses() {
        onView(withId(R.id.rv_academy)).check(new RecyclerViewItemCountAssertion(5));
    }
}
