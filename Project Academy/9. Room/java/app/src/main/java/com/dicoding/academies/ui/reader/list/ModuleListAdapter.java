package com.dicoding.academies.ui.reader.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;

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
        if (viewHolder.getItemViewType() == 0){
            viewHolder.textTitle.setTextColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorTextSecondary));
        } else {
            viewHolder.itemView.setOnClickListener(v ->
                    listener.onItemClicked(viewHolder.getAdapterPosition(), listModules.get(viewHolder.getAdapterPosition()).getModuleId())
            );
        }
    }

    @Override
    public int getItemCount() {
        return listModules.size();
    }

    @Override
    public int getItemViewType(int position) {

        int modulePosition = listModules.get(position).getPosition();
        if (modulePosition == 0) return 1;
        else if (listModules.get(modulePosition - 1).isRead()) return 1;
        else return 0;

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

