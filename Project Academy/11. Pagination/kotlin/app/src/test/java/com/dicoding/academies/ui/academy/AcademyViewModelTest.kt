package com.dicoding.academies.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.utils.DataDummy
import com.dicoding.academies.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        val dummyCourses = Resource.success(DataDummy.generateDummyCourses()) as Resource<List<CourseEntity>>
        val courses = MutableLiveData<Resource<List<CourseEntity>>>()
        courses.value = dummyCourses

        `when`<LiveData<Resource<List<CourseEntity>>>>(academyRepository.getAllCourses()).thenReturn(courses)
        val courseEntities = viewModel.getCourses().value?.data
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        val observer = mock(Observer::class.java) as Observer<Resource<List<CourseEntity>>>
        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}