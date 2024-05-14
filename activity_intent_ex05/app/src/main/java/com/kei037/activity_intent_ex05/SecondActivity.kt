package com.kei037.activity_intent_ex05

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
        val sum = intent.getIntExtra("num1", 0) + intent.getIntExtra("num2", 0)

        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            val intentResult = Intent(applicationContext, MainActivity::class.java)
            intentResult.putExtra("sum", sum)
            setResult(Activity.RESULT_OK, intentResult)
            finish()
        }
    }


}