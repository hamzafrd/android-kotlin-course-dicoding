package com.spnj.notes

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spnj.notes.adapter.AdapterActivity
import com.spnj.notes.adapter.DataClassActitivity
import com.spnj.notes.databinding.ActivityMainBinding
import com.spnj.notes.fiturNotes.NotesAddActivity
import com.spnj.notes.fiturNotes.NotesDeleteActivity
import com.spnj.notes.fiturNotes.NotesDetailActivity
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var data: ArrayList<DataClassActitivity>
        lateinit var recyclerView: RecyclerView
        lateinit var adapter: AdapterActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        buatRecycleView()
        intentRecycleView()
    }

    private fun buatRecycleView() {
        adapter = AdapterActivity()
        recyclerView = findViewById(R.id.recycleViewNotes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)
    }

    private fun intentRecycleView() {
        adapter.setItemClickListener(object : AdapterActivity.OnItemClickListener {
            override fun onItemclick(position: Int) {
                val intent = Intent(this@MainActivity, NotesDetailActivity::class.java)
                intent.putExtra("posisi", position)
                startActivity(intent)
            }
        })

        adapter.setItemLongClickListener(object : AdapterActivity.OnItemLongClickListener {
            override fun onItemclick(position: Int) {
                AlertDialog.Builder(this@MainActivity)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are You Sure ?")
                    .setMessage("Do You Want delete?")
                    .setPositiveButton("Yes") { _, _ ->
                        data.remove(data[position])
                        adapter.notifyItemRemoved(position)
                        saveData()
                    }
                    .setNegativeButton("no", null)
                    .show()
            }
        })

        binding.fabCreateNote.setOnClickListener {
            Intent(this@MainActivity, NotesAddActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.fabDeleteList.setOnClickListener {
            Intent(this@MainActivity, NotesDeleteActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun loadData() {

        val sharedPreferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        val gson = Gson()
        data = ArrayList()
        val emptyList = gson.toJson(data)
        val json = sharedPreferences.getString("task_list", emptyList)
        val type = object : TypeToken<ArrayList<DataClassActitivity>>() {}.type
        data = gson.fromJson(json, type)
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(data)

        editor.apply {
            putString("task_list", json)
            apply()
        }
    }


}
