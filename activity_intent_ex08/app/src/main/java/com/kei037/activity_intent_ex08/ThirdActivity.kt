package com.kei037.activity_intent_ex08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        title = "세 번째 엑티비티"

        val btnReturnSecond = findViewById<Button>(R.id.btnReturnSecond)

        btnReturnSecond.setOnClickListener {
            finish()
        }
    }
}