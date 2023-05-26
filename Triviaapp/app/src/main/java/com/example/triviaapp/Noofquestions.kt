package com.example.triviaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

//import com.example.triviaapp.MainActivity2.Companion.flag

class Noofquestions : AppCompatActivity() {
    companion object {
        var nextflag = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noofquestions)
        val easy: Button = findViewById(R.id.easy)
        val medium: Button = findViewById(R.id.medium)
        val hard: Button = findViewById(R.id.hard)
        val intent = Intent(this, Questions::class.java)
        easy.setOnClickListener {
            nextflag = 1
            startActivity(intent)
        }
        medium.setOnClickListener {
            nextflag = 2
            startActivity(intent)
        }
        hard.setOnClickListener {
            nextflag = 3
            startActivity(intent)
        }

    }
}
