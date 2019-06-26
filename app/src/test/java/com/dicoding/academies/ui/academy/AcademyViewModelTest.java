package com.dicoding.academies.ui.academy;

import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.utils.DataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;


public class AcademyViewModelTest {

    private AcademyViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new AcademyViewModel();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getCourses() {
        List<CourseEntity> courseEntities = viewModel.getCourses();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());
    }

}
