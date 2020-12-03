package com.dicoding.academies.ui.reader.content


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.academies.data.ModuleEntity
import com.dicoding.academies.databinding.FragmentModuleContentBinding
import com.dicoding.academies.ui.reader.CourseReaderViewModel


/**
 * A simple [Fragment] subclass.
 */
class ModuleContentFragment : Fragment() {

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModuleContentBinding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return fragmentModuleContentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        fragmentModuleContentBinding.webView.loadData(module.contentEntity?.content
                ?: "", "text/html", "UTF-8")
    }
}
