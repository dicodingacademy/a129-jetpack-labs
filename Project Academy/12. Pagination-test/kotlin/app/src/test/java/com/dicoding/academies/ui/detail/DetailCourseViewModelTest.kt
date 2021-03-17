package com.dicoding.academies.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.CourseWithModule
import com.dicoding.academies.utils.DataDummy
import com.dicoding.academies.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
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

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<CourseWithModule>>

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun `setSelectedCourse should be success`() {
        val expected = MutableLiveData<Resource<CourseWithModule>>()
        expected.value = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, true))

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(expected)

        viewModel.courseModule.observeForever(observer)

        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.courseModule.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setBookmark should be success trigger courseModule observer`() {
        val expected = MutableLiveData<Resource<CourseWithModule>>()
        expected.value = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, true))

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(expected)

        viewModel.setBookmark()
        viewModel.courseModule.observeForever(observer)

        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.courseModule.value

        assertEquals(expectedValue, actualValue)
    }
}