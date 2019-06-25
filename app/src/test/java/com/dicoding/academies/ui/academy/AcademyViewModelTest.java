package com.dicoding.academies.ui.academy;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.utils.FakeDataDummy;
import com.dicoding.academies.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

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

    @After
    public void tearDown() {
    }

    @Test
    public void getCourses() {
        MutableLiveData<Resource<List<CourseEntity>>> dummyCourse = new MutableLiveData<>();
        dummyCourse.setValue(Resource.success(FakeDataDummy.generateDummyCourses()));

        when(academyRepository.getAllCourses()).thenReturn(dummyCourse);

        Observer<Resource<List<CourseEntity>>> observer = Mockito.mock(Observer.class);

        viewModel.setUsername(USERNAME);

        viewModel.courses.observeForever(observer);

        verify(academyRepository).getAllCourses();
    }
}