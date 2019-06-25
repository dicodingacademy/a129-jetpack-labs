package com.dicoding.academies.ui.detail;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.intercepting.SingleActivityFactory;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.utils.EspressoIdlingResource;
import com.dicoding.academies.utils.FakeDataDummy;
import com.dicoding.academies.utils.RecyclerViewItemCountAssertion;
import com.dicoding.academies.utils.ViewModelUtil;
import com.dicoding.academies.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.dicoding.academies.utils.FakeDataDummy.generateDummyCourseWithModules;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class DetailCourseActivityTest {

    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses(false).get(0);

    private MutableLiveData<Resource<CourseWithModule>> courseWithModule = new MutableLiveData<>();

    private SingleActivityFactory<DetailCourseActivity> injectedFactory = new SingleActivityFactory<DetailCourseActivity>(DetailCourseActivity.class) {
        @Override
        protected DetailCourseActivity create(Intent intent) {
            DetailCourseViewModel detailCourseViewModel = mock(DetailCourseViewModel.class);
            detailCourseViewModel.courseModule = courseWithModule;
            doNothing().when(detailCourseViewModel).setCourseId(null);

            DetailCourseActivity activity = new DetailCourseActivity();
            activity.factory = ViewModelUtil.createFor(detailCourseViewModel);

            return activity;
        }
    };

    @Rule
    public ActivityTestRule<DetailCourseActivity> activityRule = new ActivityTestRule<>(injectedFactory, false, false);


    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());

        Intent result = new Intent();
        result.putExtra(DetailCourseActivity.EXTRA_COURSE, dummyCourse.getCourseId());

        activityRule.launchActivity(result);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadCourse() {
        CourseWithModule expectedCourseModule = generateDummyCourseWithModules(dummyCourse, false);
        courseWithModule.postValue(Resource.success(expectedCourseModule));

        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse.getTitle())));
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Deadline %s", dummyCourse.getDeadline()))));
    }

    @Test
    public void loadModules() {
        CourseWithModule expectedCourseModule = generateDummyCourseWithModules(dummyCourse, false);
        courseWithModule.postValue(Resource.success(expectedCourseModule));

        assertNotNull(expectedCourseModule);
        assertNotNull(expectedCourseModule.mModules);
        onView(withId(R.id.rv_module)).check(new RecyclerViewItemCountAssertion(expectedCourseModule.mModules.size()));
    }
}
