package com.dicoding.academies.ui.bookmark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.ui.detail.DetailCourseActivity;
import com.dicoding.academies.utils.GlideApp;

public class BookmarkPagedAdapter extends PagedListAdapter<CourseEntity, BookmarkPagedAdapter.BookmarkViewHolder> {

    private BookmarkFragmentCallback callback;

    BookmarkPagedAdapter(BookmarkFragmentCallback callback) {
        super(DIFF_CALLBACK);

        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookmarkViewHolder holder, int position) {
        final CourseEntity bookmark = getItem(position);
        if (bookmark != null) {
            holder.tvTitle.setText(bookmark.getTitle());
            holder.tvDate.setText(String.format("Deadline %s", bookmark.getDeadline()));
            holder.tvDescription.setText(bookmark.getDescription());
            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailCourseActivity.class);
                String courseId = bookmark.getCourseId();
                intent.putExtra(DetailCourseActivity.EXTRA_COURSE, courseId);
                context.startActivity(intent);
            });

            holder.imgShare.setOnClickListener(v -> {
                CourseEntity course = new CourseEntity(bookmark.getCourseId(),
                        bookmark.getTitle(),
                        bookmark.getDescription(),
                        bookmark.getDeadline(),
                        false,
                        bookmark.getImagePath());
                callback.onShareClick(course);
            });

            GlideApp.with(holder.itemView.getContext()).load(bookmark.getImagePath()).into(holder.imgPoster);

        }
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookmark, parent, false);
        return new BookmarkViewHolder(view);
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

    CourseEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    class BookmarkViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageButton imgShare;
        final ImageView imgPoster;

        BookmarkViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }

}
