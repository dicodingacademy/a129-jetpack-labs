package com.dicoding.academies.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.di.Injection;
import com.dicoding.academies.ui.academy.AcademyViewModel;
import com.dicoding.academies.ui.bookmark.BookmarkViewModel;
import com.dicoding.academies.ui.detail.DetailCourseViewModel;
import com.dicoding.academies.ui.reader.CourseReaderViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final AcademyRepository mAcademyRepository;

    private ViewModelFactory(AcademyRepository academyRepository) {
        mAcademyRepository = academyRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(AcademyViewModel.class)) {
            //noinspection unchecked
            return (T) new AcademyViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(DetailCourseViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailCourseViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(BookmarkViewModel.class)) {
            //noinspection unchecked
            return (T) new BookmarkViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(CourseReaderViewModel.class)) {
            //noinspection unchecked
            return (T) new CourseReaderViewModel(mAcademyRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
