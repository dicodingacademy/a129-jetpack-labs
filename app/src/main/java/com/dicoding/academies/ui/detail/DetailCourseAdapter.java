package com.dicoding.academies.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.R;

import java.util.ArrayList;
import java.util.List;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder> {

    private List<ModuleEntity> mModules = new ArrayList<>();

    public void setModules(List<ModuleEntity> modules) {
        if (modules == null) return;
        mModules.clear();
        mModules.addAll(modules);
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder viewHolder, int position) {
        viewHolder.bind(mModules.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {
        final TextView textTitle;

        ModuleViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_module_title);
        }

        void bind(String title) {
            textTitle.setText(title);
        }
    }
}
