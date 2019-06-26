package com.dicoding.academies.ui.detail;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

        viewModel.setCourseId(dummyCourse.getCourseId());
        CourseEntity courseEntity = viewModel.getCourse();
        assertNotNull(courseEntity);
        assertEquals(dummyCourse.getCourseId(), courseEntity.getCourseId());
        assertEquals(dummyCourse.getDeadline(), courseEntity.getDeadline());
        assertEquals(dummyCourse.getDescription(), courseEntity.getDescription());
        assertEquals(dummyCourse.getImagePath(), courseEntity.getImagePath());
        assertEquals(dummyCourse.getTitle(), courseEntity.getTitle());

        when(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse);
        CourseEntity resultCourse = viewModel.getCourse();
        verify(academyRepository).getCourseWithModules(courseId);
        assertEquals(dummyCourse.getCourseId(), resultCourse.getCourseId());

    }

    @Test
    public void getModules() {

        viewModel.setCourseId(dummyCourse.getCourseId());
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());

        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(FakeDataDummy.generateDummyModules(courseId));
        List<ModuleEntity> resultModules = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseId);
        assertEquals(7, resultModules.size());

    }
}