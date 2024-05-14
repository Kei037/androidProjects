package com.kei037.calc_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editText01 : EditText
    lateinit var editText02 : EditText
    lateinit var btnAdd : Button
    lateinit var btnSub : Button
    lateinit var btnMul : Button
    lateinit var btnDiv : Button
    lateinit var btnRem : Button
    lateinit var textViewResult : TextView
    lateinit var num1 : String
    lateinit var num2 : String
    var result : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "초간단 계산기"

        editText01 = findViewById(R.id.editText01)
        editText02 = findViewById(R.id.editText02)
        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnMul = findViewById(R.id.btnMul)
        btnDiv = findViewById(R.id.btnDiv)
        btnRem = findViewById(R.id.btnRem)
        textViewResult = findViewById(R.id.textViewResult)

        btnAdd.setOnClickListener {
            num1 = editText01.text.toString()
            num2 = editText02.text.toString()
            if (isEmpty(num1, num2)) {
                return@setOnClickListener
            }
            result = num1.toDouble() + num2.toDouble()
            textViewResult.setText("계산 결과: ${result}")
        }

        btnSub.setOnClickListener {
            num1 = editText01.text.toString()
            num2 = editText02.text.toString()
            if (isEmpty(num1, num2)) {
                return@setOnClickListener
            }
            result = num1.toDouble() - num2.toDouble()
            textViewResult.setText("계산 결과: ${result}")
        }

        btnMul.setOnClickListener {
            num1 = editText01.text.toString()
            num2 = editText02.text.toString()
            if (isEmpty(num1, num2)) {
                return@setOnClickListener
            }
            result = num1.toDouble() * num2.toDouble()
            textViewResult.setText("계산 결과: ${result}")
        }

        btnDiv.setOnClickListener {
            num1 = editText01.text.toString()
            num2 = editText02.text.toString()
            if (isEmpty(num1, num2)) {
                return@setOnClickListener
            }
            if (num2.equals("0")) {
                Toast.makeText(applicationContext, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            result = num1.toDouble() / num2.toDouble()
            textViewResult.setText("계산 결과: ${result}")
        }

        btnRem.setOnClickListener {
            num1 = editText01.text.toString()
            num2 = editText02.text.toString()
            if (isEmpty(num1, num2)) {
                return@setOnClickListener
            }
            result = num1.toDouble() % num2.toDouble()
            textViewResult.setText("계산 결과: ${result}")
        }

    }
    private fun isEmpty(num1: String, num2: String): Boolean {
        return if (num1.trim() == "" || num2.trim() == "") {
            Toast.makeText(applicationContext, "입력값이 비었습니다.", Toast.LENGTH_SHORT).show()
            true
        } else {
            false
        }
    }
}