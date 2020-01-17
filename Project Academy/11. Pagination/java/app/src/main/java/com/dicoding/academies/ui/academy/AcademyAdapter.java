package com.dicoding.academies.ui.academy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.ui.detail.DetailCourseActivity;

public class AcademyAdapter extends PagedListAdapter<CourseEntity, AcademyAdapter.CourseViewHolder> {

    AcademyAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<CourseEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CourseEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull CourseEntity oldItem, @NonNull CourseEntity newItem) {
                    return oldItem.getCourseId().equals(newItem.getCourseId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull CourseEntity oldItem, @NonNull CourseEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

//    hapus kode di bawah ini
//    private List<CourseEntity> listCourses = new ArrayList<>();
//
//    void setCourses(List<CourseEntity> listCourses) {
//        if (listCourses == null) return;
//        this.listCourses.clear();
//        this.listCourses.addAll(listCourses);
//    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_academy, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, int position) {
        CourseEntity course = getItem(position);
        if (course != null) {
            holder.bind(course);
        }
    }

//    @Override
//    public int getItemCount() {
//        return listCourses.size();
//    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        CourseViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }

        void bind(CourseEntity course) {
            tvTitle.setText(course.getTitle());
            tvDescription.setText(course.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.deadline_date, course.getDeadline()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailCourseActivity.class);
                intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.getCourseId());
                itemView.getContext().startActivity(intent);
            });
            Glide.with(itemView.getContext())
                    .load(course.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}

