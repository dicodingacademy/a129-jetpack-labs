package com.dicoding.academies.di.component;

import android.app.Application;

import com.dicoding.academies.CustomApplication;
import com.dicoding.academies.di.module.ActivityModule;
import com.dicoding.academies.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                AppModule.class,
                ActivityModule.class
        }
)
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(CustomApplication application);

}