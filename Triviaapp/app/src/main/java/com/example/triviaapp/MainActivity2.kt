package com.example.triviaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity


class MainActivity2 : AppCompatActivity() {
   companion object {
       var flag = -1
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()
        val sports : Button = findViewById(R.id.button6)
        val videogames : Button = findViewById(R.id.button7)
        val movies : Button = findViewById(R.id.button8)
        val computers : Button = findViewById(R.id.button9)
        val anime : Button = findViewById(R.id.button2)
        val mythology : Button = findViewById(R.id.button3)
        val newintent = Intent (this, Noofquestions::class.java)
        sports.setOnClickListener {
            flag=1
            newintent.putExtra("key", flag)
            startActivity(newintent)
        }
        videogames.setOnClickListener {
            flag=2
           newintent.putExtra("key", flag)
            startActivity(newintent)
        }
        movies.setOnClickListener {
            flag=3
          newintent.putExtra("key", flag)
            startActivity(newintent)
        }
        computers.setOnClickListener {
            flag=4
            newintent.putExtra("key", flag)
            startActivity(newintent)
        }
        anime.setOnClickListener {
            flag=5
           newintent.putExtra("key", flag)
            startActivity(newintent)
        }
        mythology.setOnClickListener {
            flag=6
            newintent.putExtra("key", flag)
            startActivity(newintent)
        }



// Second Activity


    }
}
