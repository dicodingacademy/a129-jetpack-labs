package com.dicoding.academies.di;

import android.app.Application;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.LocalRepository;
import com.dicoding.academies.data.source.remote.JsonHelper;
import com.dicoding.academies.data.source.remote.RemoteRepository;

public class Injection {

    public static AcademyRepository provideRepository(Application application) {

        LocalRepository localRepository = new LocalRepository();
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        return AcademyRepository.getInstance(localRepository, remoteRepository);
    }
}
