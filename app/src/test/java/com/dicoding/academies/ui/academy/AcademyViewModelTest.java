package com.dicoding.academies.ui.academy;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.utils.FakeDataDummy;
import com.dicoding.academies.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class AcademyViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AcademyViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private String USERNAME = "Dicoding";

    @Before
    public void setUp() {
        viewModel = new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses() {
        Resource<List<CourseEntity>> resource = Resource.success(FakeDataDummy.generateDummyCourses());
        MutableLiveData<Resource<List<CourseEntity>>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(resource);

        when(academyRepository.getAllCourses()).thenReturn(dummyCourses);

        Observer<Resource<List<CourseEntity>>> observer = mock(Observer.class);

        viewModel.setUsername(USERNAME);

        viewModel.courses.observeForever(observer);

        verify(observer).onChanged(resource);
    }
}