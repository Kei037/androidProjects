package com.kei037.activity_intent_ex05

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "메인 엑티비티"

        val editNum1 = findViewById<EditText>(R.id.editNum1)
        val editNum2 = findViewById<EditText>(R.id.editNum2)
        val btnNewActivity = findViewById<Button>(R.id.btnNewActivity)
        btnNewActivity.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("num1", editNum1.text.toString().toInt())
            intent.putExtra("num2", editNum2.text.toString().toInt())
            startActivityForResult(intent, 0)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            println("requestCode = " + requestCode)
            println("resultCode = " + resultCode)
            println("data = " + data)
            println("ActivityOk = " + Activity.RESULT_OK)
            val sum = data!!.getIntExtra("sum", 0)
            Toast.makeText(applicationContext, "합계 : ${sum}", Toast.LENGTH_SHORT).show()
        }
    }
}