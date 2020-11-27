package com.dicoding.academies.ui.academy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.databinding.ItemsAcademyBinding;
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

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsAcademyBinding binding = ItemsAcademyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseViewHolder holder, int position) {
        CourseEntity course = getItem(position);
        if (course != null) {
            holder.bind(course);
        }
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {

        final ItemsAcademyBinding binding;

        CourseViewHolder(ItemsAcademyBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(CourseEntity course) {
            binding.tvItemTitle.setText(course.getTitle());
            binding.tvItemDate.setText(String.format("Deadline %s", course.getDeadline()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailCourseActivity.class);
                intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.getCourseId());
                itemView.getContext().startActivity(intent);
            });
            Glide.with(itemView.getContext())
                    .load(course.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.imgPoster);
        }
    }
}

