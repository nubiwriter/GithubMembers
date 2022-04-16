package com.alif.githubmembers.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alif.githubmembers.DetailUserActivity
import com.alif.githubmembers.ui.fragment.FollowersFragment
import com.alif.githubmembers.ui.fragment.FollowingFragment

class SectionsPagerAdapter(activity: DetailUserActivity, data: Bundle) : FragmentStateAdapter(activity) {

    private var fragmentBundle: Bundle = data

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }

        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }
}