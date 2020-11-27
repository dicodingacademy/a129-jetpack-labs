package com.dicoding.academies.ui.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.databinding.FragmentBookmarkBinding;
import com.dicoding.academies.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment implements BookmarkFragmentCallback {

    private FragmentBookmarkBinding fragmentBookmarkBinding;

    public BookmarkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater);
        return fragmentBookmarkBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            BookmarkViewModel viewModel = new ViewModelProvider(this, factory).get(BookmarkViewModel.class);

            BookmarkAdapter adapter = new BookmarkAdapter(this);
            fragmentBookmarkBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getBookmarks().observe(this, courses -> {
                fragmentBookmarkBinding.progressBar.setVisibility(View.GONE);
                adapter.setCourses(courses);
                adapter.notifyDataSetChanged();
            });

            fragmentBookmarkBinding.rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
            fragmentBookmarkBinding.rvBookmark.setHasFixedSize(true);
            fragmentBookmarkBinding.rvBookmark.setAdapter(adapter);
        }
    }

    @Override
    public void onShareClick(CourseEntity course) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText(String.format("Segera daftar kelas %s di dicoding.com", course.getTitle()))
                    .startChooser();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentBookmarkBinding = null;
    }
}