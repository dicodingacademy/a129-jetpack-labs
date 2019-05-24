package com.dicoding.academies.ui.bookmark;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.utils.DataDummy;
import com.dicoding.academies.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookmarkViewModelTest {
    private BookmarkViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    @Before
    public void setUp() {
        viewModel = new BookmarkViewModel(academyRepository);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBookmark() {
        when(academyRepository.getBookmarkedCourses()).thenReturn(FakeDataDummy.generateDummyCourses());
        List<CourseEntity> resultCourse = viewModel.getBookmarks();
        verify(academyRepository).getBookmarkedCourses();
        assertEquals(5, resultCourse.size());
    }
}