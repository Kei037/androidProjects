package com.kei037.date_time_ex01

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var chronometer1 : Chronometer
    lateinit var btnStart : Button
    lateinit var rdoCal : RadioButton
    lateinit var rdoTime : RadioButton
    lateinit var calendarView1 : CalendarView
    lateinit var timePicker1 : TimePicker
    lateinit var btnEnd : Button
    lateinit var tvYear : TextView
    lateinit var tvMonth : TextView
    lateinit var tvDay : TextView
    lateinit var tvHour : TextView
    lateinit var tvMinute : TextView
    var selectYear : Int = 0
    var selectMonth : Int = 0
    var selectDay : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer1 = findViewById(R.id.chronometer1)
        btnStart = findViewById(R.id.btnStart)
        rdoCal = findViewById(R.id.rdoCal)
        rdoTime = findViewById(R.id.rdoTime)
        calendarView1 = findViewById(R.id.calendarView1)
        timePicker1 = findViewById(R.id.timePicker1)
        btnEnd = findViewById(R.id.btnEnd)
        tvYear = findViewById(R.id.tvYear)
        tvMonth = findViewById(R.id.tvMonth)
        tvDay = findViewById(R.id.tvDay)
        tvHour = findViewById(R.id.tvHour)
        tvMinute = findViewById(R.id.tvMinute)

        // 처음 2개 안보이게 설정
        timePicker1.visibility = View.INVISIBLE
        calendarView1.visibility = View.INVISIBLE

        rdoCal.setOnClickListener {
            timePicker1.visibility = View.INVISIBLE
            calendarView1.visibility = View.VISIBLE
        }

        rdoTime.setOnClickListener {
            timePicker1.visibility = View.VISIBLE
            calendarView1.visibility = View.INVISIBLE
        }

        // 타이머 설정
        btnStart.setOnClickListener {
            chronometer1.base = SystemClock.elapsedRealtime()
            chronometer1.start()
            chronometer1.setTextColor(Color.RED)
        }
        // 버튼을 클릭하면 날짜, 시간을 가져온다.
        btnEnd.setOnClickListener {
            chronometer1.stop()
            chronometer1.setTextColor(Color.BLUE)

            tvYear.text = selectYear.toString()
            tvMonth.text = selectMonth.toString()
            tvDay.text = selectDay.toString()

            tvHour.text = timePicker1.currentHour.toString()
            tvMinute.text = timePicker1.currentMinute.toString()
        }
        
        // 캘린더를 눌러 날짜를 바꿀때 저장
        calendarView1.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectYear = year
            selectMonth = month + 1
            selectDay = dayOfMonth
        }

    }
}