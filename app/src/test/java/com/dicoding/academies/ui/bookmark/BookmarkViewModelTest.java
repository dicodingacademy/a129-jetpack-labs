
package com.dicoding.academies.ui.bookmark;


import com.dicoding.academies.data.source.local.entity.CourseEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BookmarkViewModelTest {
    private BookmarkViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new BookmarkViewModel();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getBookmark() {
        List<CourseEntity> courseEntities = viewModel.getBookmarks();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());
    }
}