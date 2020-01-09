package com.dicoding.academies.ui.detail;

import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.data.ModuleEntity;
import com.dicoding.academies.utils.DataDummy;

import java.util.List;

public class DetailCourseViewModel extends ViewModel {
    private String courseId;
    private CourseEntity course;

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseEntity getCourse() {
        for (int i = 0; i < DataDummy.generateDummyCourses().size(); i++) {
            CourseEntity courseEntity = DataDummy.generateDummyCourses().get(i);
            if (courseEntity.getCourseId().equals(courseId)) {
                course = courseEntity;
            }
        }
        return course;
    }

    public List<ModuleEntity> getModules() {
        return DataDummy.generateDummyModules(courseId);
    }
}

