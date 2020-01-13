package com.dicoding.academies.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.academies.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f

    }
}