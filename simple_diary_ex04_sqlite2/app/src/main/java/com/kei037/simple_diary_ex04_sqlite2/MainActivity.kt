package com.kei037.simple_diary_ex04_sqlite2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var datePicker: DatePicker
    lateinit var myHelper: MyDBHelper
    lateinit var editDiary: EditText
    lateinit var btnWrite: Button
    lateinit var diaryDate: String
    var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "간단한 일기장"
        myHelper = MyDBHelper(this)

        datePicker = findViewById(R.id.datePicker)
        editDiary = findViewById(R.id.editDiary)
        btnWrite = findViewById(R.id.btnWrite)

        initBtnWrite()
        initDatePicker()
    }

    private fun initDatePicker(){
        val calendar = Calendar.getInstance()
        val calendarYear = calendar.get(Calendar.YEAR)
        val calendarMonth = calendar.get(Calendar.MONTH)
        val calendarDay = calendar.get(Calendar.DAY_OF_MONTH)

        datePicker.init(
            calendarYear,
            calendarMonth,
            calendarDay,
            DatePicker.OnDateChangedListener { datePicker: DatePicker, year: Int, month: Int, day: Int ->
                diaryDate = "${year}-${month+1}-${day}"
                val string = readDiary(diaryDate)
                editDiary.setText(string)
                btnWrite.isEnabled = true
            });
    }

    private fun readDiary(diaryDate: String): String? {
        var diaryStr: String? = myHelper.selectDiary(diaryDate)
        if (diaryStr == null) {
            editDiary.hint = "일기 없음"
            btnWrite.text = "새로저장"
        } else {
            btnWrite.text = "수정하기"
        }

        return diaryStr
    }

    private fun initBtnWrite(){
        // 저장 버튼 이벤트 리스너 등록
        btnWrite.setOnClickListener {
            val content = editDiary.text.toString()
            myHelper.insertDiary(Diary(diaryDate, content))
            Toast.makeText(applicationContext,"입력됨", Toast.LENGTH_SHORT).show()
        }
    }

}