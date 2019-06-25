package com.dicoding.academies.ui.bookmark;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.academies.R;
import com.dicoding.academies.data.source.local.entity.CourseEntity;
import com.dicoding.academies.di.Injectable;
import com.dicoding.academies.viewmodel.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment implements BookmarkFragmentCallback, Injectable {
    private BookmarkPagedAdapter adapter;
    private RecyclerView rvBookmark;
    private ProgressBar progressBar;
    private BookmarkViewModel viewModel;
    private List<CourseEntity> courses;
    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            // Aksi di bawah digunakan untuk melakukan swap ke kenan dan ke kiri
            return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (getView() != null) {
                // Sebelum melakukan penghapusan, course harus mendapatkan posisi dari item yang di swipe
                int swipedPosition = viewHolder.getAdapterPosition();

                // Kemudian memanggil CourseEntity sesuai posisi ketika diswipe
                CourseEntity courseEntity = adapter.getItemById(swipedPosition);

                // Melakukan setBookmark untuk menghapus bookmark dari list course
                viewModel.setBookmark(courseEntity);

                // Memanggil Snackbar untuk melakukan pengecekan, apakah benar melakukan penghapusan bookmark
                Snackbar snackbar = Snackbar.make(getView(), R.string.message_undo, Snackbar.LENGTH_LONG);

                // Mengembalikan item yang terhapus
                snackbar.setAction(R.string.message_ok, v -> viewModel.setBookmark(courseEntity));

                // Menampilkan snackbar
                snackbar.show();
            }
        }
    });

    @Inject
    ViewModelProvider.Factory factory;

    public BookmarkFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new BookmarkFragment();
    }

    @NonNull
    private BookmarkViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        return ViewModelProviders.of(activity, factory).get(BookmarkViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBookmark = view.findViewById(R.id.rv_bookmark);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            viewModel = obtainViewModel(getActivity());

            adapter = new BookmarkPagedAdapter(this);

            viewModel.getBookmarksPaged().observe(this, courses -> {
                if (courses != null) {
                    switch (courses.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            adapter.submitList(courses.data);
                            adapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

            });

            rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
            rvBookmark.setHasFixedSize(true);
            rvBookmark.setAdapter(adapter);

            //Memberikan aksi untuk swipe
            itemTouchHelper.attachToRecyclerView(rvBookmark);
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
}

