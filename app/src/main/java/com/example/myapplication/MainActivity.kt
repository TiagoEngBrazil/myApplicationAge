package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM")
    private val dateFormat2: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val todayDate2 = dateFormat2.format(calendar.time)

        binding.textObservation.text = getString(R.string.observation, todayDate2.toString())

        binding.buttonCalculate.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_calculate) {
            calculate()
        }
    }

    private fun validation2(): Boolean {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return ((binding.textYearOfBirth.text.toString().toInt() in (1900..currentYear)))
    }


    @SuppressLint("SetTextI18n")
    private fun calculate() {



        val yearOfBirth = binding.textYearOfBirth.text.toString().toIntOrNull()
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val todayDate = dateFormat.format(calendar.time)


        if (yearOfBirth != null && validation2()) {
            val age1 = currentYear - yearOfBirth
            val age2 = currentYear - yearOfBirth - 1
            if (currentYear == yearOfBirth) {
                binding.textAnswer1.text =
                    getString(R.string.type_answer_1, currentYear.toString())
                binding.textAnswer2.text = getString(R.string.empity_string)

            } else if (yearOfBirth == currentYear - 1) {
                binding.textAnswer1.text = getString(R.string.type_answer_2, currentYear.toString(), todayDate.toString(), todayDate.toString())

                binding.textAnswer2.text =
                    getString(R.string.type_answer_3, yearOfBirth.toString(), todayDate.toString())
            } else {
                binding.textAnswer1.text =
                    getString(R.string.type_answer_4, yearOfBirth.toString(), todayDate.toString(), todayDate.toString(), age1.toString())
                binding.textAnswer2.text =
                    getString(R.string.type_answer_5, yearOfBirth.toString(), todayDate.toString(), age2.toString())
            }

        } else if (yearOfBirth == null) {
            Toast.makeText(this, R.string.exception_year_of_birth, Toast.LENGTH_SHORT)
                .show()
            binding.textAnswer1.text = getString(R.string.empity_string)
            binding.textAnswer2.text = getString(R.string.empity_string)
            return
        } else if (!validation2()) {
            Toast.makeText(
                this, getString(R.string.exception_gap_year, currentYear.toString())
                ,
                Toast.LENGTH_LONG
            ).show()
            binding.textAnswer1.text = getString(R.string.empity_string)
            binding.textAnswer2.text = getString(R.string.empity_string)
        }
    }

}