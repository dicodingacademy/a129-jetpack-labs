package com.dicoding.academies.data.source;

import androidx.annotation.NonNull;

import com.dicoding.academies.data.source.local.LocalRepository;
import com.dicoding.academies.data.source.local.entity.ContentEntity;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.remote.RemoteRepository;
import com.dicoding.academies.data.source.remote.response.CourseResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;

import java.util.ArrayList;
import java.util.List;

public class AcademyRepository implements AcademyDataSource {

    private volatile static AcademyRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    private AcademyRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public static AcademyRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (AcademyRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AcademyRepository(localRepository, remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public ArrayList<CourseEntity> getAllCourses() {
        List<CourseResponse> courseResponses = remoteRepository.getAllCourses();
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
        return courseList;
    }

    // Pada metode ini di modul selanjutnya akan mengembalikan kelas POJO baru, gabungan antara course dengan module-nya.
    @Override
    public CourseEntity getCourseWithModules(final String courseId) {
        CourseEntity course = null;
        List<CourseResponse> courses = remoteRepository.getAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            CourseResponse response = courses.get(i);
            if (response.getId().equals(courseId)) {
                course = new CourseEntity(response.getId(),
                        response.getTitle(),
                        response.getDescription(),
                        response.getDate(),
                        false,
                        response.getImagePath());
            }
        }
        return course;
    }


    @Override
    public ArrayList<CourseEntity> getBookmarkedCourses() {
        ArrayList<CourseEntity> courseList = new ArrayList<>();
        List<CourseResponse> courses = remoteRepository.getAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            CourseResponse response = courses.get(i);
            CourseEntity course = new CourseEntity(response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getDate(),
                    false,
                    response.getImagePath());
            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public ArrayList<ModuleEntity> getAllModulesByCourse(String courseId) {
        ArrayList<ModuleEntity> moduleList = new ArrayList<>();
        List<ModuleResponse> moduleResponses = remoteRepository.getModules(courseId);
        for (int i = 0; i < moduleResponses.size(); i++) {
            ModuleResponse response = moduleResponses.get(i);
            ModuleEntity course = new ModuleEntity(response.getModuleId(),
                    response.getCourseId(),
                    response.getTitle(),
                    response.getPosition(),
                    false);

            moduleList.add(course);
        }

        return moduleList;
    }


    @Override
    public ModuleEntity getContent(String courseId, String moduleId) {
        List<ModuleResponse> moduleResponses = remoteRepository.getModules(courseId);

        ModuleEntity module = null;
        for (int i = 0; i < moduleResponses.size(); i++) {
            ModuleResponse moduleResponse = moduleResponses.get(i);

            String id = moduleResponse.getModuleId();

            if (id.equals(moduleId)) {
                module = new ModuleEntity(id, moduleResponse.getCourseId(), moduleResponse.getTitle(), moduleResponse.getPosition(), false);

                module.contentEntity = new ContentEntity(remoteRepository.getContent(moduleId).getContent());
                break;
            }
        }

        return module;
    }
}

