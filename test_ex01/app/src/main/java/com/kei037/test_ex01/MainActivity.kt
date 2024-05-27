package com.kei037.test_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)

        // 이벤트 발새이셍 동작중인 토스트 메시지를 끄기 위해 이벤트 리스너 바깥에 변수 선언
        var toast: Toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)
        // addTextChangedListener -> 에디트 텍스트의 텍스트가 변경됨
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 텍스트가 변하기 전에 동작
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 텍스트가 변경될 때 동작
                val inputText = editText.text.toString()
                toast.cancel() // 동작중인 토스트를 취소
                toast = Toast.makeText(applicationContext, inputText, Toast.LENGTH_SHORT)
                toast.show()
            }

            override fun afterTextChanged(p0: Editable?) {
                // 텍스트가 변경된 후 동작
            }

        })
    }
}