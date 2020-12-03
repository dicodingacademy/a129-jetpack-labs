package com.dicoding.academies.ui.reader;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.ContentEntity;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.DataDummy;
import com.dicoding.academies.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseReaderViewModelTest {

    private CourseReaderViewModel viewModel;
    private CourseEntity dummyCourse = DataDummy.generateDummyCourses().get(0);
    private String courseId = dummyCourse.getCourseId();
    private List<ModuleEntity> dummyModules = DataDummy.generateDummyModules(courseId);
    private String moduleId = dummyModules.get(0).getModuleId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AcademyRepository academyRepository;

    @Mock
    private Observer<Resource<List<ModuleEntity>>> modulesObserver;

    @Mock
    private Observer<Resource<ModuleEntity>> moduleObserver;

    @Before
    public void setUp() {
        viewModel = new CourseReaderViewModel(academyRepository);
        viewModel.setCourseId(courseId);
        viewModel.setSelectedModule(moduleId);

        ModuleEntity dummyModule = dummyModules.get(0);
        dummyModule.contentEntity = new ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + dummyModule.getTitle() + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
    }

    @Test
    public void getModules() {
        MutableLiveData<Resource<List<ModuleEntity>>> modules = new MutableLiveData<>();
        Resource<List<ModuleEntity>> resource = Resource.success(dummyModules);
        modules.setValue(resource);
        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(modules);

        viewModel.modules.observeForever(modulesObserver);
        verify(modulesObserver).onChanged(resource);
    }

    @Test
    public void getSelectedModule() {
        MutableLiveData<Resource<ModuleEntity>> module = new MutableLiveData<>();
        Resource<ModuleEntity> resource = Resource.success(dummyModules.get(0));
        module.setValue(resource);
        when(academyRepository.getContent(moduleId)).thenReturn(module);


        viewModel.selectedModule.observeForever(moduleObserver);
        verify(moduleObserver).onChanged(resource);
    }
}