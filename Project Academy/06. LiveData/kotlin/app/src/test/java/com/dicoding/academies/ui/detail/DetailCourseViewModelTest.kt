package com.dicoding.academies.ui.detail

import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.ModuleEntity
import com.dicoding.academies.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {
    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)
        val courseEntity = viewModel.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules() {
        `when`<ArrayList<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId)).thenReturn(DataDummy.generateDummyModules(courseId))
        val moduleEntities = viewModel.getModules()
        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }
}