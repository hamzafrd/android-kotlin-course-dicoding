package com.example.begineerandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtLebar: EditText
    private lateinit var edtTinggi: EditText
    private lateinit var edtPanjang: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView
    private lateinit var btnMoveActivity: Button

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResult.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getIntExtra(MoveForResult.EXTRA_SELECTED_VALUE, 0)
            tvHasil.text = "Hasil : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtLebar = findViewById(R.id.edt_lebar)
        edtTinggi = findViewById(R.id.edt_tinggi)
        edtPanjang = findViewById(R.id.edt_panjang)
        btnHitung = findViewById(R.id.btnHitung)
        tvHasil = findViewById(R.id.tv_result)
        btnMoveActivity = findViewById(R.id.btn_move_activity)

        btnHitung.setOnClickListener(this)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_data_class)
        btnMoveWithObject.setOnClickListener(this)

//      Implicit Intent
        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

//      Nilai Balik Dari Intent
        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btnHitung) {
            val inputLength = edtPanjang.text.toString().trim()
            val inputWidth = edtLebar.text.toString().trim()
            val inputHeight = edtTinggi.text.toString().trim()
            val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
            tvHasil.text = volume.toString()
        }

        when (p0?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, IntentActivity::class.java).also {
                    it.putExtra(IntentActivity.EXTRA_NAME, "Hamza Firdaus Boy")
                    it.putExtra(IntentActivity.EXTRA_AGE, 22)
                }
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data_class -> {
                val person1 = Person(
                    "DicodingAcademy",
                    5,
                    "academy@dicoding.com",
                    "Bandung"
                )
                val person2 = Person(
                    "DicodingAcademy2",
                    52,
                    "academy@dicoding.com2",
                    "Bandung2"
                )

                val persons = arrayListOf<Person>(person1, person2)
                val moveWithObjectIntent = Intent(this@MainActivity, IntentActivity::class.java)
//                moveWithObjectIntent.putExtra(IntentActivity.EXTRA_PERSON, person)
                moveWithObjectIntent.putParcelableArrayListExtra(
                    IntentActivity.EXTRA_PERSON,
                    persons
                )
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "081210841382"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResult::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}
