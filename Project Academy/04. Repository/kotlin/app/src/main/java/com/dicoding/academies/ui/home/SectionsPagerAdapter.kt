package com.dicoding.academies.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.dicoding.academies.R
import com.dicoding.academies.ui.academy.AcademyFragment
import com.dicoding.academies.ui.bookmark.BookmarkFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.home, R.string.bookmark)
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        if (position == 0) {
            fragment = AcademyFragment()
        } else if (position == 1) {
            fragment = BookmarkFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}