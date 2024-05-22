package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.databinding.ActivityAddCourseBinding
import com.dicoding.courseschedule.util.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {
    private lateinit var binding: ActivityAddCourseBinding
    private lateinit var addTaskViewModel: AddCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.add_course)

        val factory = AddViewModelFactory.createFactory(this)
        addTaskViewModel = ViewModelProvider(this, factory).get(AddCourseViewModel::class.java)
        addTaskViewModel.saved.observe(this) {
            if (it.getContentIfNotHandled()==true) {
                Toast.makeText(this, "Add Course Schedule Success", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                Toast.makeText(this, "Course Name or Time cant be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {
                binding.apply {
                    addTaskViewModel.insertCourse(
                        addEdCourse.text.toString(),
                        spinnerDay.selectedItemPosition,
                        tvStartTime.text.toString(),
                        tvEndTime.text.toString(),
                        addEdLecturer.text.toString(),
                        addEdNote.text.toString()
                    )
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showStartTimePicker(view: View) {
        val timeFragment = TimePickerFragment()
        timeFragment.show(supportFragmentManager, "startTimePicker")
    }
    fun showEndTimePicker(view: View) {
        val timeFragment = TimePickerFragment()
        timeFragment.show(supportFragmentManager, "endTimePicker")
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        if(tag=="endTimePicker")  binding.tvEndTime.text = timeFormat.format(calendar.time)
        else  binding.tvStartTime.text = timeFormat.format(calendar.time)
    }
}