package com.kei037.project_checkbox_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var checkbox1 : CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkbox1 = findViewById(R.id.checkBox1)
        checkbox1.setOnClickListener {
            Toast.makeText(applicationContext, "체크박스입니다.", Toast.LENGTH_SHORT).show() }
    }
}