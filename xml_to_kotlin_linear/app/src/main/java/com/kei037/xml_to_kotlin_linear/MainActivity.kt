package com.kei037.xml_to_kotlin_linear

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        // 전체 레이아웃
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )
        // 상단 레이아웃
        val paramsSub = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1.0f
        )
        // 기본 레이아웃
        val baseLayout = LinearLayout(this)
        baseLayout.orientation = LinearLayout.VERTICAL
        setContentView(baseLayout, params)

        // 바깥쪽 레이아웃 위 등록
        val firstOutLayout = LinearLayout(this)
        firstOutLayout.layoutParams = paramsSub
        firstOutLayout.orientation = LinearLayout.HORIZONTAL
        baseLayout.addView(firstOutLayout)

        // 바깥쪽 레이아웃 아래 등록 (블루)
        val secondOutLayout = LinearLayout(this)
        secondOutLayout.layoutParams = paramsSub
        secondOutLayout.setBackgroundColor(Color.rgb(0, 0, 255))
        baseLayout.addView(secondOutLayout)

        // 상단 레이아웃안의 왼쪽 레이아웃
        val redLayout = LinearLayout(this)
        redLayout.layoutParams = paramsSub
        redLayout.setBackgroundColor(Color.RED)
        firstOutLayout.addView(redLayout)

        val firstChildLayout = LinearLayout(this)
        firstChildLayout.layoutParams = paramsSub
        firstChildLayout.orientation = LinearLayout.VERTICAL
        firstOutLayout.addView(firstChildLayout)

        // firstChildLayout에 노란색과 검은색 레이아웃 추가
        val yellowLayout = LinearLayout(this)
        yellowLayout.layoutParams = paramsSub
        yellowLayout.setBackgroundColor(Color.rgb(255, 255, 0))
        firstChildLayout.addView(yellowLayout)

        val blackLayout = LinearLayout(this)
        blackLayout.layoutParams = paramsSub
        blackLayout.setBackgroundColor(Color.rgb(0, 0, 0))
        firstChildLayout.addView(blackLayout)
    }
}