package com.dicoding.academies.ui.reader;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.ContentEntity;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.FakeDataDummy;
import com.dicoding.academies.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseReaderViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private CourseReaderViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();
    private ArrayList<ModuleEntity> dummyModules = FakeDataDummy.generateDummyModules(courseId);
    private String moduleId = dummyModules.get(0).getModuleId();

    @Before
    public void setUp() {
        viewModel = new CourseReaderViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getModules() {
        MutableLiveData<Resource<List<ModuleEntity>>> moduleEntities = new MutableLiveData<>();
        moduleEntities.setValue(Resource.success(dummyModules));

        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities);

        Observer<Resource<List<ModuleEntity>>> observer = mock(Observer.class);
        viewModel.modules.observeForever(observer);

        verify(academyRepository).getAllModulesByCourse(courseId);
    }

    @Test
    public void getSelectedModule() {
        MutableLiveData<Resource<ModuleEntity>> moduleEntity = new MutableLiveData<>();

        ModuleEntity dummyModule = dummyModules.get(0);
        String content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>";
        dummyModule.contentEntity = new ContentEntity(content);

        moduleEntity.setValue(Resource.success(dummyModule));

        when(academyRepository.getContent(moduleId)).thenReturn(moduleEntity);

        viewModel.setSelectedModule(moduleId);

        Observer<Resource<ModuleEntity>> observer = mock(Observer.class);
        viewModel.selectedModule.observeForever(observer);
        verify(academyRepository).getContent(moduleId);
    }
}