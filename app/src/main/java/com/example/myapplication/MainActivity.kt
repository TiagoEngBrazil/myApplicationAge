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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_calculate) {
            calculate()
        }
    }

    private fun validation1(): Boolean {
        return (binding.textYearOfBirth.text.toString() != "")
    }

    private fun validation2(): Boolean {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return ((binding.textYearOfBirth.text.toString().toInt() in (1900..currentYear)))
    }


    @SuppressLint("SetTextI18n")
    private fun calculate() {

        val yearOfBirth = binding.textYearOfBirth.text.toString().toInt()
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val todayDate = dateFormat.format(calendar.time)

        val age1 = currentYear - yearOfBirth
        val age2 = currentYear - yearOfBirth - 1

        if (validation1() && validation2()) {
            if (currentYear == yearOfBirth) {
                binding.textAnswer1.text =
                    "Quem nasceu em $currentYear ainda não completou 1 ano de vida"
                binding.textAnswer2.text = ""

            } else if (yearOfBirth == currentYear - 1) {
                binding.textAnswer1.text =
                    "Quem nasceu em $yearOfBirth, antes de $todayDate ou em $todayDate, tem 1 ano de vida"
                binding.textAnswer2.text =
                    "Quem nasceu em $yearOfBirth, depois de $todayDate, ainda não completou 1 ano de vida"
            } else {
                binding.textAnswer1.text =
                    "Quem nasceu em $yearOfBirth, antes de $todayDate ou em $todayDate, tem $age1 anos de vida"
                binding.textAnswer2.text =
                    "Quem nasceu em $yearOfBirth, depois de $todayDate, tem $age2 anos de vida"
            }

        } else if (!validation1()) {
            Toast.makeText(this, "É obrigatório preencher o ano de nascimento!", Toast.LENGTH_SHORT)
                .show()
            binding.textAnswer1.text = ""
            binding.textAnswer2.text = ""
        } else if (!validation2()) {
            Toast.makeText(
                this,
                "O ano de nascimento tem que ser maior que 1900 e menor que $currentYear",
                Toast.LENGTH_LONG
            ).show()
            binding.textAnswer1.text = ""
            binding.textAnswer2.text = ""
        }
    }
}