package com.example.myapiretrofit

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapiretrofit.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private val mainViewModel: MainViewModel by viewModels()\
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)
//        findRestaurant()

        mainViewModel.apply {
            with(this@MainActivity) {
                restaurant.observe(this) { restaurant ->
                    setRestaurantData(restaurant)
                }
                listReview.observe(this) { consumerReviews ->
                    setReviewData(consumerReviews)
                }
                isLoading.observe(this) {
                    showLoading(it)
                }

                //snackbar
                snackBarText.observe(this) {
                    it.getContentIfNotHandled()?.let { snackbarText ->
                        Snackbar.make(
                            window.decorView.rootView,
                            snackbarText,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                }
            }
        }

        binding.btnSend.setOnClickListener { view ->
//            postReview(binding.edReview.text.toString())
            mainViewModel.postReview(binding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun setRestaurantData(restaurant: Restaurant) {
        binding.tvTitle.text = restaurant.name
        binding.tvDescription.text = restaurant.description
        Glide.with(this@MainActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(binding.ivPicture)
    }

    private fun setReviewData(consumerReviews: List<CustomerReviewsItem>) {
        val listReview = ArrayList<String>()
        for (review in consumerReviews) {
            listReview.add(
                """
                ${review.review}
                - ${review.name}
                """.trimIndent()
            )
        }
        val adapter = ReviewAdapter(listReview)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}