package com.dicoding.academies.ui.reader.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.academies.R;
import com.dicoding.academies.data.ModuleEntity;

import java.util.ArrayList;
import java.util.List;

interface MyAdapterClickListener {
    void onItemClicked(int position, String moduleId);
}

public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder> {

    private final MyAdapterClickListener listener;
    private List<ModuleEntity> listModules = new ArrayList<>();

    ModuleListAdapter(MyAdapterClickListener listener) {
        this.listener = listener;
    }

    void setModules(List<ModuleEntity> listModules) {
        if (listModules == null) return;
        this.listModules.clear();
        this.listModules.addAll(listModules);
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list_custom, parent, false));
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder viewHolder, int position) {
        ModuleEntity module = listModules.get(position);
        viewHolder.bind(module);
        viewHolder.itemView.setOnClickListener(v ->
                listener.onItemClicked(viewHolder.getAdapterPosition(), listModules.get(viewHolder.getAdapterPosition()).getModuleId())
        );
    }

    @Override
    public int getItemCount() {
        return listModules.size();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder {
        final TextView textTitle;
        final TextView textLastSeen;

        ModuleViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_module_title);
            textLastSeen = itemView.findViewById(R.id.text_last_seen);
        }

        void bind(ModuleEntity module) {
            textTitle.setText(module.getTitle());
        }
    }
}

