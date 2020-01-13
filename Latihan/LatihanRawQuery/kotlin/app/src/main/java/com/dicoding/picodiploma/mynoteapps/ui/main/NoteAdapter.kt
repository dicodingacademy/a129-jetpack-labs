package com.dicoding.picodiploma.mynoteapps.ui.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.mynoteapps.R
import com.dicoding.picodiploma.mynoteapps.database.Note
import com.dicoding.picodiploma.mynoteapps.helper.NoteDiffCallback
import com.dicoding.picodiploma.mynoteapps.ui.insert.NoteAddUpdateActivity
import com.dicoding.picodiploma.mynoteapps.ui.main.NoteAdapter.NoteViewHolder
import kotlinx.android.synthetic.main.item_note.view.*
import java.util.*

class NoteAdapter internal constructor(private val activity: Activity) : RecyclerView.Adapter<NoteViewHolder>() {
    private val listNotes = ArrayList<Note>()
    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note){
            with(itemView){
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description
                cv_item_note.setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }
}