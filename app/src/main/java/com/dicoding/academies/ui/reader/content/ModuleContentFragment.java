package com.dicoding.academies.ui.reader.content;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.dicoding.academies.R;
import com.dicoding.academies.data.ContentEntity;
import com.dicoding.academies.data.ModuleEntity;
import com.dicoding.academies.ui.reader.CourseReaderActivity;
import com.dicoding.academies.ui.reader.CourseReaderCallback;
import com.dicoding.academies.ui.reader.CourseReaderViewModel;
import com.dicoding.academies.utils.DataDummy;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleContentFragment extends Fragment {
    public static final String TAG = ModuleContentFragment.class.getSimpleName();

    private WebView webView;
    private CourseReaderViewModel viewModel;

    public static ModuleContentFragment newInstance() {
        return new ModuleContentFragment();
    }

    public ModuleContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.web_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(CourseReaderViewModel.class);
            ModuleEntity module = viewModel.getSelectedModule();
            populateWebView(module);
        }
    }

    private void populateWebView(ModuleEntity content) {
        webView.loadData(content.contentEntity.getContent(), "text/html", "UTF-8");
    }

}
