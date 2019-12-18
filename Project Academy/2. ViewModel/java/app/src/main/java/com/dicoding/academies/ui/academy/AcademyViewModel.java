package com.dicoding.academies.ui.academy;

import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.utils.DataDummy;

import java.util.List;

public class AcademyViewModel extends ViewModel {

    public List<CourseEntity> getCourses() {
        return DataDummy.generateDummyCourses();
    }
}

