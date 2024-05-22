package com.training.dicodingsubmitpart2.intent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.training.dicodingsubmitpart2.R

class Detail : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_KLS = "extra_kls"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_IMG = "extra_img"
        const val EXTRA_ALAMAT = "extra_alamat"
        const val EXTRA_CHIP1 = "chip1"
        const val EXTRA_CHIP2 = "chip2"
        const val EXTRA_CHIP3 = "chip3"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        val nama: TextView = findViewById(R.id.nama_student)
        val b: TextView = findViewById(R.id.umur_student)
        val c: TextView = findViewById(R.id.alamat_student)
        val d: TextView = findViewById(R.id.kelas_student)
        val e: ImageView = findViewById(R.id.image_student)

        val hobby1 = findViewById<Chip>(R.id.chip1)
        val hobby2 = findViewById<Chip>(R.id.chip2)
        val hobby3 = findViewById<Chip>(R.id.chip3)

        val name = intent.getStringExtra(EXTRA_NAME)
        val umur = intent.getIntExtra(EXTRA_AGE, 0)
        val alamat = intent.getStringExtra(EXTRA_ALAMAT)
        val kelas = intent.getStringExtra(EXTRA_KLS)
        val image = intent.getIntExtra(EXTRA_IMG, 0)

        val chip1 = intent.getStringExtra(EXTRA_CHIP1)
        val chip2 = intent.getStringExtra(EXTRA_CHIP2)
        val chip3 = intent.getStringExtra(EXTRA_CHIP3)

        nama.text = name
        b.text = umur.toString()
        c.text = alamat
        d.text = kelas
        e.setImageResource(image)

        hobby1.text = chip1
        hobby2.text = chip2
        hobby3.text = chip3
    }

    override fun onClick(p0: View?) {
        val an = Intent(this@Detail, About::class.java)
        startActivity(an)
    }
}