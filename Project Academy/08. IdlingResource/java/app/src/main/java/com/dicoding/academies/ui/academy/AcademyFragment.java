package com.dicoding.academies.ui.academy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dicoding.academies.databinding.FragmentAcademyBinding;
import com.dicoding.academies.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcademyFragment extends Fragment {

    private FragmentAcademyBinding fragmentAcademyBinding;

    public AcademyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAcademyBinding = FragmentAcademyBinding.inflate(inflater);
        return fragmentAcademyBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            AcademyViewModel viewModel = new ViewModelProvider(this, factory).get(AcademyViewModel.class);

            AcademyAdapter academyAdapter = new AcademyAdapter();

            fragmentAcademyBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getCourses().observe(this, courses -> {
                        fragmentAcademyBinding.progressBar.setVisibility(View.GONE);
                        academyAdapter.setCourses(courses);
                        academyAdapter.notifyDataSetChanged();
                    }
            );

            fragmentAcademyBinding.rvAcademy.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentAcademyBinding.rvAcademy.setHasFixedSize(true);
            fragmentAcademyBinding.rvAcademy.setAdapter(academyAdapter);
        }
    }
}