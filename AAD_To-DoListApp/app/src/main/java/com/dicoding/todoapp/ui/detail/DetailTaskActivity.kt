package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.databinding.ActivityTaskDetailBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var detailTaskViewModel: DetailTaskViewModel
    private lateinit var binding: ActivityTaskDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 11 : Show detail task and implement delete action
        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)
        detailTaskViewModel.setTaskId(intent.getIntExtra(TASK_ID,-1))
        detailTaskViewModel.task.observe(this){
            if(it==null) return@observe
            binding.detailEdTitle.setText(it.title)
            binding.detailEdDescription.setText(it.description)
            binding.detailEdDueDate.setText(it.dueDateMillis.toString())
        }
        binding.btnDeleteTask.setOnClickListener{
            Toast.makeText(this, "Delete Task Succes", Toast.LENGTH_SHORT).show()
            detailTaskViewModel.deleteTask()
            finish()
        }
    }
}