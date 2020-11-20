package com.dicoding.academies.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.academies.data.source.local.entity.ModuleEntity
import com.dicoding.academies.databinding.ItemsModuleListCustomBinding
import java.util.*

class ModuleListAdapter internal constructor(private val listener: MyAdapterClickListener) : RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>() {
    private val listModules = ArrayList<ModuleEntity>()

    internal fun setModules(modules: List<ModuleEntity>?) {
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val binding = ItemsModuleListCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ModuleViewHolder, position: Int) {
        val module = listModules[position]
        viewHolder.bind(module)
        viewHolder.itemView.setOnClickListener {
            listener.onItemClicked(viewHolder.adapterPosition, listModules[viewHolder.adapterPosition].moduleId)
        }
    }

    override fun getItemCount(): Int = listModules.size

    inner class ModuleViewHolder(private val binding: ItemsModuleListCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }
}

internal interface MyAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String)
}