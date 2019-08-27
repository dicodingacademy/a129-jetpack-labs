package com.dicoding.academies.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.utils.FakeDataDummy;
import com.dicoding.academies.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

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
    private ArrayList<ModuleEntity> dummyModules = FakeDataDummy.generateDummyModules(courseId);


    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setCourseId(courseId);
        viewModel.setBookmark();
    }

    @Test
    public void getCourseWithModule() {
        Resource<CourseWithModule> resource = Resource.success(FakeDataDummy.generateDummyCourseWithModules(dummyCourse, true));
        MutableLiveData<Resource<CourseWithModule>> courseEntities = new MutableLiveData<>();
        courseEntities.setValue(resource);

        when(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntities);

        Observer<Resource<CourseWithModule>> observer = mock(Observer.class);
        viewModel.courseModule.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}