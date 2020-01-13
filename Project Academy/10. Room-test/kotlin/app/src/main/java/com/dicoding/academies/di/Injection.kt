package com.dicoding.academies.di

import android.content.Context

import com.dicoding.academies.data.AcademyRepository
import com.dicoding.academies.data.source.local.LocalDataSource
import com.dicoding.academies.data.source.local.room.AcademyDatabase
import com.dicoding.academies.data.source.remote.RemoteDataSource
import com.dicoding.academies.utils.AppExecutors
import com.dicoding.academies.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
