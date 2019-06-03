package com.dicoding.picodiploma.mynoteapps.ui.main;

import android.app.Activity;
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
import com.dicoding.picodiploma.mynoteapps.helper.NoteDiffCallback;
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private final ArrayList<Note> listNotes = new ArrayList<>();
    private final Activity activity;

    NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    void setListNotes(List<Note> listNotes) {
        final NoteDiffCallback diffCallback = new NoteDiffCallback(this.listNotes, listNotes);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.listNotes.clear();
        this.listNotes.addAll(listNotes);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        holder.tvTitle.setText(listNotes.get(position).getTitle());
        holder.tvDate.setText(listNotes.get(position).getDate());
        holder.tvDescription.setText(listNotes.get(position).getDescription());
        holder.cvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NoteAddUpdateActivity.class);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, holder.getAdapterPosition());
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, listNotes.get(holder.getAdapterPosition()));
                activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
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
}