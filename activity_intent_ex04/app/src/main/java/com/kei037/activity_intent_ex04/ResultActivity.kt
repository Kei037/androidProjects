package com.kei037.activity_intent_ex04

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        // MainActivity에서 보낸 투표 결과 값을 받음
        title = "투표결과"

        initializeUI()
    }

    override fun onResume() {
        super.onResume()
        initializeUI()
    }

    private fun initializeUI() {
        val intent = intent
        val voteCounts = intent.getIntArrayExtra("voteCounts")
        val imageNames = intent.getStringArrayExtra("imageNames")

        // 객체 배열 생성
        val textViews = arrayOfNulls<TextView>(9)
        val ratingBars = arrayOfNulls<RatingBar>(9)

        val textViewBest = findViewById<TextView>(R.id.textViewBest)
        val imageViewBest = findViewById<ImageView>(R.id.imageViewBest)

        // 텍스트뷰 id 배열
        val textViewIds = arrayOf(
            R.id.textView1, R.id.textView2, R.id.textView3,
            R.id.textView4, R.id.textView5, R.id.textView6,
            R.id.textView7, R.id.textView8, R.id.textView9
        )

        // 이미지 이름 문자열 배열
        val ratingBarIds = arrayOf(
            R.id.ratingBar1, R.id.ratingBar2, R.id.ratingBar3,
            R.id.ratingBar4, R.id.ratingBar5, R.id.ratingBar6,
            R.id.ratingBar7, R.id.ratingBar8, R.id.ratingBar9
        )

        // 이미지 배열
        val imageIds = arrayOf(
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        )

        var idxBest = 0

        for (i in voteCounts!!.indices) {
            textViews[i] = findViewById(textViewIds[i])
            textViews[i]!!.text = imageNames!![i]

            ratingBars[i] = findViewById(ratingBarIds[i])
            ratingBars[i]!!.rating = voteCounts[i].toFloat()

            if (voteCounts[i] > idxBest) {
                idxBest = i
            }
        }

        textViewBest.text = imageNames?.get(idxBest)
        imageViewBest.setImageResource(imageIds[idxBest])

        val btnReturn = findViewById<Button>(R.id.btnReturn)
        btnReturn.setOnClickListener {
            finish()
        }
    }
}
