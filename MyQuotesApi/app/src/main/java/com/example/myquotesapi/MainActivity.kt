package com.example.myquotesapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myquotesapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        internal val TAG = "LOG " + MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFragmentManager = supportFragmentManager
        val mHomeFragment = MainFragment()
        val fragment = mFragmentManager.findFragmentByTag(MainFragment::class.java.simpleName)

        if (fragment !is MainFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name : " + MainFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mHomeFragment, MainFragment::class.java.simpleName)
                .commit()
        }
    }

}