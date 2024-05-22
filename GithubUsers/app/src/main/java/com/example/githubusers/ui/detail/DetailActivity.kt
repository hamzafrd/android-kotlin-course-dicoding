package com.example.githubusers.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.api.model.DetailGithubUser
import com.example.githubusers.data.local.entity.UserEntity
import com.example.githubusers.databinding.ActivityDetailBinding
import com.example.githubusers.ui.favorite.FavoriteActivity.Companion.IS_FAVORITE
import com.example.githubusers.helper.ViewModelFactory
import com.example.githubusers.ui.home.HomeFragment.Companion.EXTRA_ID
import com.example.githubusers.ui.home.HomeFragment.Companion.EXTRA_IMAGEURL
import com.example.githubusers.ui.home.HomeFragment.Companion.EXTRA_USERNAME
import com.example.githubusers.ui.settings.SettingsActivity
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var username: String
    private lateinit var imageUrl: String
    private  var id: Int? = null

    private lateinit var userCurrent: UserEntity
    private var isFavorite = false

    private lateinit var binding: ActivityDetailBinding

    //    private val detailViewModel by viewModels<DetailViewModel>()
    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Github Users"

        setExtras()
        detailViewModel.apply {
            isLoading.observe(this@DetailActivity) {
                showLoading(it)
            }

            getDetailUsers(username)
            detailUsers.observe(this@DetailActivity) {
                getDetailUsers(it)
            }
        }
        setFavorite()
    }

    private fun setExtras() {
        username = intent.extras?.getString(EXTRA_USERNAME).toString()
        imageUrl = intent.extras?.getString(EXTRA_IMAGEURL).toString()
        id = intent.extras?.getInt(EXTRA_ID)
    }

    private fun setFavorite() {
        userCurrent = UserEntity(
            username,
            imageUrl,
            id
        )
        Log.d("Favorite currentUser : ", userCurrent.username)
        Log.d("Favorite currentUser id : ", userCurrent.id.toString())

        isFavorite = intent.extras?.getBoolean(IS_FAVORITE) ?: false
        Log.d("Favorite Intent : ", isFavorite.toString())

        binding.fabFavorite.apply {
            detailViewModel.setFavorite(isFavorite)
            if(isFavorite) setImageResource(R.drawable.baseline_favorite_24)
            else setImageResource(R.drawable.baseline_favorite_border_24)

            setOnClickListener {
                detailViewModel.setFavorite(isFavorite)
                Log.d("Favorite Clicked : ", isFavorite.toString())

                if (isFavorite) {
                    isFavorite = false
                    detailViewModel.removeFromFavorite(userCurrent)
                    setImageResource(R.drawable.baseline_favorite_border_24)
                } else {
                    isFavorite = true
                    detailViewModel.addToFavorite(userCurrent)
                    setImageResource(R.drawable.baseline_favorite_24)
                }
            }
        }
    }

    private fun showLoading(it: Boolean) {
        if (it) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun getDetailUsers(detailUsers: DetailGithubUser) {
        binding.viewPager.adapter = DetailPagerAdapter(this, username)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        Glide.with(binding.root.context)
            .load(imageUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.detailAvatar)

        binding.apply {
            detailUsername.text = username
            detailName.text = detailUsers.name
            detailCompany.text = getString(R.string.company, detailUsers.company)
            detailLocation.text = getString(R.string.location, detailUsers.location)
            detailRepository.text =
                getString(R.string.repository, detailUsers.publicRepos.toString())
            detailFollowers.text = getString(R.string.followers, detailUsers.followers.toString())
            detailFollowing.text = getString(R.string.following, detailUsers.following.toString())
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
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }

        return true
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )
    }
}

