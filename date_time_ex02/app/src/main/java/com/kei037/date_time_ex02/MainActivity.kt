package com.kei037.date_time_ex02

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var chronometer1 : Chronometer
    lateinit var rdoDate : RadioButton
    lateinit var rdoTime : RadioButton
    lateinit var datePicker1: DatePicker
    lateinit var timePicker1 : TimePicker
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
        rdoDate = findViewById(R.id.rdoDate)
        rdoTime = findViewById(R.id.rdoTime)
        datePicker1 = findViewById(R.id.datePicker1)
        timePicker1 = findViewById(R.id.timePicker1)
        tvYear = findViewById(R.id.tvYear)
        tvMonth = findViewById(R.id.tvMonth)
        tvDay = findViewById(R.id.tvDay)
        tvHour = findViewById(R.id.tvHour)
        tvMinute = findViewById(R.id.tvMinute)

        init()

        // 라디오 버튼 보이기
        chronometer1.setOnClickListener {
            rdoDate.visibility = View.VISIBLE
            rdoTime.visibility = View.VISIBLE
        }

        // 데이터피커, 타임피커 보이기
        rdoDate.setOnClickListener {
            timePicker1.visibility = View.INVISIBLE
            datePicker1.visibility = View.VISIBLE
        }
        rdoTime.setOnClickListener {
            timePicker1.visibility = View.VISIBLE
            datePicker1.visibility = View.INVISIBLE
        }

        chronometer1.setOnLongClickListener {
            chronometer1.base = SystemClock.elapsedRealtime()
            chronometer1.start()
            chronometer1.setTextColor(Color.RED)
            return@setOnLongClickListener(true)
        }

        tvYear.setOnLongClickListener {
            chronometer1.stop()
            chronometer1.setTextColor(Color.BLUE)

            tvYear.text = selectYear.toString()
            tvMonth.text = selectMonth.toString()
            tvDay.text = selectDay.toString()

            tvHour.text = timePicker1.hour.toString()
            tvMinute.text = timePicker1.minute.toString()
            return@setOnLongClickListener(true)
        }

        datePicker1.setOnDateChangedListener { view, year, month, dayOfMonth ->
            selectYear = year
            selectMonth = month + 1
            selectDay = dayOfMonth
        }
    }
    fun init() {
        rdoDate.isChecked = false
        rdoTime.isChecked = false
        rdoDate.visibility = View.INVISIBLE
        rdoTime.visibility = View.INVISIBLE
        timePicker1.visibility = View.INVISIBLE
        datePicker1.visibility = View.INVISIBLE
    }
}