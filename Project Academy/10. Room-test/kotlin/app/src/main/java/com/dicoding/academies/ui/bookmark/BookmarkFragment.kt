package com.dicoding.academies.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.data.source.local.entity.CourseEntity
import com.dicoding.academies.databinding.FragmentBookmarkBinding
import com.dicoding.academies.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class BookmarkFragment : Fragment(), BookmarkFragmentCallback {

    private var _fragmentBookmarkBinding: FragmentBookmarkBinding? = null
    private val binding get() = _fragmentBookmarkBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

            val adapter = BookmarkAdapter(this)
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getBookmarks().observe(this, { courses ->
                binding?.progressBar?.visibility = View.GONE
                adapter.setCourses(courses)
                adapter.notifyDataSetChanged()
            })

            binding?.rvBookmark?.layoutManager = LinearLayoutManager(context)
            binding?.rvBookmark?.setHasFixedSize(true)
            binding?.rvBookmark?.adapter = adapter
        }
    }

    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(requireActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang.")
                    .setText("Segera daftar kelas ${course.title} di dicoding.com")
                    .startChooser()
        }
    }
}