package com.dicoding.academies.ui.bookmark;

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


public class BookmarkViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private BookmarkViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new BookmarkViewModel(academyRepository);
    }

    @Test
    public void getBookmark() {
        Resource<List<CourseEntity>> resource = Resource.success(FakeDataDummy.generateDummyCourses());
        MutableLiveData<Resource<List<CourseEntity>>> dummyCourses = new MutableLiveData<>();
        dummyCourses.setValue(resource);

        when(academyRepository.getBookmarkedCourses()).thenReturn(dummyCourses);

        Observer<Resource<List<CourseEntity>>> observer = mock(Observer.class);

        viewModel.getBookmarks().observeForever(observer);

        verify(observer).onChanged(resource);
    }
}