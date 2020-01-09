package com.dicoding.academies.ui.bookmark;

import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.utils.DataDummy;

import java.util.List;

public class BookmarkViewModel extends ViewModel {
    List<CourseEntity> getBookmarks() {
        return DataDummy.generateDummyCourses();
    }
}

