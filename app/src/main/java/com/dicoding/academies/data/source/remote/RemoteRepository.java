package com.dicoding.academies.data.source.remote;

import com.dicoding.academies.data.source.remote.response.ContentResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;

import java.util.List;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;

    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(helper);
        }
        return INSTANCE;
    }

    public void getAllCourses(LoadCoursesCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onAllCoursesReceived(jsonHelper.loadCourses()), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getModules(String courseId, LoadModulesCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onAllModulesReceived(jsonHelper.loadModule(courseId)), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getContent(String moduleId, GetContentCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onContentReceived(jsonHelper.loadContent(moduleId)), SERVICE_LATENCY_IN_MILLIS);
    }

    public List<ModuleResponse> getModules(String courseId) {
        return jsonHelper.loadModule(courseId);
    }

    public ContentResponse getContent(String moduleId) {
        return jsonHelper.loadContent(moduleId);
    }

}
