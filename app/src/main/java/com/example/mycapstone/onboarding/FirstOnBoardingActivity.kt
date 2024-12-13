package com.example.mycapstone.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycapstone.R
import com.example.mycapstone.databinding.ActivityFirstOnBoardingBinding

class FirstOnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstOnBoardingBinding

    private val selectedPreferences = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedPreferences()

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, SecondOnBoardingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun selectedPreferences() {
        val buttons = listOf(
            binding.btnBeach,
            binding.btnCity,
            binding.btnNature,
            binding.btnMountains
        )

        buttons.forEach { button->
            button.setOnClickListener {
                val preferenceName = button.text.toString()
                if (selectedPreferences.contains(preferenceName)) {
                    selectedPreferences.remove(preferenceName)
                    button.setBackgroundColor(resources.getColor(R.color.gray, null))
                } else {
                    if (selectedPreferences.size < 3 ) {
                        selectedPreferences.add(preferenceName)
                        Log.d("GamePlayed", "selectGamePlayedBio: $preferenceName")
                        button.setBackgroundColor(resources.getColor(R.color.green, null))
                    } else {
                        Toast.makeText(this, "You Can Only select up To 5 Games", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}