package com.dicoding.academies.ui.reader;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.ContentEntity;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseReaderViewModelTest {

    private CourseReaderViewModel viewModel;
    private CourseEntity dummyCourse = DataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();
    private ArrayList<ModuleEntity> dummyModules = DataDummy.generateDummyModules(courseId);
    private String moduleId = dummyModules.get(0).getModuleId();

    @Mock
    private AcademyRepository academyRepository;

    @Before
    public void setUp() {
        viewModel = new CourseReaderViewModel(academyRepository);
        viewModel.setSelectedCourse(courseId);
        viewModel.setSelectedModule(moduleId);

        ModuleEntity dummyModule = dummyModules.get(0);
        dummyModule.contentEntity = new ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + dummyModule.getTitle() + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
    }

    @Test
    public void getModules() {
        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules);
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseId);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }

    @Test
    public void getSelectedModule() {
        when(academyRepository.getContent(courseId, moduleId)).thenReturn(dummyModules.get(0));
        ModuleEntity moduleEntity = viewModel.getSelectedModule();
        verify(academyRepository).getContent(courseId, moduleId);
        assertNotNull(moduleEntity);
        ContentEntity contentEntity = moduleEntity.contentEntity;
        assertNotNull(contentEntity);
        String content = contentEntity.getContent();
        assertNotNull(content);
        assertEquals(content,  dummyModules.get(0).contentEntity.getContent());
    }
}