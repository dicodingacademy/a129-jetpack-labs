package com.dicoding.academies.di;

import android.app.Application;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.LocalRepository;
import com.dicoding.academies.data.source.local.room.AcademyDatabase;
import com.dicoding.academies.data.source.remote.RemoteRepository;
import com.dicoding.academies.utils.AppExecutors;
import com.dicoding.academies.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Application application) {

        AcademyDatabase database = AcademyDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.academyDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();

        return AcademyRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }
}
