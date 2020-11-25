package com.dicoding.academies.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.databinding.FragmentAcademyBinding
import com.dicoding.academies.viewmodel.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class AcademyFragment : Fragment() {

    private lateinit var fragmentAcademyBinding: FragmentAcademyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentAcademyBinding = FragmentAcademyBinding.inflate(layoutInflater, container, false)
        return fragmentAcademyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]

            val academyAdapter = AcademyAdapter()

            fragmentAcademyBinding.progressBar.visibility = View.VISIBLE
            viewModel.getCourses().observe(this, { courses ->
                fragmentAcademyBinding.progressBar.visibility = View.GONE
                academyAdapter.setCourses(courses)
                academyAdapter.notifyDataSetChanged()
            })

            with(fragmentAcademyBinding.rvAcademy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}

