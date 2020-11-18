package com.dicoding.academies.ui.detail;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.databinding.ActivityDetailCourseBinding;
import com.dicoding.academies.databinding.ContentDetailCourseBinding;
import com.dicoding.academies.ui.reader.CourseReaderActivity;
import com.dicoding.academies.viewmodel.ViewModelFactory;

import java.util.List;

public class DetailCourseActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE = "extra_course";
    private ContentDetailCourseBinding contentDetailCourseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailCourseBinding activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(getLayoutInflater());
        contentDetailCourseBinding = activityDetailCourseBinding.detailContent;

        setContentView(activityDetailCourseBinding.getRoot());

        setSupportActionBar(activityDetailCourseBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DetailCourseAdapter adapter = new DetailCourseAdapter();

        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        DetailCourseViewModel viewModel = new ViewModelProvider(this, factory).get(DetailCourseViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String courseId = extras.getString(EXTRA_COURSE);
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId);
                List<ModuleEntity> modules = viewModel.getModules();
                adapter.setModules(modules);
                populateCourse(viewModel.getCourse());

            }
        }

        contentDetailCourseBinding.rvModule.setNestedScrollingEnabled(false);
        contentDetailCourseBinding.rvModule.setLayoutManager(new LinearLayoutManager(this));
        contentDetailCourseBinding.rvModule.setHasFixedSize(true);
        contentDetailCourseBinding.rvModule.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(contentDetailCourseBinding.rvModule.getContext(), DividerItemDecoration.VERTICAL);
        contentDetailCourseBinding.rvModule.addItemDecoration(dividerItemDecoration);
    }

    private void populateCourse(CourseEntity courseEntity) {
        contentDetailCourseBinding.textTitle.setText(courseEntity.getTitle());
        contentDetailCourseBinding.textDescription.setText(courseEntity.getDescription());
        contentDetailCourseBinding.textDate.setText(String.format("Deadline %s", courseEntity.getDeadline()));

        Glide.with(this)
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(contentDetailCourseBinding.imagePoster);

        contentDetailCourseBinding.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.getCourseId());
            startActivity(intent);
        });
    }
}