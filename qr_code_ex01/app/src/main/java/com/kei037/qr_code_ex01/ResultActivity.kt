package com.kei037.qr_code_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kei037.qr_code_ex01.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getStringExtra("msg") ?: "데이터가 존재하지 않습니다."
        setUI(result)
    }

    private fun setUI(result: String) {
        // 넘어온 QR 코드 속 데이터를 텍스트뷰에 설정
        binding.textViewContent.text = result
        // 돌아가기 버튼을 클릭했을때 ResultActivity 종료
        binding.btnGoBack.setOnClickListener {
            finish()
        }
    }

}