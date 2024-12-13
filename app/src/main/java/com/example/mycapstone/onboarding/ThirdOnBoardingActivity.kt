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
import com.example.mycapstone.databinding.ActivityThirdOnBoardingBinding

class ThirdOnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdOnBoardingBinding

    private val selectedPreferences = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, FourthOnBoardingActivity::class.java)
            startActivity(intent)
        }

        selectedPreferences()
    }


    private fun selectedPreferences() {
        val buttons = listOf(
            binding.btnLuxury,
            binding.btnLow,
            binding.btnMountains,
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