package com.dicoding.academies.ui.bookmark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertEquals(5, viewModel.getBookmarks().size());
    }
}