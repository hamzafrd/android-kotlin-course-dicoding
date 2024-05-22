package com.spnj.notes.fiturNotes

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spnj.notes.MainActivity
import com.spnj.notes.MainActivity.Companion.adapter
import com.spnj.notes.MainActivity.Companion.data
import com.spnj.notes.MainActivity.Companion.recyclerView
import com.spnj.notes.R
import com.spnj.notes.adapter.AdapterActivity
import com.spnj.notes.adapter.DataClassActitivity
import com.spnj.notes.databinding.ActivityMainDeleteBinding
import kotlin.collections.ArrayList

class NotesDeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        notif()
        setArray()
        buatRecycleView()
        intentRecycleView()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this@NotesDeleteActivity)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Delete")
            .setMessage("Discard All Changes?")
            .setPositiveButton("Yes") { _, _ ->
                Intent(this@NotesDeleteActivity, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            .setNegativeButton("no", null)
            .show()

    }

    private fun notif() {
        AlertDialog.Builder(this@NotesDeleteActivity)
            .setIcon(R.drawable.ic_twotone_delete_sweep_24)
            .setTitle("Delete Notes")
            .setMessage("Click On The Notes to Delete")
            .show()
    }

    private fun setArray() {
        adapter = AdapterActivity()
    }

    private fun buatRecycleView() {
        recyclerView = binding.recycleViewNotes
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun intentRecycleView() {
        adapter.setItemLongClickListener(object : AdapterActivity.OnItemLongClickListener {
            override fun onItemclick(position: Int) {
                val intent = Intent(this@NotesDeleteActivity, NotesDetailActivity::class.java)
                intent.putExtra("posisi", position)
                startActivity(intent)
            }
        })

        adapter.setItemClickListener(object : AdapterActivity.OnItemClickListener {
            override fun onItemclick(position: Int) {
                AlertDialog.Builder(this@NotesDeleteActivity)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are You Sure ?")
                    .setMessage("Do You Want delete?")
                    .setPositiveButton("Yes") { _, _ ->
                        data.remove(data[position])
                        adapter.notifyItemRemoved(position)
                    }
                    .setNegativeButton("no", null)
                    .show()
            }
        })

        binding.fabDone.setOnClickListener {
            Intent(this@NotesDeleteActivity, MainActivity::class.java).also {
                saveData()
                startActivity(it)
                finish()
            }
        }
    }


    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
        val gson = Gson()
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
