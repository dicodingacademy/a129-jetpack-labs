package com.dicoding.academies.di.module;

import android.app.Application;

import androidx.room.Room;

import com.dicoding.academies.data.source.local.LocalRepository;
import com.dicoding.academies.data.source.local.room.AcademyDao;
import com.dicoding.academies.data.source.local.room.AcademyDatabase;
import com.dicoding.academies.data.source.remote.RemoteRepository;
import com.dicoding.academies.utils.JsonHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton
    @Provides
    AcademyDatabase academyDatabase(Application application) {
        return Room.databaseBuilder(application,
                AcademyDatabase.class, "Academies.db")
                .build();
    }

    @Singleton
    @Provides
    AcademyDao provideAcademyDao(AcademyDatabase database) {
        return database.academyDao();
    }

    @Singleton
    @Provides
    LocalRepository provideLocal(AcademyDao dao) {
        return new LocalRepository(dao);
    }

    @Singleton
    @Provides
    JsonHelper provideJsonHelper(Application application) {
        return new JsonHelper(application);
    }

    @Singleton
    @Provides
    RemoteRepository provideRemote(JsonHelper jsonHelper) {
        return new RemoteRepository(jsonHelper);
    }
}
