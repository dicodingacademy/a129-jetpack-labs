package com.dicoding.academies.ui.reader.content;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.ModuleEntity;
import com.dicoding.academies.ui.reader.CourseReaderViewModel;
import com.dicoding.academies.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModuleContentFragment extends Fragment {
    public static final String TAG = ModuleContentFragment.class.getSimpleName();
    private WebView webView;
    private ProgressBar progressBar;

    public ModuleContentFragment() {
        // Required empty public constructor
    }

    public static ModuleContentFragment newInstance() {
        return new ModuleContentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_module_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.web_view);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
        CourseReaderViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(CourseReaderViewModel.class);

        progressBar.setVisibility(View.VISIBLE);
        viewModel.getSelectedModule().observe(this, module -> {
            if (module != null) {
                progressBar.setVisibility(View.GONE);
                populateWebView(module);
            }
        });
    }

    private void populateWebView(ModuleEntity module) {
        webView.loadData(module.contentEntity.getContent(), "text/html", "UTF-8");
    }
}
