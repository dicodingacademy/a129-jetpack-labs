package com.dicoding.academies.data;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;

import com.dicoding.academies.data.source.remote.response.ContentResponse;
import com.dicoding.academies.data.source.remote.response.CourseResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;
import com.dicoding.academies.utils.JsonHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class RemoteTest {

    private JsonHelper jsonHelper;
    private Application application;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        application = ApplicationProvider.getApplicationContext();
        jsonHelper = new JsonHelper(application);
    }

    @After
    public void tearDown() {
    }

    private final String courseId = "a14";
    private final String moduleId = "a14m1";

    @Test
    public void loadCourses() {
        List<CourseResponse> expectedCourse = jsonHelper.loadCourses();

        assertNotNull(expectedCourse);
    }

    @Test
    public void loadModule() {
        List<ModuleResponse> expectedModules = jsonHelper.loadModule(courseId);

        assertNotNull(expectedModules);
    }

    @Test
    public void loadContent() {
        ContentResponse expectedContent = jsonHelper.loadContent(moduleId);

        assertNotNull(expectedContent);
    }

}
