package com.dicoding.academies.ui.bookmark;

import androidx.test.filters.LargeTest;
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
import static org.junit.Assert.*;

@LargeTest
public class BookmarkFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private BookmarkFragment bookmarkFragment = new BookmarkFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(bookmarkFragment);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadBookmarks() {
        onView(withId(R.id.rv_bookmark)).check(new RecyclerViewItemCountAssertion(5));
    }
}