package com.dicoding.academies.data

import com.dicoding.academies.data.source.local.entity.ContentEntity
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.data.source.local.entity.ModuleEntity
import com.dicoding.academies.data.source.remote.RemoteDataSource
import java.util.*

class FakeAcademyRepository(private val remoteDataSource: RemoteDataSource) : AcademyDataSource {

    companion object {
        @Volatile
        private var instance: FakeAcademyRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FakeAcademyRepository =
                instance ?: synchronized(this) {
                    instance ?: FakeAcademyRepository(remoteData)
                }
    }

    override fun getAllCourses(): ArrayList<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (i in courseResponses.indices) {
            val response = courseResponses[i]
            val course = CourseEntity(response.id,
                    response.title,
                    response.description,
                    response.date,
                    false,
                    response.imagePath)

            courseList.add(course)
        }
        return courseList
    }


    override fun getBookmarkedCourses(): ArrayList<CourseEntity> {
        val courseList = ArrayList<CourseEntity>()
        val courses = remoteDataSource.getAllCourses()
        for (i in courses.indices) {
            val response = courses[i]
            val course = CourseEntity(response.id,
                    response.title,
                    response.description,
                    response.date,
                    false,
                    response.imagePath)
            courseList.add(course)
        }
        return courseList
    }

    // Pada metode ini di modul selanjutnya akan mengembalikan kelas POJO baru, gabungan antara course dengan module-nya.
    override fun getCourseWithModules(courseId: String?): CourseEntity {
        var course: CourseEntity? = null
        val courses = remoteDataSource.getAllCourses()
        for (i in courses.indices) {
            val response = courses[i]
            if (response.id == courseId) {
                course = CourseEntity(response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath)
            }
        }
        return course as CourseEntity
    }

    override fun getAllModulesByCourse(courseId: String?): ArrayList<ModuleEntity> {
        val moduleList = ArrayList<ModuleEntity>()
        val moduleResponses = remoteDataSource.getModules(courseId)
        for (i in moduleResponses.indices) {
            val response = moduleResponses[i]
            val course = ModuleEntity(response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false)

            moduleList.add(course)
        }

        return moduleList
    }


    override fun getContent(courseId: String?, moduleId: String?): ModuleEntity {
        val moduleResponses = remoteDataSource.getModules(courseId)

        var module: ModuleEntity? = null
        for (i in moduleResponses.indices) {
            val moduleResponse = moduleResponses[i]

            val id = moduleResponse.moduleId

            if (id == moduleId) {
                module = ModuleEntity(id, moduleResponse.courseId, moduleResponse.title, moduleResponse.position, false)

                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }

        return module as ModuleEntity
    }
}

