package com.kei037.view_binding_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kei037.view_binding_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 흭득
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // 액티비티 화면 출력
        setContentView(binding.root)

        // 뷰 객체 이용
        binding.visibleBtn.setOnClickListener {
            binding.targetView.visibility = View.VISIBLE
        }
        binding.invisibleBtn.setOnClickListener {
            binding.targetView.visibility = View.INVISIBLE
        }
    }
}