package com.kei037.spinner_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "스피너 테스트"

        val movie = arrayOf("써니", "완득이", "괴물", "라디오 스타", "비열한 거리", "왕의 남자",
            "아일랜드", "웰컴 투 동막골", "헬보이", "백 투더 퓨처")

        val spinner = findViewById<Spinner>(R.id.spinner)

        val adapter: ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, movie)
        spinner.adapter = adapter

        val posterID = arrayOf(
            R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10
        )

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val imageView = findViewById<ImageView>(R.id.imageView)
                imageView.scaleType = ImageView.ScaleType.FIT_CENTER
                imageView.setPadding(5, 5, 5, 5)
                imageView.setImageResource(posterID[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}