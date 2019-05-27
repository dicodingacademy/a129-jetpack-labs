package com.dicoding.academies.ui.academy;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.ui.detail.DetailCourseActivity;
import com.dicoding.academies.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class AcademyAdapter extends RecyclerView.Adapter<AcademyAdapter.AcademyViewHolder> {
    private final Activity activity;
    private List<CourseEntity> mCourses = new ArrayList<>();

    AcademyAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<CourseEntity> getListCourses() {
        return mCourses;
    }

    void setListCourses(List<CourseEntity> listCourses) {
        if (listCourses == null) return;
        this.mCourses.clear();
        this.mCourses.addAll(listCourses);
    }

    @NonNull
    @Override
    public AcademyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_academy, parent, false);
        return new AcademyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AcademyViewHolder holder, int position) {
        holder.tvTitle.setText(getListCourses().get(position).getTitle());
        holder.tvDescription.setText(getListCourses().get(position).getDescription());
        holder.tvDate.setText(String.format("Deadline %s", getListCourses().get(position).getDeadline()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailCourseActivity.class);
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, getListCourses().get(position).getCourseId());
            activity.startActivity(intent);
        });
        GlideApp.with(holder.itemView.getContext())
                .load(getListCourses().get(position).getImagePath())
                .apply(new RequestOptions().override(60,60).placeholder(R.drawable.ic_refresh_black)
                        .error(R.drawable.ic_broken_image_black))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListCourses().size();
    }

    class AcademyViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageView imgPoster;

        AcademyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
        }
    }
}

