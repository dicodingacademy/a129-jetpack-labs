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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.databinding.FragmentBookmarkBinding;
import com.dicoding.academies.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment implements BookmarkFragmentCallback {

    private FragmentBookmarkBinding fragmentBookmarkBinding;

    private BookmarkViewModel viewModel;
    private BookmarkAdapter adapter;

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

        itemTouchHelper.attachToRecyclerView(fragmentBookmarkBinding.rvBookmark);

        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this, factory).get(BookmarkViewModel.class);

            adapter = new BookmarkAdapter(this);
            fragmentBookmarkBinding.progressBar.setVisibility(View.VISIBLE);
            viewModel.getBookmarks().observe(this, courses -> {
                fragmentBookmarkBinding.progressBar.setVisibility(View.GONE);
                adapter.submitList(courses);
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

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                int swipedPosition = viewHolder.getAdapterPosition();
                CourseEntity courseEntity = adapter.getSwipedData(swipedPosition);
                viewModel.setBookmark(courseEntity);
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setBookmark(courseEntity));
                snackbar.show();
            }
        }
    });
}