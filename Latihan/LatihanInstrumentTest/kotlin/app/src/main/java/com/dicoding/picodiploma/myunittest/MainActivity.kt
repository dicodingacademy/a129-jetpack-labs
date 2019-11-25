package com.dicoding.picodiploma.myunittest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        btn_save.setOnClickListener(this)
        btn_calculate_surface_area.setOnClickListener(this)
        btn_calculate_circumference.setOnClickListener(this)
        btn_calculate_volume.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val length = edt_length.text.toString().trim()
        val width = edt_width.text.toString().trim()
        val height = edt_height.text.toString().trim()

        when {
            length.isEmpty() -> edt_length.error = "Field ini tidak boleh kosong"
            width.isEmpty() -> edt_width.error = "Field ini tidak boleh kosong"
            height.isEmpty() -> edt_height.error = "Field ini tidak boleh kosong"
            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when {
                    v.id == R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }
                    v.id == R.id.btn_calculate_circumference -> {
                        tv_result.text = mainViewModel.circumference.toString()
                        gone()
                    }
                    v.id == R.id.btn_calculate_surface_area -> {
                        tv_result.text = mainViewModel.surfaceArea.toString()
                        gone()
                    }
                    v.id == R.id.btn_calculate_volume -> {
                        tv_result.text = mainViewModel.volume.toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        btn_calculate_volume.visibility = View.VISIBLE
        btn_calculate_circumference.visibility = View.VISIBLE
        btn_calculate_surface_area.visibility = View.VISIBLE
        btn_save.visibility = View.GONE
    }

    private fun gone() {
        btn_calculate_volume.visibility = View.GONE
        btn_calculate_circumference.visibility = View.GONE
        btn_calculate_surface_area.visibility = View.GONE
        btn_save.visibility = View.VISIBLE
    }
}
