package com.dicoding.academies.ui.reader;

import com.dicoding.academies.data.ContentEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseReaderViewModelTest {

    private CourseReaderViewModel viewModel;
    private ContentEntity content;
    private String moduleId;

    @Before
    public void setUp() {
        viewModel = new CourseReaderViewModel();
        viewModel.setCourseId("a14");

        moduleId = "a14m1";

        String title = viewModel.getModules().get(0).getTitle();
        content = new ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getModules() {
        assertEquals(7, viewModel.getModules().size());
    }

    @Test
    public void getSelectedModule() {
        viewModel.setSelectedModule(moduleId);

        assertEquals(viewModel.getSelectedModule().contentEntity.getContent(), content.getContent());
    }

}
