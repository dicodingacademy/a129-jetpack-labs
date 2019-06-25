package com.dicoding.academies.data;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.CourseWithModule;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.data.source.local.room.AcademyDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.dicoding.academies.utils.FakeDataDummy.generateDummyCourses;
import static com.dicoding.academies.utils.FakeDataDummy.generateDummyModules;
import static com.dicoding.academies.utils.LiveDataTestUtil.getValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LocalDatabaseTest {

    private AcademyDatabase db;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {

        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
                AcademyDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void insertAllCoursesTest() {

        List<CourseEntity> expectedCourses = generateDummyCourses(false);
        db.academyDao().insertCourses(expectedCourses);

        List<CourseEntity> actualCourses = getValue(db.academyDao().getCourses());

        assertEquals(expectedCourses.size(), actualCourses.size());
    }

    @Test
    public void insertAllModulesTest() {

        List<CourseEntity> expectedCourse = generateDummyCourses(false);
        db.academyDao().insertCourses(expectedCourse);

        int randomIndex;
        Random r = new Random();
        randomIndex = r.nextInt(expectedCourse.size());

        List<ModuleEntity> expectedModules = generateDummyModules(expectedCourse.get(randomIndex).getCourseId());
        db.academyDao().insertModules(expectedModules);

        List<ModuleEntity> actualModules = getValue(db.academyDao().getModulesByCourseId(expectedCourse.get(randomIndex).getCourseId()));

        assertEquals(expectedModules.size(), actualModules.size());
    }

    @Test
    public void getCourseWithModuleTest() {

        List<CourseEntity> expectedCourse = new ArrayList<>();
        expectedCourse.add(generateDummyCourses(false).get(0));
        List<ModuleEntity> expectedModules = generateDummyModules(expectedCourse.get(0).getCourseId());

        db.academyDao().insertCourses(expectedCourse);
        db.academyDao().insertModules(expectedModules);

        CourseWithModule actualCourseWithModule = getValue(db
                .academyDao()
                .getCourseWithModuleById(expectedCourse.get(0).getCourseId()));

        assertNotNull(actualCourseWithModule);
        assertNotNull(actualCourseWithModule.mCourse);
        assertNotNull(actualCourseWithModule.mModules);
        assertEquals(expectedCourse.get(0).getCourseId(), actualCourseWithModule.mCourse.getCourseId());
        assertEquals(expectedModules.size(), actualCourseWithModule.mModules.size());
    }

    @Test
    public void courseBookmarkTest() {

        // Insert course
        List<CourseEntity> insertedCourses = generateDummyCourses(false);
        db.academyDao().insertCourses(insertedCourses);

        CourseEntity expectedCourses = getValue(db.academyDao().getCourses()).get(0);

        // Tes untuk bookmarked
        expectedCourses.setBookmarked(true);
        db.academyDao().updateCourse(expectedCourses);

        CourseWithModule actualCourse = getValue(db.academyDao()
                .getCourseWithModuleById(expectedCourses.getCourseId()));

        assertTrue(actualCourse.mCourse.isBookmarked());


        // Tes untuk unbookmarked
        expectedCourses.setBookmarked(false);
        db.academyDao().updateCourse(expectedCourses);

        actualCourse = getValue(db.academyDao()
                .getCourseWithModuleById(expectedCourses.getCourseId()));

        assertFalse(actualCourse.mCourse.isBookmarked());

    }

    @Test
    public void updateModuleWithContentTest() {

        // Pertama insert course
        List<CourseEntity> expectedCourse = generateDummyCourses(false);
        db.academyDao().insertCourses(expectedCourse);

        int randomIndex;
        Random r = new Random();
        randomIndex = r.nextInt(expectedCourse.size());

        // Kedua insert modules
        List<ModuleEntity> expectedModules = generateDummyModules(expectedCourse.get(randomIndex).getCourseId());
        db.academyDao().insertModules(expectedModules);

        // Content yang akan dimasukkan
        String expectedContent = "Lorem ipsum dolor sit amet";

        int randomIndex2;
        randomIndex2 = r.nextInt(expectedModules.size());

        // Ambil random module
        ModuleEntity expectedModule = expectedModules.get(randomIndex2);

        db.academyDao().updateModuleByContent(expectedContent, expectedModule.getModuleId());

        ModuleEntity actualModule = getValue(db.academyDao().getModuleById(expectedModule.getModuleId()));

        assertNotNull(actualModule.contentEntity);
        assertEquals(expectedContent, actualModule.contentEntity.getContent());
    }
}
