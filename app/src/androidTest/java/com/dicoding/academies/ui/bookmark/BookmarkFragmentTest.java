package com.dicoding.academies.ui.bookmark;

import androidx.test.rule.ActivityTestRule;

import com.dicoding.academies.R;
import com.dicoding.academies.testing.SingleFragmentActivity;
import com.dicoding.academies.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class BookmarkFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private BookmarkFragment bookmarkFragment = new BookmarkFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(bookmarkFragment);
    }

    @Test
    public void loadBookmarks() {
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_bookmark)).check(new RecyclerViewItemCountAssertion(5));
    }
}
