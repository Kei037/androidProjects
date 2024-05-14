package com.kei037.simple_diary_ex02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var datePicker: DatePicker
    lateinit var editDiary: EditText
    lateinit var btnWrite: Button
    lateinit var fileName: String
    lateinit var savePath: String
    var isFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Context.MODE_PRIVATE
        )

        title = "간단 일기장"

        datePicker = findViewById(R.id.dataPicker)
        editDiary = findViewById(R.id.editDiary)
        btnWrite = findViewById(R.id.btnWrite)

        val calender = Calendar.getInstance()
        val calendarYear = calender.get(Calendar.YEAR)
        val calendarMonth = calender.get(Calendar.MONTH)
        val calendarDay = calender.get(Calendar.DAY_OF_MONTH)

        // 저장 파일 경로 지정, 경로에 저장할 디렉토리가 없으면 추가
        savePath = Environment.getExternalStorageDirectory().absolutePath + "/MyDiary"
        makeDir() // 저장 함수화

        fileName = "${calendarYear}_${calendarMonth + 1}_${calendarDay}.txt"
        val str = readDiary(fileName)
        editDiary.setText(str)
        btnWrite.isEnabled = true


        datePicker.init(
            calendarYear,
            calendarMonth,
            calendarDay,
            DatePicker.OnDateChangedListener() { datePicker: DatePicker, year: Int, month: Int, day: Int ->
                makeDir()
                fileName = "${year}_${month + 1}_${day}.txt"
                val string = readDiary(fileName) // 날짜에 해당하는 일기 파일을 읽기
//                println(fileName)
                editDiary.setText(string) // 에디트텍스트에 일기 내용을 출력
                btnWrite.isEnabled = true // 버튼 활성화
            }
        )

        btnWrite.setOnClickListener {
            val outputStream = FileOutputStream("$savePath/$fileName")
            val string = editDiary.text.toString()
            outputStream.write(string.toByteArray()) // 앱 내부 저장소에 저장
            outputStream.close()
            Toast.makeText(applicationContext, "${fileName} 이 저장됨", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makeDir() {
        if (!isFlag) {
            // 저장 경로 생성
            val myDir = File(savePath)
            myDir.mkdir()
            isFlag = true
        }
    }


    private fun readDiary(fileName: String): String? {
        var diaryStr: String? = null
        val inputStream: FileInputStream

        try {
            inputStream = FileInputStream("$savePath/$fileName")
            val txt = ByteArray(inputStream.available()) // 글자 수 만큼 크기 지정
            inputStream.read(txt)
            inputStream.close()
            diaryStr = txt.toString(Charsets.UTF_8).trim()
            btnWrite.text = "수정하기"
        } catch (e: IOException) {
            editDiary.hint = "일기 없음"
            btnWrite.text = "새로 저장"
        }
        return diaryStr
    }

}