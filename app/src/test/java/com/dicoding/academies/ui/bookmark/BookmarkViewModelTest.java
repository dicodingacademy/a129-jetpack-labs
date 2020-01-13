package com.dicoding.academies.ui.bookmark;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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

        MutableLiveData<Resource<PagedList<CourseEntity>>> dummyCourse = new MutableLiveData<>();
        PagedList<CourseEntity> pagedList = mock(PagedList.class);

        dummyCourse.setValue(Resource.success(pagedList));

        when(academyRepository.getBookmarkedCoursesPaged()).thenReturn(dummyCourse);

        Observer<Resource<PagedList<CourseEntity>>> observer = mock(Observer.class);

        viewModel.getBookmarksPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));

    }
}