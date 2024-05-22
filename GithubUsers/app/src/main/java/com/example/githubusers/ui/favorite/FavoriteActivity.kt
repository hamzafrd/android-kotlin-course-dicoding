package com.example.githubusers.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.data.local.entity.UserEntity
import com.example.githubusers.databinding.ActivityFavoriteBinding
import com.example.githubusers.helper.ViewModelFactory
import com.example.githubusers.ui.detail.DetailActivity
import com.example.githubusers.ui.home.HomeFragment
import com.example.githubusers.ui.main.MainAdapter
import com.example.githubusers.ui.settings.SettingsActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: MainAdapter
    private var dataFavorite = arrayListOf<UserEntity>()

    private val viewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.favorite_title)
        binding.rvFavoriteUsers.layoutManager = LinearLayoutManager(this)

        loadFavoriteUserData()
    }

    private fun loadFavoriteUserData() {
        viewModel.getAllFavoriteUser().observe(this) { listFavorite ->
            adapter = MainAdapter {
                Intent(this@FavoriteActivity, DetailActivity::class.java).also { intent ->
                    intent.putExtra(HomeFragment.EXTRA_USERNAME, it.username)
                    intent.putExtra(HomeFragment.EXTRA_IMAGEURL, it.urlToImage)
                    intent.putExtra(HomeFragment.EXTRA_ID, it.id ?: 0)
                    intent.putExtra(IS_FAVORITE, true)
                    startActivity(intent)
                }
            }
            if (!listFavorite.isNullOrEmpty()) {
                adapter.setListUser(listFavorite)
            } else {
                dataFavorite.clear()
                binding.tvFavoriteNotFound.visibility = View.VISIBLE
            }
            binding.rvFavoriteUsers.adapter = adapter
            binding.rvFavoriteUsers.setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)

        val actionFavorite = menu?.findItem(R.id.action_favorite)
        actionFavorite?.isVisible = false

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_setting) {
            Intent(this@FavoriteActivity, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }

        return true
    }

    companion object {
        const val IS_FAVORITE = "is_favorite"
    }
}