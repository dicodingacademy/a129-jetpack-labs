package com.dicoding.academies.di.module;

import com.dicoding.academies.ui.academy.AcademyFragment;
import com.dicoding.academies.ui.bookmark.BookmarkFragment;
import com.dicoding.academies.ui.reader.content.ModuleContentFragment;
import com.dicoding.academies.ui.reader.list.ModuleListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract AcademyFragment contributeAcademyFragment();

    @ContributesAndroidInjector
    abstract BookmarkFragment contributeBookmarkFragment();

    @ContributesAndroidInjector
    abstract ModuleListFragment contributeModuleListFragment();

    @ContributesAndroidInjector
    abstract ModuleContentFragment contributeModuleContentFragment();

}
