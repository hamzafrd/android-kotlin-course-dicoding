package com.example.githubusers.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubusers.R
import com.example.githubusers.ui.favorite.FavoriteActivity
import com.example.githubusers.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                Intent(this@MainActivity, FavoriteActivity::class.java).also {
                    startActivity(it)
                }

                return true
            }
            R.id.action_setting -> {
                Intent(this@MainActivity, SettingsActivity::class.java).also {
                    startActivity(it)
                }

                return true
            }

            else -> return true
        }
    }
}