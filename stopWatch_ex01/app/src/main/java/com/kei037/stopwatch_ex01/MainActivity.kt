package com.kei037.stopwatch_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Timer
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var isRunning = false // 실행 여부 확인용 변수
    var timer: Timer? = null // 타이머 객체
    var time = 0

    private lateinit var btnStart: Button
    private lateinit var btnRefresh: Button
    private lateinit var tvMinute: TextView
    private lateinit var tvSecond: TextView
    private lateinit var tvMillisecond: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        btnRefresh = findViewById(R.id.btnRefresh)
        tvMinute = findViewById(R.id.tvMinute)
        tvSecond = findViewById(R.id.tvSecond)
        tvMillisecond = findViewById(R.id.tvMillisecond)

        btnStart.setOnClickListener(this)
        btnRefresh.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnStart -> {
                if (isRunning) {
                    pause()
                } else {
                    start()
                }
            }
            R.id.btnRefresh -> {
                refresh()
            }
        }
    }

    private fun start() {
        // 시작
        btnStart.text = "일시정지"
        btnStart.setBackgroundColor(getColor(R.color.red))
        isRunning = true

        // 0.01초마다 실행
        timer = timer(period = 10) {
            time++
            val milliSecond = time % 100 // 밀리초
            val second = (time % 6000) / 100 // 초
            val minute = time / 6000 // 분

            runOnUiThread {
                // UI 업데이트
                if (isRunning) {
                    tvMillisecond.text =
                        if (milliSecond < 10) ".0${milliSecond}" else ".${milliSecond}"
                    tvSecond.text = if (second < 10) ":0${second}" else ":${second}"
                    tvMinute.text = "${minute}"
                }
            }
        }
    }

    private fun pause() {
        btnStart.text = "시작"
        btnStart.setBackgroundColor(getColor(R.color.blue))

        // 스톱워치 측정을 일시정지 하는 로직
        isRunning = false // 멈춤 상태로 전환
        timer?.cancel() // 타이머 멈추기
    }

    private fun refresh() {
        // 초기화
        timer?.cancel()

        btnStart.text = "시작"
        btnStart.setBackgroundColor(getColor(R.color.blue))
        isRunning = false

        time = 0
        tvMillisecond.text = ".00"
        tvSecond.text = ":00"
        tvMinute.text = "00"
    }


}