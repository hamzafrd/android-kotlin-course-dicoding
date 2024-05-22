package com.training.dicodingsubmitpart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.training.dicodingsubmitpart2.adapter.GridAdapter
import com.training.dicodingsubmitpart2.adapter.CardAdapter
import com.training.dicodingsubmitpart2.adapter.VerticalAdapter
import com.training.dicodingsubmitpart2.intent.About
import com.training.dicodingsubmitpart2.intent.Detail

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rv: RecyclerView
    private var list: ArrayList<Students> = arrayListOf()
    private var bar: String = "List of Students"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(bar)

        rv = findViewById(R.id.rv_students)
        rv.setHasFixedSize(true)

        list.addAll(DataStudents.listData)
        showRecyclerList()

        val about = findViewById<Button>(R.id.btn_about)

        about.setOnClickListener(this)
    }

    private fun showRecyclerList() {
        rv.layoutManager = LinearLayoutManager(this)
        val listAdapter = VerticalAdapter(list)
        rv.adapter = listAdapter

        listAdapter.setOnItemClickCallback(object : VerticalAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Students) {
                showSelected(data)
            }
        })
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showSelected(murid: Students) {
        Toast.makeText(this, "Kamu memilih " + murid.nama, Toast.LENGTH_SHORT).show()

        val detail = Intent(this, Detail::class.java)
        detail.apply {
            putExtra(Detail.EXTRA_NAME,murid.nama)
            putExtra(Detail.EXTRA_KLS,murid.kelas)
            putExtra(Detail.EXTRA_ALAMAT,murid.alamat)
            putExtra(Detail.EXTRA_IMG,murid.image)
            putExtra(Detail.EXTRA_AGE,murid.umur)

            putExtra(Detail.EXTRA_CHIP1,murid.chip1)
            putExtra(Detail.EXTRA_CHIP2,murid.chip2)
            putExtra(Detail.EXTRA_CHIP3,murid.chip3)
        }
        startActivity(detail)
    }

    /////////////////////Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    ///// Menu select
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
            }
            R.id.action_grid -> {
                bar = "Student's Picture"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                bar = "Student's CardView"
                showRecyclerCardView()
            }
        }
        setActionBarTitle(bar)
    }

    private fun showRecyclerGrid() {
        rv.layoutManager = GridLayoutManager(this, 2)
        val gridAdapter = GridAdapter(list)
        rv.adapter = gridAdapter

       gridAdapter.setOnItemClickCallback(object : GridAdapter.OnItemClickCallback{
           override fun onItemClicked(data: Students) {
               showSelected(data)
           }

       })
    }

    private fun showRecyclerCardView() {
        rv.layoutManager = LinearLayoutManager(this)
        val cardAdapter = CardAdapter(list)
        rv.adapter = cardAdapter

        cardAdapter.setOnItemClickCallback(object : CardAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Students) {
                showSelected(data)
            }

        })
    }

    override fun onClick(p0: View?) {
        Intent(this,About::class.java).also {
            startActivity(it)
        }
    }


}