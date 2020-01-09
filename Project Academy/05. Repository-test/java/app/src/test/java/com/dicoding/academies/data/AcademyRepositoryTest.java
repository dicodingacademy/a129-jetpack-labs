package com.dicoding.academies.data;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.remote.RemoteDataSource;
import com.dicoding.academies.data.source.remote.response.ContentResponse;
import com.dicoding.academies.data.source.remote.response.CourseResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;
import com.dicoding.academies.utils.DataDummy;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyRepositoryTest {

    private RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private FakeAcademyRepository academyRepository = new FakeAcademyRepository(remote);

    private ArrayList<CourseResponse> courseResponses = DataDummy.generateRemoteDummyCourses();
    private String courseId = courseResponses.get(0).getId();
    private ArrayList<ModuleResponse> moduleResponses = DataDummy.generateRemoteDummyModules(courseId);
    private String moduleId = moduleResponses.get(0).getModuleId();
    private ContentResponse content = DataDummy.generateRemoteDummyContent(moduleId);

    @Test
    public void getAllCourses() {
        when(remote.getAllCourses()).thenReturn(courseResponses);
        ArrayList<CourseEntity> courseEntities = academyRepository.getAllCourses();
        verify(remote).getAllCourses();
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(), courseEntities.size());
    }

    @Test
    public void getAllModulesByCourse() {
        when(remote.getModules(courseId)).thenReturn(moduleResponses);
        ArrayList<ModuleEntity> moduleEntities = academyRepository.getAllModulesByCourse(courseId);
        verify(remote).getModules(courseId);
        assertNotNull(moduleEntities);
        assertEquals(moduleResponses.size(), moduleEntities.size());
    }

    @Test
    public void getBookmarkedCourses() {
        when(remote.getAllCourses()).thenReturn(courseResponses);
        ArrayList<CourseEntity> courseEntities = academyRepository.getBookmarkedCourses();
        verify(remote).getAllCourses();
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(), courseEntities.size());
    }

    @Test
    public void getContent() {
        when(remote.getModules(courseId)).thenReturn(moduleResponses);
        when(remote.getContent(moduleId)).thenReturn(content);
        ModuleEntity resultModule = academyRepository.getContent(courseId, moduleId);
        verify(remote).getContent(moduleId);
        assertNotNull(resultModule);
        assertEquals(content.getContent(), resultModule.contentEntity.getContent());
    }

    @Test
    public void getCourseWithModules() {
        when(remote.getAllCourses()).thenReturn(courseResponses);
        CourseEntity resultCourse = academyRepository.getCourseWithModules(courseId);
        verify(remote).getAllCourses();
        assertNotNull(resultCourse);
        assertEquals(courseResponses.get(0).getTitle(), resultCourse.getTitle());
    }
}