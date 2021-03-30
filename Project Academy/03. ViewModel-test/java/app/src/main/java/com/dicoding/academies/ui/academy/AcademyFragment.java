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

import com.dicoding.academies.data.CourseEntity;
import com.dicoding.academies.databinding.FragmentAcademyBinding;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AcademyFragment extends Fragment {

    private FragmentAcademyBinding fragmentAcademyBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentAcademyBinding = FragmentAcademyBinding.inflate(inflater, container, false);
        return fragmentAcademyBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            AcademyViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(AcademyViewModel.class);
            List<CourseEntity> courses = viewModel.getCourses();

            AcademyAdapter academyAdapter = new AcademyAdapter();
            academyAdapter.setCourses(courses);

            fragmentAcademyBinding.rvAcademy.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentAcademyBinding.rvAcademy.setHasFixedSize(true);
            fragmentAcademyBinding.rvAcademy.setAdapter(academyAdapter);
        }

    }
}

