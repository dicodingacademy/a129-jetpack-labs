package com.dicoding.academies.ui.academy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcademyViewModelTest {

    private AcademyViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new AcademyViewModel();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getCourses() {
        assertEquals(5, viewModel.getCourses().size());
    }

}