package com.dicoding.academies.ui.academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dicoding.academies.data.source.AcademyRepository;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.vo.Resource;

import java.util.List;


public class AcademyViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    public AcademyViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<Resource<List<CourseEntity>>> courses = Transformations.switchMap(mLogin,
            data -> academyRepository.getAllCourses());

    void setUsername(String username) {
        mLogin.setValue(username);
    }
}


