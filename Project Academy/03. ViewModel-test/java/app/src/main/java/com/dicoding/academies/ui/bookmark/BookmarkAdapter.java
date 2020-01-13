package com.dicoding.academies.ui.bookmark;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.academies.R;
import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.ui.detail.DetailCourseActivity;

import java.util.ArrayList;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.CourseViewHolder> {
    private final BookmarkFragmentCallback callback;
    private ArrayList<CourseEntity> listCourses = new ArrayList<>();

    BookmarkAdapter(BookmarkFragmentCallback callback) {
        this.callback = callback;
    }


    public void setCourses(List<CourseEntity> courses) {
        if (courses == null) return;
        this.listCourses.clear();
        this.listCourses.addAll(courses);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookmark, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, int position) {
        CourseEntity course = listCourses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return listCourses.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageButton imgShare;
        final ImageView imgPoster;

        CourseViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        void bind(CourseEntity course) {
            tvTitle.setText(course.getTitle());
            tvDescription.setText(course.getDescription());
            tvDate.setText(String.format("Deadline %s", course.getDeadline()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailCourseActivity.class);
                intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.getCourseId());
                itemView.getContext().startActivity(intent);
            });
            imgShare.setOnClickListener(v -> callback.onShareClick(course));
            Glide.with(itemView.getContext())
                    .load(course.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}

