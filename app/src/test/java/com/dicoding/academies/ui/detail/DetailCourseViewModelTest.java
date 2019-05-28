package com.dicoding.academies.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailCourseViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

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
        MutableLiveData<CourseEntity> courseEntities = new MutableLiveData<>();
        courseEntities.setValue(dummyCourse);

        when(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntities);

        Observer<CourseEntity> observer = mock(Observer.class);
        viewModel.getCourse().observeForever(observer);

        verify(academyRepository).getCourseWithModules(courseId);
    }

    @Test
    public void getModules() {
        MutableLiveData<List<ModuleEntity>> moduleEntities = new MutableLiveData<>();
        moduleEntities.setValue(FakeDataDummy.generateDummyModules(courseId));

        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities);

        Observer<List<ModuleEntity>> observer = mock(Observer.class);
        viewModel.getModules().observeForever(observer);
        verify(academyRepository).getAllModulesByCourse(courseId);
    }
}