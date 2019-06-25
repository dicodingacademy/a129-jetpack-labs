package com.dicoding.academies.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dicoding.academies.ui.academy.AcademyViewModel;
import com.dicoding.academies.di.ViewModelKey;
import com.dicoding.academies.ui.bookmark.BookmarkViewModel;
import com.dicoding.academies.ui.detail.DetailCourseViewModel;
import com.dicoding.academies.ui.reader.CourseReaderViewModel;
import com.dicoding.academies.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AcademyViewModel.class)
    abstract ViewModel bindAcademyViewModel(AcademyViewModel academyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkViewModel.class)
    abstract ViewModel bindBookmarkViewModel(BookmarkViewModel bookmarkViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailCourseViewModel.class)
    abstract ViewModel bindDetailCourseModel(DetailCourseViewModel detailCourseViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CourseReaderViewModel.class)
    abstract ViewModel bindCourseReaderModel(CourseReaderViewModel courseReaderViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);
}