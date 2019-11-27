package com.dicoding.picodiploma.myviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Membuat varibel global
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Menghubungkan ViewModel dengan Activity
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // Menampilkan hasil pertama kali
        displayResult()
        // Memberikan aksi klik kepada button calculate
        btn_calculate.setOnClickListener {
            // Mendapatkan string dari editText
            val width = edt_width.text.toString()
            val height = edt_height.text.toString()
            val length = edt_length.text.toString()
            // Melakukan pengecekan apakah empty atau tidak
            when {
                width.isEmpty() -> {
                    edt_width.error = "Masih kosong"
                }
                height.isEmpty() -> {
                    edt_height.error = "Masih kosong"
                }
                length.isEmpty() -> {
                    edt_length.error = "Masih kosong"
                }
                else -> {
                    // Melakukan pengiriman string ke ViewModel
                    viewModel.calculate(width, height, length)
                    //Menampilkan hasil dari calculate
                    displayResult()
                }
            }
        }
    }

    // Menampilkan hasil dari ViewModel ke tvResults
    private fun displayResult() {
        tv_result.text = viewModel.result.toString()
    }
}