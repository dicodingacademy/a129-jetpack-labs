package com.dicoding.academies.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.academies.data.source.local.LocalDataSource
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.data.source.local.entity.CourseWithModule
import com.dicoding.academies.data.source.local.entity.ModuleEntity
import com.dicoding.academies.data.source.remote.ApiResponse
import com.dicoding.academies.data.source.remote.RemoteDataSource
import com.dicoding.academies.data.source.remote.response.ContentResponse
import com.dicoding.academies.data.source.remote.response.CourseResponse
import com.dicoding.academies.data.source.remote.response.ModuleResponse
import com.dicoding.academies.utils.AppExecutors
import com.dicoding.academies.vo.Resource
import java.util.*

class FakeAcademyRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors)
    : AcademyDataSource {

    override fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>> {
        return object : NetworkBoundResource<PagedList<CourseEntity>, List<CourseResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<CourseEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getAllCourses(), config).build()
            }

            override fun shouldFetch(data: PagedList<CourseEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            public override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return remoteDataSource.getAllCourses()
            }

            public override fun saveCallResult(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (i in courseResponses.indices) {
                    val response = courseResponses[i]
                    val course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath)
                    courseList.add(course)
                }

                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }

    // Pada metode ini di modul selanjutnya akan mengembalikan kelas POJO baru, gabungan antara course dengan module-nya.
    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<CourseWithModule> {
                return localDataSource.getCourseWithModules(courseId)
            }

            override fun shouldFetch(courseWithModule: CourseWithModule?): Boolean {
                return courseWithModule?.mModules == null || courseWithModule.mModules.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModules(courseId)
            }

            override fun saveCallResult(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (i in moduleResponses.indices) {
                    val response = moduleResponses[i]
                    val course = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false)

                    moduleList.add(course)
                }

                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> {
                return localDataSource.getAllModulesByCourse(courseId)
            }

            override fun shouldFetch(modules: List<ModuleEntity>?): Boolean {
                return modules == null || modules.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteDataSource.getModules(courseId)
            }

            override fun saveCallResult(moduleResponses: List<ModuleResponse>) {

                val moduleList = ArrayList<ModuleEntity>()
                for (i in moduleResponses.indices) {
                    val response = moduleResponses[i]
                    val course = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false)

                    moduleList.add(course)
                }

                localDataSource.insertModules(moduleList)

            }
        }.asLiveData()
    }


    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> {
                return localDataSource.getModuleWithContent(moduleId)
            }

            override fun shouldFetch(moduleEntity: ModuleEntity?): Boolean {
                return moduleEntity?.contentEntity == null
            }

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> {
                return remoteDataSource.getContent(moduleId)
            }

            override fun saveCallResult(contentResponse: ContentResponse) {
                localDataSource.updateContent(contentResponse.content.toString(), moduleId)
            }
        }.asLiveData()
    }

    override fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedCourses(), config).build()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setCourseBookmark(course, state) }
    }

    override fun setReadModule(module: ModuleEntity) {
        appExecutors.diskIO().execute { localDataSource.setReadModule(module) }
    }
}

