package com.kei037.activity_intent_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ratingBar1 = findViewById<RatingBar>(R.id.ratingBar1)
        val ratingBar2 = findViewById<RatingBar>(R.id.ratingBar2)
        val ratingBar3 = findViewById<RatingBar>(R.id.ratingBar3)
        val btnIncrease = findViewById<Button>(R.id.btnIncrease)
        val btnDecrease = findViewById<Button>(R.id.btnDecrease)

        btnIncrease.setOnClickListener {
            ratingBar1.rating = ratingBar1.rating + ratingBar1.stepSize
            ratingBar2.rating = ratingBar2.rating + ratingBar2.stepSize
            ratingBar3.rating = ratingBar3.rating + ratingBar3.stepSize
        }
        btnDecrease.setOnClickListener {
            ratingBar1.rating = ratingBar1.rating - ratingBar1.stepSize
            ratingBar2.rating = ratingBar2.rating - ratingBar2.stepSize
            ratingBar3.rating = ratingBar3.rating - ratingBar3.stepSize
        }
    }
}