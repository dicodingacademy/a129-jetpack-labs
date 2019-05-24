package com.dicoding.academies.ui.detail;

import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;

import java.util.List;

public class DetailCourseViewModel extends ViewModel {
    private CourseEntity mCourse;
    private String courseId;
    private AcademyRepository academyRepository;

    public DetailCourseViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public CourseEntity getCourse() {
        return academyRepository.getCourseWithModules(courseId);
    }

    public List<ModuleEntity> getModules() {
        return academyRepository.getAllModulesByCourse(courseId);
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}
