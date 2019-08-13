package com.dicoding.academies.di;

import android.app.Application;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.remote.RemoteRepository;
import com.dicoding.academies.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        return AcademyRepository.getInstance(remoteRepository);
    }
}
