package com.example.mycapstone.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycapstone.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)

        val buttonStarted: Button = findViewById(R.id.getStartedButton)
        buttonStarted.setOnClickListener {
            val intent = Intent(this, FirstOnBoardingActivity::class.java)
            startActivity(intent)
        }
    }
}