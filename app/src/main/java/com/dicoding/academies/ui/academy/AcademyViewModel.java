package com.dicoding.academies.ui.academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;

import java.util.List;


public class AcademyViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<List<CourseEntity>> getCourses() {
        return academyRepository.getAllCourses();
    }
}


