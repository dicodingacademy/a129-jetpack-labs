package com.dicoding.academies.data;

public class ModuleEntity {
    public ContentEntity contentEntity;
    private String mModuleId;
    private String mCourseId;
    private String mTitle;
    private Integer mPosition;
    private boolean mRead = false;

    public ModuleEntity(String moduleId, String courseId, String title, Integer position, Boolean read) {
        this.mModuleId = moduleId;
        this.mCourseId = courseId;
        this.mTitle = title;
        this.mPosition = position;

        if (read != null) {
            this.mRead = read;
        }
    }

    public String getModuleId() {
        return mModuleId;
    }

    public void setModuleId(String moduleId) {
        this.mModuleId = moduleId;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public void setCourseid(String courseId) {
        this.mCourseId = courseId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Integer getPosition() {
        return mPosition;
    }

    public void setPosition(Integer position) {
        this.mPosition = position;
    }

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean read) {
        this.mRead = read;
    }
}

