package com.dicoding.academies.ui.detail;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailCourseViewModelTest {
    private DetailCourseViewModel viewModel;
    private CourseEntity dummyCourse = DataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();

    @Mock
    private AcademyRepository academyRepository;

    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setSelectedCourse(courseId);
    }

    @Test
    public void getCourse() {
        when(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse);
        CourseEntity courseEntity = viewModel.getCourse();
        verify(academyRepository).getCourseWithModules(courseId);
        assertNotNull(courseEntity);
        assertEquals(dummyCourse.getCourseId(), courseEntity.getCourseId());
        assertEquals(dummyCourse.getDeadline(), courseEntity.getDeadline());
        assertEquals(dummyCourse.getDescription(), courseEntity.getDescription());
        assertEquals(dummyCourse.getImagePath(), courseEntity.getImagePath());
        assertEquals(dummyCourse.getTitle(), courseEntity.getTitle());
    }

    @Test
    public void getModules() {
        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(DataDummy.generateDummyModules(courseId));
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseId);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }
}