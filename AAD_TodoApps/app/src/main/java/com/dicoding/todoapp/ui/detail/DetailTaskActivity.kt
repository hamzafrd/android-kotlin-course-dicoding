package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val factory = ViewModelFactory.getInstance(this)
        val detailViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        val title = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val description = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val dueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val btnDelete = findViewById<Button>(R.id.btn_delete_task)

        detailViewModel.setTaskId(intent.getIntExtra(TASK_ID, -1))
        detailViewModel.task.observe(this) {
            it?.let{
                title.setText(it.title)
                description.setText(it.description)
                dueDate.setText(it.dueDateMillis.toString())
            }
        }

        btnDelete.setOnClickListener {
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
            finish()
            detailViewModel.deleteTask()
        }
    }
}