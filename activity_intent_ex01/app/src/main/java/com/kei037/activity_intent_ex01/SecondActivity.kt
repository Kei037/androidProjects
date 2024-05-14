package com.kei037.activity_intent_ex01

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)

        val btnReturn: Button = findViewById(R.id.btnReturn)
        btnReturn.setOnClickListener {
            finish()
        }
    }
}