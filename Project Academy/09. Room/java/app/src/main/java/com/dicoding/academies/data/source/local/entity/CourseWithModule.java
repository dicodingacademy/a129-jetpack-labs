package com.dicoding.academies.data.source.local.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CourseWithModule {
    @Embedded
    public CourseEntity mCourse;

    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    public List<ModuleEntity> mModules;
}