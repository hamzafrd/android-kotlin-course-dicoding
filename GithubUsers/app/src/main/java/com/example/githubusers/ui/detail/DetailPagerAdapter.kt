package com.example.githubusers.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubusers.ui.detail.followingFollowers.FollowErIngFragment

class DetailPagerAdapter(activity: AppCompatActivity, str: String) :
    FragmentStateAdapter(activity) {
    private val username = str
    private val followersMode = "followers"
    private val followingMode = "following"
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowErIngFragment()
        val bundle = Bundle()
        when (position) {
            0 -> {
                bundle.putString(FollowErIngFragment.ARG_USER, username)
                bundle.putString(FollowErIngFragment.ARG_MODE, followersMode)
                fragment.arguments = bundle
            }
            1 -> {
                bundle.putString(USERNAME, username)
                bundle.putString(MODE, followingMode)
                fragment.arguments = bundle
            }
        }
        return fragment
    }

    companion object {
        const val USERNAME = "username"
        const val MODE = "mode"
    }
}
