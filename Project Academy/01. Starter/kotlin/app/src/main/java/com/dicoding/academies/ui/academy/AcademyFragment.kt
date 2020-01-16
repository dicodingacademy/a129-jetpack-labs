package com.dicoding.academies.ui.academy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academies.R
import com.dicoding.academies.utils.DataDummy
import kotlinx.android.synthetic.main.fragment_academy.*


/**
 * A simple [Fragment] subclass.
 */
class AcademyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_academy, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val courses = DataDummy.generateDummyCourses()
            val academyAdapter = AcademyAdapter()
            academyAdapter.setCourses(courses)

            with(rv_academy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}

