package com.dicoding.academies.di;

import android.content.Context;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.remote.RemoteDataSource;
import com.dicoding.academies.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Context context) {

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));

        return AcademyRepository.getInstance(remoteDataSource);
    }
}
