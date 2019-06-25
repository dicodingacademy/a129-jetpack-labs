package com.dicoding.academies.ui.academy;

import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.testing.SingleFragmentActivity;
import com.dicoding.academies.utils.EspressoIdlingResource;
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

@RunWith(AndroidJUnit4.class)
public class AcademyFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private MutableLiveData<Resource<List<CourseEntity>>> courses = new MutableLiveData<>();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());

        AcademyViewModel academyViewModel = mock(AcademyViewModel.class);
        academyViewModel.courses = courses;
        doNothing().when(academyViewModel).setUsername(any());

        AcademyFragment academyFragment = new AcademyFragment();
        academyFragment.factory = ViewModelUtil.createFor(academyViewModel);

        activityRule.getActivity().setFragment(academyFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadData() {
        List<CourseEntity> expectedCourse = generateDummyCourses(false);
        courses.postValue(Resource.success(expectedCourse));

        onView(withId(R.id.rv_academy)).check(new RecyclerViewItemCountAssertion(expectedCourse.size()));
    }
}
