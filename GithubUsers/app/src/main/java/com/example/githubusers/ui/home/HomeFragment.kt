package com.example.githubusers.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.data.api.model.GithubUser
import com.example.githubusers.data.local.entity.UserEntity
import com.example.githubusers.databinding.FragmentHomeBinding
import com.example.githubusers.helper.SettingPreference
import com.example.githubusers.helper.ViewModelFactory
import com.example.githubusers.ui.main.MainAdapter
import com.google.android.material.snackbar.Snackbar

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class HomeFragment : Fragment() {
    private lateinit var adapter: MainAdapter
    private lateinit var homeViewModel: HomeViewModel

    //    private val userList = arrayListOf<GithubUser>()
    private val dataUsers = arrayListOf<UserEntity>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).title = "Github Users"

        val layoutManager = LinearLayoutManager(context)
        val itemDivider = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvGithubUser.setHasFixedSize(true)
        binding.rvGithubUser.layoutManager = layoutManager
        binding.rvGithubUser.addItemDecoration(itemDivider)

        val pref = context?.dataStore?.let { SettingPreference.getInstance(it) }
        homeViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(pref = pref)
        )[HomeViewModel::class.java]

        searchUser()

        homeViewModel.apply {
            searchUsers.observe(viewLifecycleOwner) {
                showUsers(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
            isSearched.observe(viewLifecycleOwner) {
                showPlaceHolder(it)
            }

            toastText.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { text ->
                    Snackbar.make(
                        binding.root,
                        text,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            getThemeSettings().observe(viewLifecycleOwner) {
                if (it) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    private fun showUsers(usersApi: List<GithubUser>?) {
        dataUsers.clear()
        usersApi?.map {
            val user = UserEntity(
                it.login,
                it.avatarUrl,
                it.id,
            )
            dataUsers.add(user)
        }

        adapter = MainAdapter() {
            val mBundle = Bundle()
            mBundle.putString(EXTRA_USERNAME, it.username)
            mBundle.putString(EXTRA_IMAGEURL, it.urlToImage)
            mBundle.putInt(EXTRA_ID, it.id ?: 0)
            view?.findNavController()
                ?.navigate(R.id.action_homeFragment_to_detailActivity, mBundle)

        }
        adapter.setListUser(dataUsers)
        binding.rvGithubUser.adapter = adapter
    }

    private fun showPlaceHolder(it: Boolean) {
        if (it) {
            binding.searchPlaceholder.visibility = View.GONE
        } else {
            binding.searchPlaceholder.visibility = View.VISIBLE
        }
    }

    private fun showLoading(it: Boolean) {
        if (it) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun searchUser() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    return true
                } else {
                    dataUsers.clear()
                    homeViewModel.getUsers(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    companion object {
        const val EXTRA_USERNAME = "username"
        const val EXTRA_IMAGEURL = "image_url"
        const val EXTRA_ID = "id"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}