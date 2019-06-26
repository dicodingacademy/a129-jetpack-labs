package com.dicoding.academies.ui.academy;

import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AcademyViewModelTest {

    private AcademyViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);

    @Before
    public void setUp() {
        viewModel = new AcademyViewModel(academyRepository);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getCourses() {
        when(academyRepository.getAllCourses()).thenReturn(FakeDataDummy.generateDummyCourses());
        List<CourseEntity> courseEntities = viewModel.getCourses();
        assertNotNull(courseEntities);
        verify(academyRepository).getAllCourses();
        assertEquals(5, courseEntities.size());
    }
}

