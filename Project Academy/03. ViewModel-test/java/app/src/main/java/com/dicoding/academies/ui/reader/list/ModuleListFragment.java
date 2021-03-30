package com.dicoding.academies.ui.reader.list;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dicoding.academies.data.ModuleEntity;
import com.dicoding.academies.databinding.FragmentModuleListBinding;
import com.dicoding.academies.ui.reader.CourseReaderActivity;
import com.dicoding.academies.ui.reader.CourseReaderCallback;
import com.dicoding.academies.ui.reader.CourseReaderViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

public class ModuleListFragment extends Fragment implements MyAdapterClickListener {

    public static final String TAG = ModuleListFragment.class.getSimpleName();

    private CourseReaderViewModel viewModel;

    private FragmentModuleListBinding fragmentModuleListBinding;
    private ModuleListAdapter adapter;
    private CourseReaderCallback courseReaderCallback;

    public ModuleListFragment() {
        // Required empty public constructor
    }

    public static ModuleListFragment newInstance() {
        return new ModuleListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentModuleListBinding = FragmentModuleListBinding.inflate(inflater, container, false);
        return fragmentModuleListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            viewModel = new ViewModelProvider(requireActivity(), new ViewModelProvider.NewInstanceFactory()).get(CourseReaderViewModel.class);
            adapter = new ModuleListAdapter(this);
            populateRecyclerView(viewModel.getModules());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        courseReaderCallback = ((CourseReaderActivity) context);
    }

    @Override
    public void onItemClicked(int position, String moduleId) {
        courseReaderCallback.moveTo(position, moduleId);
        viewModel.setSelectedModule(moduleId);
    }

    private void populateRecyclerView(List<ModuleEntity> modules) {
        fragmentModuleListBinding.progressBar.setVisibility(View.GONE);
        adapter.setModules(modules);
        fragmentModuleListBinding.rvModule.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentModuleListBinding.rvModule.setHasFixedSize(true);
        fragmentModuleListBinding.rvModule.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        fragmentModuleListBinding.rvModule.addItemDecoration(dividerItemDecoration);
    }
}

