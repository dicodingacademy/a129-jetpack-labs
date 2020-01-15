package com.dicoding.academies.ui.reader;

import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;

import java.util.ArrayList;


public class CourseReaderViewModel extends ViewModel {

    private String courseId;
    private String moduleId;
    private AcademyRepository academyRepository;

    public CourseReaderViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public void setSelectedCourse(String courseId) {
        this.courseId = courseId;
    }

    public void setSelectedModule(String moduleId) {
        this.moduleId = moduleId;
    }

    public ArrayList<ModuleEntity> getModules() {
        return academyRepository.getAllModulesByCourse(courseId);
    }

    public ModuleEntity getSelectedModule() {
        return academyRepository.getContent(courseId, moduleId);
    }
}


