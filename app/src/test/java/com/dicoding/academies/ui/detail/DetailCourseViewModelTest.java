package com.dicoding.academies.ui.detail;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailCourseViewModelTest {
    private DetailCourseViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();

    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getCourse() {
        when(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse);
        CourseEntity resultCourse = viewModel.getCourse();
        verify(academyRepository).getCourseWithModules(courseId);
        assertEquals(dummyCourse.getCourseId(), resultCourse.getCourseId());
    }

    @Test
    public void getModules() {
        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(FakeDataDummy.generateDummyModules(courseId));
        List<ModuleEntity> resultModules = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseId);
        assertEquals(7, resultModules.size());
    }
}