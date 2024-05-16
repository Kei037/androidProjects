package com.kei037.simple_diary_ex03_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var datePicker: DatePicker
    lateinit var editDiary: EditText
    lateinit var btnWrite: Button

    lateinit var myHelper: MyDBHelper
    lateinit var sqlDB: SQLiteDatabase
    lateinit var diaryDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "간단 일기장 SQLite.ver"

        datePicker = findViewById(R.id.dataPicker)
        editDiary = findViewById(R.id.editDiary)
        btnWrite = findViewById(R.id.btnWrite)

        val calender = Calendar.getInstance()
        val calendarYear = calender.get(Calendar.YEAR)
        val calendarMonth = calender.get(Calendar.MONTH)
        val calendarDay = calender.get(Calendar.DAY_OF_MONTH)

        myHelper = MyDBHelper(this)
        sqlDB = myHelper.writableDatabase

        diaryDate = "${calendarYear}_${calendarMonth + 1}_${calendarDay}"
        val str = readDiary(diaryDate)
        editDiary.setText(str)
        btnWrite.isEnabled = true

        datePicker.init(
            calendarYear,
            calendarMonth,
            calendarDay,
            DatePicker.OnDateChangedListener() { datePicker: DatePicker, year: Int, month: Int, day: Int ->
                diaryDate = "${year}_${month + 1}_${day}"
                val string = readDiary(diaryDate)
                editDiary.setText(string)
                btnWrite.isEnabled = true
            }
        )

        btnWrite.setOnClickListener {
            val string = editDiary.text.toString()
            saveDiary(diaryDate, string)
            Toast.makeText(applicationContext, "${diaryDate} 이 저장됨", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readDiary(diaryDate: String): String? {
        var diaryStr: String? = null
        val cursor: Cursor = sqlDB.rawQuery("SELECT content FROM my_diary WHERE diary_date = '$diaryDate';", null)

        if (cursor.moveToFirst()) {
            diaryStr = cursor.getString(0)
            btnWrite.text = "수정하기"
        } else {
            editDiary.hint = "일기 없음"
            btnWrite.text = "새로 저장"
        }
        cursor.close()
        return diaryStr
    }

    private fun saveDiary(diaryDate: String, content: String) {
        val contentValues = ContentValues()
        contentValues.put("diary_date", diaryDate)
        contentValues.put("content", content)

        val result = sqlDB.insertWithOnConflict("my_diary", null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
        if (result == -1L) {
            Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show()
        }
    }

    inner class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "groupDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE my_diary (diary_date CHAR(10) PRIMARY KEY, content VARCHAR(500));")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS my_diary")
            onCreate(db)
        }
    }
}
