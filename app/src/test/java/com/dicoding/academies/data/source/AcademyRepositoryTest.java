package com.dicoding.academies.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.academies.data.source.local.LocalRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.remote.RemoteRepository;
import com.dicoding.academies.data.source.remote.response.ContentResponse;
import com.dicoding.academies.data.source.remote.response.CourseResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;
import com.dicoding.academies.utils.FakeDataDummy;
import com.dicoding.academies.utils.InstantAppExecutors;
import com.dicoding.academies.utils.LiveDataTestUtil;
import com.dicoding.academies.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository local = Mockito.mock(LocalRepository.class);
    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = Mockito.mock(InstantAppExecutors.class);
    private FakeAcademyRepository academyRepository = new FakeAcademyRepository(local, remote, instantAppExecutors);

    private ArrayList<CourseResponse> courseResponses = FakeDataDummy.generateRemoteDummyCourses();
    private String courseId = courseResponses.get(0).getId();
    private ArrayList<ModuleResponse> moduleResponses = FakeDataDummy.generateRemoteDummyModules(courseId);
    private String moduleId = moduleResponses.get(0).getModuleId();
    private ContentResponse content = FakeDataDummy.generateRemoteDummyContent(moduleId);

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getAllCourses() {
        MutableLiveData<List<CourseEntity>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(FakeDataDummy.generateDummyCourses());

        when(local.getAllCourses()).thenReturn(dummyCourses);

        Resource<List<CourseEntity>> result = LiveDataTestUtil.getValue(academyRepository.getAllCourses());

        verify(local).getAllCourses();
        assertNotNull(result.data);
    }

    @Test
    public void getAllModulesByCourse() {
        MutableLiveData<List<ModuleEntity>> dummyModules = new MutableLiveData<>();
        dummyModules.setValue(FakeDataDummy.generateDummyModules(courseId));

        when(local.getAllModulesByCourse(courseId)).thenReturn(dummyModules);

        Resource<List<ModuleEntity>> result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId));

        verify(local).getAllModulesByCourse(courseId);
        assertNotNull(result);
    }

    @Test
    public void getBookmarkedCourses() {
        MutableLiveData<List<CourseEntity>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(FakeDataDummy.generateDummyCourses());

        when(local.getBookmarkedCourses()).thenReturn(dummyCourses);

        Resource<List<CourseEntity>> result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses());

        verify(local).getBookmarkedCourses();
        assertNotNull(result);
    }

    @Test
    public void getContent() {
        MutableLiveData<ModuleEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(FakeDataDummy.generateDummyModuleWithContent(moduleId));

        when(local.getModuleWithContent(courseId)).thenReturn(dummyEntity);

        Resource<ModuleEntity> result = LiveDataTestUtil.getValue(academyRepository.getContent(courseId));

        verify(local).getModuleWithContent(courseId);
        assertNotNull(result);
    }

    @Test
    public void getCourseWithModules() {
        MutableLiveData<CourseWithModule> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(FakeDataDummy.generateDummyCourseWithModules(FakeDataDummy.generateDummyCourses().get(0), false));

        when(local.getCourseWithModules(courseId)).thenReturn(dummyEntity);

        Resource<CourseWithModule> result = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId));

        verify(local).getCourseWithModules(courseId);
        assertNotNull(result);
    }
}
