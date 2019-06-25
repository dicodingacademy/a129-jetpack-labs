package com.dicoding.academies.ui.bookmark;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.testing.SingleFragmentActivity;
import com.dicoding.academies.utils.EspressoIdlingResource;
import com.dicoding.academies.utils.PagedListUtil;
import com.dicoding.academies.utils.RecyclerViewItemCountAssertion;
import com.dicoding.academies.utils.ViewModelUtil;
import com.dicoding.academies.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.dicoding.academies.utils.FakeDataDummy.generateDummyCourses;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class BookmarkFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);

    private MutableLiveData<Resource<PagedList<CourseEntity>>> bookmarks = new MutableLiveData<>();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());

        BookmarkViewModel bookmarkViewModel = mock(BookmarkViewModel.class);
        when(bookmarkViewModel.getBookmarksPaged()).thenReturn(bookmarks);
        doNothing().when(bookmarkViewModel).setBookmark(any());

        BookmarkFragment bookmarkFragment = new BookmarkFragment();
        bookmarkFragment.factory = ViewModelUtil.createFor(bookmarkViewModel);

        activityRule.getActivity().setFragment(bookmarkFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadData() {
        List<CourseEntity> expectedCourses = generateDummyCourses(true);
        bookmarks.postValue(Resource.success(PagedListUtil.mockPagedList(expectedCourses)));

        onView(withId(R.id.rv_bookmark)).check(new RecyclerViewItemCountAssertion(expectedCourses.size()));
    }

}
