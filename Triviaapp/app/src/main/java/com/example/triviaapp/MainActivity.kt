package com.example.triviaapp

import com.example.triviaapp.MainActivity2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val letsStart : Button = findViewById(R.id.button)
        letsStart.setOnClickListener {
            val Intent = Intent (this, MainActivity2::class.java)
            startActivity(Intent)
        }
    }
}