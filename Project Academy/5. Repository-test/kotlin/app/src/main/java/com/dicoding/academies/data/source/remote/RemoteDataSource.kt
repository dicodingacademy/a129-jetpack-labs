package com.dicoding.academies.data.source.remote

import com.dicoding.academies.data.source.remote.response.ContentResponse
import com.dicoding.academies.data.source.remote.response.CourseResponse
import com.dicoding.academies.data.source.remote.response.ModuleResponse
import com.dicoding.academies.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getAllCourses(): List<CourseResponse> {
        return jsonHelper.loadCourses()
    }

    fun getModules(courseId: String): List<ModuleResponse> {
        return jsonHelper.loadModule(courseId)
    }

    fun getContent(moduleId: String): ContentResponse {
        return jsonHelper.loadContent(moduleId)
    }
}

