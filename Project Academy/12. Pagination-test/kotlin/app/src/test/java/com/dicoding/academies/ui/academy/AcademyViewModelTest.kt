package com.dicoding.academies.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.utils.DataDummy
import com.dicoding.academies.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<CourseEntity>>>

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun `getCourses should be success`() {
        val courses = PagedTestDataSources.snapshot(DataDummy.generateDummyCourses())
        val expected = MutableLiveData<Resource<PagedList<CourseEntity>>>()
        expected.value = Resource.success(courses)

        `when`(academyRepository.getAllCourses()).thenReturn(expected)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getCourses().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getCourses should be success but data is empty`() {
        val courses = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<CourseEntity>>>()
        expected.value = Resource.success(courses)

        `when`(academyRepository.getAllCourses()).thenReturn(expected)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getCourses().value?.data?.size
        assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `getCourses should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<CourseEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(academyRepository.getAllCourses()).thenReturn(expected)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getCourses().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<CourseEntity>) : PositionalDataSource<CourseEntity>() {
        companion object {
            fun snapshot(items: List<CourseEntity> = listOf()): PagedList<CourseEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<CourseEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<CourseEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}