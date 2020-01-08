package com.dicoding.academies.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.vo.Resource;

public class DetailCourseViewModel extends ViewModel {
    private MutableLiveData<String> courseId = new MutableLiveData<>();
    private AcademyRepository academyRepository;

    public DetailCourseViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<Resource<CourseWithModule>> courseModule = Transformations.switchMap(courseId,
            mCourseId -> academyRepository.getCourseWithModules(mCourseId));

    public String getCourseId() {
        return courseId.getValue();
    }

    public void setCourseId(String courseId) {
        this.courseId.setValue(courseId);
    }

    void setBookmark() {
        Resource<CourseWithModule> moduleResource = courseModule.getValue();
        if (moduleResource != null) {
            CourseWithModule courseWithModule = moduleResource.data;

            if (courseWithModule != null) {
                CourseEntity courseEntity = courseWithModule.mCourse;

                // Kode di bawah menggunakan tanda seru (!),
                // karena akan mengganti status dari apakah sudah di bookmark atau tidak menjadi apakah sudah siap dibookmark atau tidak
                final boolean newState = !courseEntity.isBookmarked();
                academyRepository.setCourseBookmark(courseEntity, newState);
            }
        }
    }
}


