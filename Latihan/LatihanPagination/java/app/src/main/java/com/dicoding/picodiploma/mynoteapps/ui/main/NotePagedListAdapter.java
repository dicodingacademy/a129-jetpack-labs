package com.dicoding.picodiploma.mynoteapps.ui.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.picodiploma.mynoteapps.database.Note;
import com.dicoding.picodiploma.mynoteapps.databinding.ItemNoteBinding;
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity;

public class NotePagedListAdapter extends PagedListAdapter<Note, NotePagedListAdapter.NoteViewHolder> {
    private final Activity activity;

    NotePagedListAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public NotePagedListAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotePagedListAdapter.NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        final ItemNoteBinding binding;

        NoteViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(Note note) {
            binding.tvItemTitle.setText(note.getTitle());
            binding.tvItemDate.setText(note.getDate());
            binding.tvItemDescription.setText(note.getDescription());

            binding.cvItemNote.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), NoteAddUpdateActivity.class);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, getAdapterPosition());
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note);
                activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
            });
        }
    }

    private static DiffUtil.ItemCallback<Note> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Note>() {
                @Override
                public boolean areItemsTheSame(Note oldNote, Note newNote) {
                    return oldNote.getTitle().equals(newNote.getTitle()) && oldNote.getDescription().equals(newNote.getDescription());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Note oldNote, @NonNull Note newNote) {
                    return oldNote.equals(newNote);
                }
            };
}