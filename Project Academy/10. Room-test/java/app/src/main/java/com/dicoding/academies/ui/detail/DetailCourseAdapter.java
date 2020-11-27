package com.dicoding.academies.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.databinding.ItemsModuleListBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder> {

    private List<ModuleEntity> listModules = new ArrayList<>();

    public void setModules(List<ModuleEntity> modules) {
        if (modules == null) return;
        listModules.clear();
        listModules.addAll(modules);
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsModuleListBinding binding = ItemsModuleListBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ModuleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder viewHolder, int position) {
        ModuleEntity module = listModules.get(position);
        viewHolder.bind(module);
    }

    @Override
    public int getItemCount() {
        return listModules.size();
    }

    static class ModuleViewHolder extends RecyclerView.ViewHolder {
        final ItemsModuleListBinding binding;

        ModuleViewHolder(ItemsModuleListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ModuleEntity module) {
            binding.textModuleTitle.setText(module.getTitle());
        }
    }
}