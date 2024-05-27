package com.kei037.edittext_to_kotiln

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // 전체 레이아웃
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )
        val commonParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )

        val baseLayout = LinearLayout(this)
        baseLayout.orientation = LinearLayout.VERTICAL
        setContentView(baseLayout, layoutParams)

        val editText = EditText(this)
        editText.hint = "여기에 입력하세요"
        baseLayout.addView(editText, commonParams)

        val btn1 = Button(this)
        btn1.text = "버튼입니다"
        btn1.setBackgroundColor(Color.YELLOW)
        baseLayout.addView(btn1, commonParams)

        val textView = TextView(this)
        textView.text = "텍스트뷰입니다"
        textView.setTextColor(Color.MAGENTA)
        textView.textSize = 20.0f
        baseLayout.addView(textView, commonParams)

        btn1.setOnClickListener {
            textView.text = editText.text.toString()
        }
    }
}