package com.dicoding.picodiploma.mynoteapps.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.mynoteapps.R
import com.dicoding.picodiploma.mynoteapps.database.Note
import com.dicoding.picodiploma.mynoteapps.databinding.ItemNoteBinding
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity

class NotePagedListAdapter internal constructor(private val activity: Activity) : PagedListAdapter<Note, NotePagedListAdapter.NoteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        val binding = ItemNoteBinding.bind(view)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position) as Note)
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note){
            with(binding){
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Note> = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldNote: Note, newNote: Note): Boolean {
                return oldNote.title == newNote.title && oldNote.description == newNote.description
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: Note, newNote: Note): Boolean {
                return oldNote == newNote
            }
        }
    }
}