package com.dicoding.academies.ui.detail;

import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.data.ModuleEntity;
import com.dicoding.academies.utils.DataDummy;

import java.util.ArrayList;
import java.util.List;

public class DetailCourseViewModel extends ViewModel {
    private String courseId;

    public void setSelectedCourse(String courseId) {
        this.courseId = courseId;
    }

    public CourseEntity getCourse() {
        CourseEntity course = null;
        ArrayList<CourseEntity> courseEntities = DataDummy.generateDummyCourses();
        for (CourseEntity courseEntity : courseEntities) {
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

