package com.example.githubusers.ui.detail.followingFollowers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.data.api.model.GithubUser
import com.example.githubusers.data.local.entity.UserEntity
import com.example.githubusers.databinding.FragmentFollowErIngBinding
import com.example.githubusers.ui.detail.DetailViewModel
import com.example.githubusers.ui.main.MainAdapter

class FollowErIngFragment : Fragment() {
    lateinit var adapter: MainAdapter
    private lateinit var viewModel: DetailViewModel

    private var _binding: FragmentFollowErIngBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowErIngBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        val mode = arguments?.getString(ARG_MODE)
        val username = arguments?.getString(ARG_USER) ?: ""

        viewModel.apply {
            isLoadingTab.observe(viewLifecycleOwner) {
                showLoading(it)
            }

            if (mode == "following") {
                getUsersFollowing(username)
                Log.d(TAG, "Following : $username $mode")

                following.observe(viewLifecycleOwner) {
                    getFollows(it)
                }

            } else {
                getUsersFollowers(username)
                Log.d(TAG, "Followers : $username $mode")

                followers.observe(viewLifecycleOwner) {
                    getFollows(it)
                }
            }

        }
    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.rvGithubFollowiers.setHasFixedSize(true)
        binding.rvGithubFollowiers.layoutManager = layoutManager
    }

    private fun showLoading(it: Boolean) {
        binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
    }

    //    private fun getFollowing(following: List<GithubUser>) {
//        adapter = FollowErIngAdapter(following)
//        binding.rvGithubFollowiers.adapter = adapter
//    }
//
//    private fun getFollowers(followers: List<GithubUser>) {
//        adapter = FollowErIngAdapter(followers)
//        binding.rvGithubFollowiers.adapter = adapter
//    }
    private fun getFollows(following: List<GithubUser>) {
        val newListUser = ArrayList<UserEntity>()
        newListUser.clear()
        following.map {
            val user = UserEntity(
                it.login,
                it.avatarUrl,
                it.id
            )
            newListUser.add(user)
        }

        adapter = MainAdapter() {
            Toast.makeText(
                requireContext(),
                "${it.username} clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        adapter.setListUser(newListUser)
        binding.rvGithubFollowiers.adapter = adapter
    }

    companion object {
        const val ARG_MODE = "mode"
        const val ARG_USER = "username"
        private val TAG = FollowErIngFragment::class.java.simpleName
    }
}