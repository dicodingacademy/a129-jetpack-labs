package com.dicoding.academies.data.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "moduleentities",
        primaryKeys = {"moduleId", "courseId"},
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "courseId",
                childColumns = "courseId"),
        indices = {@Index(value = "moduleId"),
                @Index(value = "courseId")}
)
public class ModuleEntity {
    @Embedded
    public ContentEntity contentEntity;

    @NonNull
    @ColumnInfo(name = "moduleId")
    private String mModuleId;

    @NonNull
    @ColumnInfo(name = "courseId")
    private String mCourseId;

    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name = "position")
    private Integer mPosition;

    @ColumnInfo(name = "read")
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

