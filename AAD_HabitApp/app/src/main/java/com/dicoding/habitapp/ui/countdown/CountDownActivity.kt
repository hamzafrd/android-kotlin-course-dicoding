package com.dicoding.habitapp.ui.countdown

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.notification.NotificationWorker
import com.dicoding.habitapp.utils.HABIT
import com.dicoding.habitapp.utils.HABIT_ID
import com.dicoding.habitapp.utils.HABIT_TITLE

class CountDownActivity : AppCompatActivity() {
    private var startCountDown = false
    private lateinit var workManager: WorkManager
    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)
        supportActionBar?.title = "Count Down"
        workManager = WorkManager.getInstance(this)
        val habit = intent.getParcelableExtra<Habit>(HABIT) as Habit

        findViewById<TextView>(R.id.tv_count_down_title).text = habit.title
        val tvCountDown: TextView = findViewById(R.id.tv_count_down)

        val viewModel = ViewModelProvider(this).get(CountDownViewModel::class.java)

        //TODO 10 : Set initial time and observe current time. Update button state when countdown is finished
        viewModel.setInitialTime(habit.minutesFocus)
        viewModel.currentTimeString.observe(this){
            tvCountDown.text = it
        }
        viewModel.eventCountDownFinish.observe(this){
            if(!startCountDown) updateButtonState(it)
            else updateButtonState(!it)
            if(it && startCountDown) {
                startOneTimeWorkManager(habit)
            }
        }
        //TODO 13 : Start and cancel One Time Request WorkManager to notify when time is up.
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            updateButtonState(true)
            viewModel.startTimer()
            startCountDown = true
        }

        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            viewModel.resetTimer()
            cancelOneTimeWorkManager()
            startCountDown = false
            updateButtonState(false)
        }
    }

    private fun updateButtonState(isRunning: Boolean) {
        findViewById<Button>(R.id.btn_start).isEnabled = !isRunning
        findViewById<Button>(R.id.btn_stop).isEnabled = isRunning
    }
    private fun startOneTimeWorkManager(habit: Habit) {
        val data = Data.Builder()
            .putInt(HABIT_ID, habit.id)
            .putString(HABIT_TITLE, habit.title)
            .build()

        oneTimeWorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInputData(data)
            .build()

        workManager.enqueue(oneTimeWorkRequest)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this) { workInfo ->
            val status = workInfo.state.name
            if (workInfo.state == WorkInfo.State.ENQUEUED) {
                Log.d(TAG, "Notification has been enqueued. Status : $status")
            } else if (workInfo.state == WorkInfo.State.CANCELLED) {
                Log.d(TAG, "Notification has been cancelled")
            }
        }
    }
    private fun cancelOneTimeWorkManager() {
        try {
            workManager.cancelWorkById(oneTimeWorkRequest.id)
            Log.d(TAG, "Cancelling WorkManager successful!")
        } catch (e: Exception) {
            Log.d(TAG, "Cancelling WorkManager failed due to ${e.message}")
        }
    }
    companion object {
        const val TAG = "CountdownActivity"
    }
}