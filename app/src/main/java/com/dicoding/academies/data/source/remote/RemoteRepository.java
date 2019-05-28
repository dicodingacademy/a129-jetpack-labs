package com.dicoding.academies.data.source.remote;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.academies.data.source.remote.response.ContentResponse;
import com.dicoding.academies.data.source.remote.response.CourseResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;
import com.dicoding.academies.utils.EspressoIdlingResource;
import com.dicoding.academies.utils.JsonHelper;

import java.util.List;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<CourseResponse>>> getAllCoursesAsLiveData() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<CourseResponse>>> resultCourse = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.success(jsonHelper.loadCourses()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultCourse;
    }

    public LiveData<ApiResponse<List<ModuleResponse>>> getAllModulesByCourseAsLiveData(String courseId) {

        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<ModuleResponse>>> resultModules = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultModules.setValue(ApiResponse.success(jsonHelper.loadModule(courseId)));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultModules;
    }

    public LiveData<ApiResponse<ContentResponse>> getContentAsLiveData(String moduleId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<ContentResponse>> resultContent = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultContent.setValue(ApiResponse.success(jsonHelper.loadContent(moduleId)));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultContent;
    }

}