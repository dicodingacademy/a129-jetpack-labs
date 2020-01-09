package com.dicoding.academies.di;

import android.content.Context;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.LocalDataSource;
import com.dicoding.academies.data.source.local.room.AcademyDatabase;
import com.dicoding.academies.data.source.remote.RemoteDataSource;
import com.dicoding.academies.utils.AppExecutors;
import com.dicoding.academies.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Context context) {

        AcademyDatabase database = AcademyDatabase.getInstance(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.academyDao());
        AppExecutors appExecutors = new AppExecutors();

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}
