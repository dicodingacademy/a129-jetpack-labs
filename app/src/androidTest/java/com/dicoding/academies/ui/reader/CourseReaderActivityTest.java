package com.dicoding.academies.ui.reader;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.intercepting.SingleActivityFactory;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
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

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.dicoding.academies.utils.FakeDataDummy.generateDummyModules;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
public class CourseReaderActivityTest {
    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses(false).get(0);
    private MutableLiveData<Resource<ModuleEntity>> selectedModule = new MutableLiveData<>();
    private MutableLiveData<Resource<List<ModuleEntity>>> modules = new MutableLiveData<>();


    private SingleActivityFactory<CourseReaderActivity> injectedFactory = new SingleActivityFactory<CourseReaderActivity>(CourseReaderActivity.class) {
        @Override
        protected CourseReaderActivity create(Intent intent) {
            CourseReaderViewModel courseReaderViewModel = mock(CourseReaderViewModel.class);
            courseReaderViewModel.selectedModule = selectedModule;
            courseReaderViewModel.modules = modules;

            doNothing().when(courseReaderViewModel).setCourseId(null);

            CourseReaderActivity activity = new CourseReaderActivity();
            activity.factory = ViewModelUtil.createFor(courseReaderViewModel);

            return activity;
        }
    };

    @Rule
    public ActivityTestRule<CourseReaderActivity> activityRule = new ActivityTestRule<>(injectedFactory, true, false);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());

        Intent result = new Intent();
        result.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, dummyCourse.getCourseId());

        activityRule.launchActivity(result);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadModule() {
        List<ModuleEntity> expectedModules = generateDummyModules(dummyCourse.getCourseId());
        modules.postValue(Resource.success(expectedModules));

        onView(withId(R.id.rv_module)).check(new RecyclerViewItemCountAssertion(expectedModules.size()));
    }

    @Test
    public void clickItem() {
        List<ModuleEntity> expectedModules = generateDummyModules(dummyCourse.getCourseId());
        modules.postValue(Resource.success(expectedModules));

        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.web_view)).check(matches(isDisplayed()));
    }
}