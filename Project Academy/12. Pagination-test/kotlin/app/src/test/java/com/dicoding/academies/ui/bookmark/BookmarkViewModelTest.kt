package com.dicoding.academies.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.utils.DataDummy
import org.junit.Assert
import org.junit.Assert.assertEquals
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
class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<PagedList<CourseEntity>>

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun `getBookmarkedCourses should be success`() {
        val expected = MutableLiveData<PagedList<CourseEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyCourses())

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(expected)

        viewModel.getBookmarks().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getBookmarks().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getBookmarkedCourses should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<CourseEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(expected)

        viewModel.getBookmarks().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getBookmarks().value?.size
        Assert.assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
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