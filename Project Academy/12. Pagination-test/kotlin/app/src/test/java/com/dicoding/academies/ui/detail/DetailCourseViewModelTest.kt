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
import org.mockito.Mockito.*
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
    fun `setBookmark should not add favorite when courseModule is empty`() {
        viewModel.setBookmark()
        verify(academyRepository, times(0)).setCourseBookmark(dummyCourse, true)
    }

    @Test
    fun `setBookmark should add favorite when courseModule is not empty and not bookmarked`() {
        val expected = MutableLiveData<Resource<CourseWithModule>>()
        expected.value = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, false))

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(expected)
        viewModel.courseModule.observeForever(observer)

        doNothing().`when`(academyRepository).setCourseBookmark(dummyCourse, true)

        viewModel.setBookmark()
        verify(academyRepository, times(1)).setCourseBookmark(dummyCourse, true)
    }

    @Test
    fun `setBookmark should remove from favorite when courseModule is not empty and already bookmarked`() {
        val expected = MutableLiveData<Resource<CourseWithModule>>()
        expected.value = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, true))

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(expected)
        viewModel.courseModule.observeForever(observer)

        doNothing().`when`(academyRepository).setCourseBookmark(dummyCourse, false)

        viewModel.setBookmark()
        verify(academyRepository, times(1)).setCourseBookmark(dummyCourse, false)
    }

}