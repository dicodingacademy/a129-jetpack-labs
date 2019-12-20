package com.dicoding.academies.ui.reader

import com.dicoding.academies.data.source.local.entity.ContentEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private lateinit var dummyContentEntity: ContentEntity
    private var moduleId: String? = null

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel()
        viewModel.courseId = "a14"

        moduleId = "a14m1"

        val title = viewModel.getModules()[0].title
        dummyContentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">$title</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Test
    fun getModules() {
        val moduleEntities = viewModel.getModules()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }

    @Test
    fun getSelectedModule() {
        viewModel.moduleId = moduleId
        val moduleEntity = viewModel.getSelectedModule()
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity.contentEntity
        assertNotNull(contentEntity)
        val content = contentEntity.content
        assertNotNull(content)
        assertEquals(content, dummyContentEntity.content)
    }

}