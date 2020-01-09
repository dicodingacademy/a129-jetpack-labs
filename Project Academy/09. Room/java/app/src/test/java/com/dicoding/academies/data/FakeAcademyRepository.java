package com.dicoding.academies.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.academies.data.source.local.entity.ContentEntity;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.remote.RemoteDataSource;
import com.dicoding.academies.data.source.remote.response.CourseResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;

import java.util.ArrayList;
import java.util.List;

public class FakeAcademyRepository implements AcademyDataSource {

    private volatile static FakeAcademyRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    FakeAcademyRepository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public LiveData<List<CourseEntity>> getAllCourses() {
        MutableLiveData<List<CourseEntity>> courseResults = new MutableLiveData<>();
        remoteDataSource.getAllCourses(courseResponses -> {
            List<CourseEntity> courseList = new ArrayList<>();
            for (int i = 0; i < courseResponses.size(); i++) {
                CourseResponse response = courseResponses.get(i);
                CourseEntity course = new CourseEntity(response.getId(),
                        response.getTitle(),
                        response.getDescription(),
                        response.getDate(),
                        false,
                        response.getImagePath());

                courseList.add(course);
            }
            courseResults.postValue(courseList);
        });

        return courseResults;
    }

    @Override
    public LiveData<List<CourseEntity>> getBookmarkedCourses() {
        MutableLiveData<List<CourseEntity>> courseResults = new MutableLiveData<>();

        remoteDataSource.getAllCourses(courseResponses -> {
            ArrayList<CourseEntity> courseList = new ArrayList<>();
            for (int i = 0; i < courseResponses.size(); i++) {
                CourseResponse response = courseResponses.get(i);
                CourseEntity course = new CourseEntity(response.getId(),
                        response.getTitle(),
                        response.getDescription(),
                        response.getDate(),
                        false,
                        response.getImagePath());
                courseList.add(course);
            }
            courseResults.postValue(courseList);
        });

        return courseResults;
    }

    // Pada metode ini di modul selanjutnya akan mengembalikan kelas POJO baru, gabungan antara course dengan module-nya.
    @Override
    public LiveData<CourseEntity> getCourseWithModules(final String courseId) {
        MutableLiveData<CourseEntity> courseResult = new MutableLiveData<>();

        remoteDataSource.getAllCourses(courseResponses -> {
            for (int i = 0; i < courseResponses.size(); i++) {
                CourseResponse response = courseResponses.get(i);
                if (response.getId().equals(courseId)) {
                    CourseEntity course = new CourseEntity(response.getId(),
                            response.getTitle(),
                            response.getDescription(),
                            response.getDate(),
                            false,
                            response.getImagePath());
                    courseResult.postValue(course);
                }
            }
        });

        return courseResult;
    }

    @Override
    public LiveData<List<ModuleEntity>> getAllModulesByCourse(String courseId) {
        MutableLiveData<List<ModuleEntity>> moduleResults = new MutableLiveData<>();

        remoteDataSource.getModules(courseId, moduleResponses -> {
            ArrayList<ModuleEntity> moduleList = new ArrayList<>();
            for (int i = 0; i < moduleResponses.size(); i++) {
                ModuleResponse response = moduleResponses.get(i);
                ModuleEntity course = new ModuleEntity(response.getModuleId(),
                        response.getCourseId(),
                        response.getTitle(),
                        response.getPosition(),
                        false);

                moduleList.add(course);
            }
            moduleResults.postValue(moduleList);
        });

        return moduleResults;
    }


    @Override
    public LiveData<ModuleEntity> getContent(String courseId, String moduleId) {
        MutableLiveData<ModuleEntity> moduleResult = new MutableLiveData<>();

        remoteDataSource.getModules(courseId, moduleResponses -> {
            ModuleEntity module;
            for (int i = 0; i < moduleResponses.size(); i++) {
                ModuleResponse moduleResponse = moduleResponses.get(i);

                String id = moduleResponse.getModuleId();

                if (id.equals(moduleId)) {
                    module = new ModuleEntity(id, moduleResponse.getCourseId(), moduleResponse.getTitle(), moduleResponse.getPosition(), false);

                    remoteDataSource.getContent(moduleId, contentResponse -> {
                        module.contentEntity = new ContentEntity(contentResponse.getContent());
                        moduleResult.postValue(module);
                    });

                    break;
                }
            }
        });
        return moduleResult;
    }
}

