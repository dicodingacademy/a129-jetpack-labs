package com.dicoding.academies.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.databinding.ActivityDetailCourseBinding;
import com.dicoding.academies.databinding.ContentDetailCourseBinding;
import com.dicoding.academies.ui.reader.CourseReaderActivity;
import com.dicoding.academies.viewmodel.ViewModelFactory;

public class DetailCourseActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE = "extra_course";
    private ContentDetailCourseBinding contentDetailCourseBinding;
    private ActivityDetailCourseBinding activityDetailCourseBinding;

    DetailCourseViewModel viewModel;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(getLayoutInflater());
        contentDetailCourseBinding = activityDetailCourseBinding.detailContent;

        setContentView(activityDetailCourseBinding.getRoot());

        setSupportActionBar(activityDetailCourseBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DetailCourseAdapter adapter = new DetailCourseAdapter();
        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(this, factory).get(DetailCourseViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String courseId = extras.getString(EXTRA_COURSE);
            if (courseId != null) {
                viewModel.setCourseId(courseId);

                viewModel.courseModule.observe(this, courseWithModuleResource -> {
                    if (courseWithModuleResource != null) {

                        switch (courseWithModuleResource.status) {
                            case LOADING:
                                activityDetailCourseBinding.progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                if (courseWithModuleResource.data != null) {
                                    activityDetailCourseBinding.progressBar.setVisibility(View.GONE);
                                    activityDetailCourseBinding.content.setVisibility(View.VISIBLE);
                                    adapter.setModules(courseWithModuleResource.data.mModules);
                                    adapter.notifyDataSetChanged();
                                    populateCourse(courseWithModuleResource.data.mCourse);
                                }
                                break;
                            case ERROR:
                                activityDetailCourseBinding.progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        viewModel.courseModule.observe(this, courseWithModule -> {
            if (courseWithModule != null) {
                switch (courseWithModule.status) {
                    case LOADING:
                        activityDetailCourseBinding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        if (courseWithModule.data != null) {
                            activityDetailCourseBinding.progressBar.setVisibility(View.GONE);
                            boolean state = courseWithModule.data.mCourse.isBookmarked();
                            setBookmarkState(state);
                        }
                        break;
                    case ERROR:
                        activityDetailCourseBinding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_bookmark) {
            viewModel.setBookmark();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentDetailCourseBinding = null;
        activityDetailCourseBinding = null;
    }

    private void setBookmarkState(boolean state) {
        if (menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_bookmark);
        if (state) {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white));
        }
    }
}