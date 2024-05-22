package com.example.recycleview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.recycleview.databinding.ActivityDetailActivityBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailActivityBinding
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val imgDetail : ImageView = findViewById(R.id.img_item_photo_detail)
//        val nameDetail : TextView = findViewById(R.id.tv_item_name_detail)
//        val descDetail : TextView = findViewById(R.id.tv_item_description_detail)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }
        Log.d("Detail Data", data?.name.toString())
//        data?.photo?.let { imgDetail.setImageResource(it) }
        Glide.with(baseContext)
            .load(data?.photo)
            .into(binding.imgItemPhotoDetail)
        binding.tvItemNameDetail.text = data?.name
        binding.tvItemDescriptionDetail.text = data?.description
    }
}