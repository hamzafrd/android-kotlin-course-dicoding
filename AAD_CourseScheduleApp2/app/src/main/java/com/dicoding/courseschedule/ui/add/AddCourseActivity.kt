package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.courseschedule.R

class AddCourseActivity : AppCompatActivity() {
    private val viewModel by viewModels<AddCourseViewModel> {
        AddCourseViewModelFactory.createFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
    }
}