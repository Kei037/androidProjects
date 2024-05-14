package com.kei037.widget_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.layout07)

        val btnPrev = findViewById<Button>(R.id.btnPrev)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val viewFlipper = findViewById<ViewFlipper>(R.id.viewFlipper1)

        btnPrev.setOnClickListener {
            viewFlipper.showPrevious()
        }
        btnNext.setOnClickListener {
            viewFlipper.showNext()
        }



//        setContentView(R.layout.layout03)

        // 자동 완성될 문자열을 배열로 정의
//        val items = arrayOf("CSI-뉴욕", "CSI-라스베가스", "CSI-마이애미", "Friend", "Fringe", "Lost")
//        // ArrayAdapter는 뷰와 데이터를 연결
//        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items)
//
//        val auto = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView1)
//        auto.setAdapter(adapter)
//
//        val multi = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView1)
//        val tokenizer = MultiAutoCompleteTextView.CommaTokenizer()
//        multi.setTokenizer(tokenizer)
//        multi.setAdapter(adapter)

    }
}