package com.dicoding.academies.di.module;

import com.dicoding.academies.ui.detail.DetailCourseActivity;
import com.dicoding.academies.ui.home.HomeActivity;
import com.dicoding.academies.ui.reader.CourseReaderActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract HomeActivity contributeHomeActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract DetailCourseActivity contributeDetailActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract CourseReaderActivity contributeCourseReaderActivity();

}