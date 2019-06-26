package com.dicoding.academies.ui.detail;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DetailCourseViewModelTest {
    private DetailCourseViewModel viewModel;
    private CourseEntity dummyCourse;

    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel();
        dummyCourse = new CourseEntity("a14",
                "Menjadi Android Developer Expert",
                "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
                "100 Hari",
                false,
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getCourse() {
        viewModel.setCourseId(dummyCourse.getCourseId());
        CourseEntity courseEntity = viewModel.getCourse();
        assertNotNull(courseEntity);
        assertEquals(dummyCourse.getCourseId(), courseEntity.getCourseId());
        assertEquals(dummyCourse.getDeadline(), courseEntity.getDeadline());
        assertEquals(dummyCourse.getDescription(), courseEntity.getDescription());
        assertEquals(dummyCourse.getImagePath(), courseEntity.getImagePath());
        assertEquals(dummyCourse.getTitle(), courseEntity.getTitle());
    }

    @Test
    public void getModules() {
        viewModel.setCourseId(dummyCourse.getCourseId());
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }

}