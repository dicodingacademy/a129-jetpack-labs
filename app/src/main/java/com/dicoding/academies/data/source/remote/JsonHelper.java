package com.dicoding.academies.data.source.remote;

import android.app.Application;

import com.dicoding.academies.data.source.remote.response.ContentResponse;
import com.dicoding.academies.data.source.remote.response.ModuleResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private Application application;

    public JsonHelper(Application application) {
        this.application = application;
    }

    private String parsingFileToString(String fileName) {
        try {
            InputStream is = application.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<com.dicoding.academies.data.source.remote.CourseResponse> loadCourses() {
        ArrayList<com.dicoding.academies.data.source.remote.CourseResponse> list = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(parsingFileToString("CourseResponses.json"));
            JSONArray listArray = responseObject.getJSONArray("courses");
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject course = listArray.getJSONObject(i);

                String id = course.getString("id");
                String title = course.getString("title");
                String description = course.getString("description");
                String date = course.getString("date");
                String imagePath = course.getString("imagePath");

                com.dicoding.academies.data.source.remote.CourseResponse courseResponse = new com.dicoding.academies.data.source.remote.CourseResponse(id, title, description, date, imagePath);
                list.add(courseResponse);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ModuleResponse> loadModule(String courseId) {
        String fileName = String.format("Module_%s.json", courseId);
        ArrayList<ModuleResponse> list = new ArrayList<>();
        try {

            String result = parsingFileToString(fileName);
            if (result != null) {
                JSONObject responseObject = new JSONObject(result);
                JSONArray listArray = responseObject.getJSONArray("modules");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject course = listArray.getJSONObject(i);

                    String moduleId = course.getString("moduleId");
                    String title = course.getString("title");
                    String position = course.getString("position");

                    ModuleResponse courseResponse = new ModuleResponse(moduleId, courseId, title, Integer.parseInt(position));
                    list.add(courseResponse);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


    public ContentResponse loadContent(String moduleId) {
        String fileName = String.format("Content_%s.json", moduleId);
        ContentResponse contentResponse = null;
        try {

            String result = parsingFileToString(fileName);
            if (result != null) {
                JSONObject responseObject = new JSONObject(result);

                String content = responseObject.getString("content");

                contentResponse = new ContentResponse(moduleId, content);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return contentResponse;
    }
}

