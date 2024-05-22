package com.spnj.notes.fiturNotes

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.spnj.notes.MainActivity
import com.spnj.notes.MainActivity.Companion.data
import com.spnj.notes.adapter.DataClassActitivity
import com.spnj.notes.databinding.EditorNotesBinding

class NotesAddActivity : AppCompatActivity() {
    private lateinit var binding: EditorNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditorNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabSaveNote.setOnClickListener {
            insertItem()
            finish()
            saveData()
        }
    }

    override fun onBackPressed() {
        val line1 = binding.judulEditor
        val line2 = binding.catatanEditor
        val logic1 = line1.text.toString().trim().isEmpty()
        val logic2 = line2.text.toString().trim().isEmpty()
        if (logic1 && logic2) {
            Toast.makeText(this@NotesAddActivity, "cancelled", Toast.LENGTH_SHORT).show()
            finish()
        } else if (binding.judulEditor.text.toString().trim().isEmpty()) {
            data.add(DataClassActitivity(line2.text.toString(), line2.text.toString()))
            Toast.makeText(this@NotesAddActivity, "Noted As Title", Toast.LENGTH_SHORT).show()
        } else {
            data.add(DataClassActitivity(line1.text.toString(), line2.text.toString()))
        }
        MainActivity.adapter.notifyItemInserted(data.size)
        finish()
    }

    private fun insertItem() {
        val line1 = binding.judulEditor
        val line2 = binding.catatanEditor
        val logic1 = line1.text.toString().trim().isEmpty()
        val logic2 = line2.text.toString().trim().isEmpty()
        if (logic1 && logic2) {
            finish()
        } else if (binding.judulEditor.text.toString().trim().isEmpty()) {
            data.add(DataClassActitivity(line2.text.toString(), line2.text.toString()))
            Toast.makeText(this@NotesAddActivity, "Noted As Title.", Toast.LENGTH_SHORT).show()
        } else {
            data.add(DataClassActitivity(line1.text.toString(), line2.text.toString()))
        }
        MainActivity.adapter.notifyItemInserted(data.size)
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