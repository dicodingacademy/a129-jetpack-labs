package com.dicoding.academies.ui.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;


public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<PagedList<CourseEntity>> getBookmarks() {
        return academyRepository.getBookmarkedCourses();
    }

    void setBookmark(CourseEntity courseEntity) {
        final boolean newState = !courseEntity.isBookmarked();
        academyRepository.setCourseBookmark(courseEntity, newState);
    }
}

