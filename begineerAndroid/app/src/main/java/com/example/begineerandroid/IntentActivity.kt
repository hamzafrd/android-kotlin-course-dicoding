package com.example.begineerandroid

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IntentActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        val tvIntentData: TextView = findViewById(R.id.tv_string_intent)

        val name = intent.getStringExtra(EXTRA_NAME)
        val age = intent.getIntExtra(EXTRA_AGE, 0)


//        parcelable
        val persons = if (Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra(EXTRA_PERSON, Person::class.java)
            intent.getParcelableArrayListExtra(EXTRA_PERSON, Person::class.java)
        } else {
            @Suppress("DEPRECATION")
//            intent.getParcelableExtra(EXTRA_PERSON)
            intent.getParcelableArrayListExtra(EXTRA_PERSON)
        }

        if (persons != null) {
            val text = ArrayList<String>()
            persons.forEach { person ->
                text.add(
                    "Name : ${person.name.toString()},\nEmail : ${person.email},\nAge : ${person.age},\nLocation : ${person.city}"
                )
            }

            tvIntentData.text = text.toString()
        } else {
            val text = "name:$name age:$age"
            tvIntentData.text = text

        }
    }
}