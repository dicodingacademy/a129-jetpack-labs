package com.dicoding.picodiploma.mynoteapps.ui.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.paging.PagedListAdapter;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.picodiploma.mynoteapps.R;
import com.dicoding.picodiploma.mynoteapps.database.Note;
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity;

public class NotePagedListAdapter extends PagedListAdapter<Note, NotePagedListAdapter.NoteViewHolder> {
    private final Activity activity;

    NotePagedListAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        final Note note = getItem(position);
        if (note != null) {
            holder.tvTitle.setText(note.getTitle());
            holder.tvDate.setText(note.getDate());
            holder.tvDescription.setText(note.getDescription());
            holder.cvNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, NoteAddUpdateActivity.class);
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, holder.getAdapterPosition());
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note);
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
                }
            });
        }
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDescription, tvDate;
        final CardView cvNote;

        NoteViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }

    private static DiffUtil.ItemCallback<Note> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Note>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Note oldNote, Note newNote) {
                    return oldNote.getTitle().equals(newNote.getTitle());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Note oldNote, @NonNull Note newNote) {
                    return oldNote.equals(newNote);
                }
            };
}
