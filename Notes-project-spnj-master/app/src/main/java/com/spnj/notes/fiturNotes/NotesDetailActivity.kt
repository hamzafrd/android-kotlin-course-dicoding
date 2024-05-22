package com.spnj.notes.fiturNotes

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.spnj.notes.MainActivity.Companion.adapter
import com.spnj.notes.MainActivity.Companion.data
import com.spnj.notes.adapter.DataClassActitivity
import com.spnj.notes.databinding.EditorNotesBinding

class NotesDetailActivity : AppCompatActivity() {
    private lateinit var binding: EditorNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditorNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saveText()
        saveData()
    }

    private fun saveText() {
        val judulE = binding.judulEditor
        val catatanE = binding.catatanEditor

        val extra: Bundle? = intent.extras
        val posisi = extra!!.getInt("posisi", -1)
        judulE.setText(data[posisi].judul)
        catatanE.setText(data[posisi].catatan)
        updatenotes(judulE, catatanE, posisi)
    }

    private fun updatenotes(judulE: TextInputEditText, catatanE: TextInputEditText, posisi: Int) {
        judulE.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    data[posisi] = DataClassActitivity(p0.toString(), data[posisi].catatan)
                    if (binding.judulEditor.text.toString().trim().isEmpty()) {
                        data[posisi] = DataClassActitivity(data[posisi].catatan, data[posisi].catatan)
                    }
                    adapter.notifyItemChanged(posisi)
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })

        catatanE.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    data[posisi] = DataClassActitivity(data[posisi].judul, p0.toString())
                    if (binding.judulEditor.text.toString().trim().isEmpty()) {
                        data[posisi] = DataClassActitivity(data[posisi].catatan, p0.toString())
                    }

                    adapter.notifyItemChanged(posisi)
                    saveData()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        binding.fabSaveNote.setOnClickListener {
            finish()
        }
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
