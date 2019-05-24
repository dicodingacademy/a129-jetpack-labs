package com.dicoding.academies.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.academies.data.source.local.LocalRepository;
import com.dicoding.academies.data.source.local.entity.ContentEntity;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.remote.RemoteRepository;
import com.dicoding.academies.data.source.remote.response.ContentResponse;
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
    public LiveData<List<CourseEntity>> getAllCourses() {
        MutableLiveData<List<CourseEntity>> courseResults = new MutableLiveData<>();

        remoteRepository.getAllCourses(new RemoteRepository.LoadCoursesCallback() {
            @Override
            public void onAllCoursesReceived(List<CourseResponse> courseResponses) {
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
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return courseResults;
    }

    // Pada metode ini di modul selanjutnya akan mengembalikan kelas POJO baru, gabungan antara course dengan module-nya.
    @Override
    public LiveData<CourseEntity> getCourseWithModules(final String courseId) {
        MutableLiveData<CourseEntity> courseResult = new MutableLiveData<>();

        remoteRepository.getAllCourses(new RemoteRepository.LoadCoursesCallback() {
            @Override
            public void onAllCoursesReceived(List<CourseResponse> courseResponses) {
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
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return courseResult;
    }


    @Override
    public LiveData<List<CourseEntity>> getBookmarkedCourses() {
        MutableLiveData<List<CourseEntity>> courseResults = new MutableLiveData<>();

        remoteRepository.getAllCourses(new RemoteRepository.LoadCoursesCallback() {
            @Override
            public void onAllCoursesReceived(List<CourseResponse> courseResponses) {
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
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return courseResults;
    }

    @Override
    public LiveData<List<ModuleEntity>> getAllModulesByCourse(String courseId) {
        MutableLiveData<List<ModuleEntity>> moduleResults= new MutableLiveData<>();

        remoteRepository.getModules(courseId, new RemoteRepository.LoadModulesCallback() {
            @Override
            public void onAllModulesReceived(List<ModuleResponse> moduleResponses) {
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
            }

            @Override
            public void onDataNotAvailable() {

            }
        });


        return moduleResults;
    }


    @Override
    public LiveData<ModuleEntity> getContent(String courseId, String moduleId) {
        MutableLiveData<ModuleEntity> moduleResult = new MutableLiveData<>();
        remoteRepository.getModules(courseId, new RemoteRepository.LoadModulesCallback() {
            @Override
            public void onAllModulesReceived(List<ModuleResponse> moduleResponses) {
                ModuleEntity module;
                for (int i = 0; i < moduleResponses.size(); i++) {
                    ModuleResponse moduleResponse = moduleResponses.get(i);

                    String id = moduleResponse.getModuleId();

                    if (id.equals(moduleId)) {
                        module = new ModuleEntity(id, moduleResponse.getCourseId(), moduleResponse.getTitle(), moduleResponse.getPosition(), false);

                        remoteRepository.getContent(moduleId, new RemoteRepository.GetContentCallback() {
                            @Override
                            public void onContentReceived(ContentResponse contentResponse) {
                                module.contentEntity = new ContentEntity(contentResponse.getContent());
                                moduleResult.postValue(module);
                            }

                            @Override
                            public void onDataNotAvailable() {

                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        return moduleResult;
    }
}
