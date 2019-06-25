package com.dicoding.academies.ui.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.di.Injectable;
import com.dicoding.academies.vo.Resource;

import javax.inject.Inject;


public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    @Inject
    public BookmarkViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

//    LiveData<Resource<List<CourseEntity>>> getBookmarks() {
//        return academyRepository.getBookmarkedCourses();
//    }

    LiveData<Resource<PagedList<CourseEntity>>> getBookmarksPaged() {
        return academyRepository.getBookmarkedCoursesPaged();
    }

    void setBookmark(CourseEntity courseEntity) {
        // Kode di bawah menggunakan tanda seru (!),
        // karena akan mengganti status dari apakah sudah di bookmark atau tidak menjadi apakah sudah siap dibookmark atau tidak
        final boolean newState = !courseEntity.isBookmarked();
        academyRepository.setCourseBookmark(courseEntity, newState);
    }
}

