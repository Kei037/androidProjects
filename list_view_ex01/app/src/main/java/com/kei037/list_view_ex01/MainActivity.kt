package com.kei037.list_view_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "리스트뷰 테스트"

        /*
        // part1
        val listView = findViewById<View>(R.id.listView) as ListView

        val mid = arrayOf("홍콩", "타이페이", "타이난", "가오슝", "후쿠오카",
        "쿄토", "오사카", "구마모토", "히로시마", "하노이",
        "호치민", "다낭", "하롱베이", "싱가폴", "마카오")

        val adapter: ArrayAdapter<String> = ArrayAdapter(this,
        android.R.layout.simple_list_item_multiple_choice, mid)

        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(applicationContext, mid[i], Toast.LENGTH_SHORT).show()
        }

         */

        val midList = mutableListOf<String>()
        val listView = findViewById<ListView>(R.id.listView)

        val adapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_multiple_choice, midList)
        listView.adapter = adapter

        val editItem = findViewById<EditText>(R.id.editItem)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            midList.add(editItem.text.toString())
            adapter.notifyDataSetChanged()
        }
        listView.setOnItemLongClickListener { parent, view, position, id ->
            midList.removeAt(position)
            adapter.notifyDataSetChanged()
            false
        }
    }
}