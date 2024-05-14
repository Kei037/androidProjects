package com.kei037.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var button1 : Button
    // 멤버변수(전역변수)는 선언하면 초기화 해야함
    // 만약 값을 나중에 대입하려면 앞에 lateinit를 붙임.
    // 즉 초기화하지 않은 멤버변수 앞에는 항상 lateinit을 붙임.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            Toast.makeText(applicationContext, "버튼을 눌렀어요.", Toast.LENGTH_SHORT).show() }

    }
}