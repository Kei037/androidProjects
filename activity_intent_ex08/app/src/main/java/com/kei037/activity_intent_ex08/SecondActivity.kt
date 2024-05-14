package com.kei037.activity_intent_ex08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = "두 번째 엑티비티"

        val btnThird = findViewById<Button>(R.id.btnThird)
        val btnReturnFirst = findViewById<Button>(R.id.btnReturnFirst)

        btnThird.setOnClickListener {
            val intent = Intent(applicationContext, ThirdActivity::class.java)
            startActivity(intent)
        }

        btnReturnFirst.setOnClickListener {
            finish()
        }
    }
}