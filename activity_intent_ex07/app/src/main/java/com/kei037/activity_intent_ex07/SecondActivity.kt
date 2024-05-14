package com.kei037.activity_intent_ex07

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = "Second 엑티비티"

        // intent 관련 처리
        val intent = intent
        val checkRadio = intent.getStringExtra("checkRadio")
        val num1 = intent.getIntExtra("num1", 0)
        val num2 = intent.getIntExtra("num2", 0)
        var result = 0

        when (checkRadio) {
            "sum" -> {
                result = num1 + num2
            }
            "min" -> {
                result = num1 - num2
            }
            "mul" -> {
                result = num1 * num2
            }
            "div" -> {
                result = num1 / num2
            }
        }

        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            val intentResult = Intent(applicationContext, MainActivity::class.java)
            intentResult.putExtra("result", result)
            setResult(Activity.RESULT_OK, intentResult)
            finish()
        }
    }
}