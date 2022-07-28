package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

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

    private fun calculate() {
        Toast.makeText(this, "Tô on!", Toast.LENGTH_SHORT).show()
    }
}