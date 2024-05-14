package com.kei037.file_stream_ex01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        Part 1
        setContentView(R.layout.activity_main)

        val btnWrite = findViewById<Button>(R.id.btnWrite)
        val btnRead = findViewById<Button>(R.id.btnRead)

        btnWrite.setOnClickListener {
            // openFileOutput 객체 생성, file.txt파일 생성,  MODE_PRIVATE: 생성한 앱에서만 사용가능
            val outputStream = openFileOutput("file.txt", Context.MODE_PRIVATE)
            val string = "파일 처리 테스트"
            outputStream.write(string.toByteArray()) // file.txt파일에 string 문자열 입력
            outputStream.close()                     // 닫기
            Toast.makeText(applicationContext, "file.txt가 생성됨.", Toast.LENGTH_SHORT).show()
        }

        btnRead.setOnClickListener {
            try {
                // openFileInput로 file.txt 불러옴
                val inputStream = openFileInput("file.txt")
                val txt = ByteArray(30)
                inputStream.read(txt)  // 읽기
                val string = txt.toString(Charsets.UTF_8)
                Toast.makeText(applicationContext, string, Toast.LENGTH_SHORT).show()
            } catch (e : Exception) {
                Toast.makeText(applicationContext, "파일 없음", Toast.LENGTH_SHORT).show()
            }
        }
         */
        /*
        // Part 2 raw폴더의 txt파일 읽기
        setContentView(R.layout.layout01)

        val btnRead = findViewById<Button>(R.id.btnRead)
        val editRaw = findViewById<EditText>(R.id.editRaw)

        btnRead.setOnClickListener {
            // res > raw > text.txt 파일 불러와 내용을 editText에 넣음
            val inputStream = resources.openRawResource(R.raw.text)
            val txt = ByteArray(inputStream.available())
            inputStream.read(txt)
            editRaw.setText(txt.toString(Charsets.UTF_8))
            inputStream.close()
        }

         */
        /*
        // Part 3 SD카드 파일
        setContentView(R.layout.layout02)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Context.MODE_PRIVATE
        )

        val btnRead = findViewById<Button>(R.id.btnRead)
        val editSD = findViewById<EditText>(R.id.editSD)

        btnRead.setOnClickListener {
            try {
                // 경로 지정
                val inputStream = FileInputStream("/storage/emulated/0/DownLoad/test.txt")
                val txt = ByteArray(inputStream.available()) // 글자 길이만큼 불러옴
                inputStream.read(txt)
                editSD.setText(txt.toString(Charsets.UTF_8))
                inputStream.close()
            } catch (e: IOException) {
                println(e.printStackTrace())
                Toast.makeText(applicationContext, "에러", Toast.LENGTH_SHORT).show()
            }
        }
         */
        /*
        // Part 4 SD카드 저장소에 디렉토리 생성, 삭제
        setContentView(R.layout.layout03)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Context.MODE_PRIVATE
        )

        val btnMkdir = findViewById<Button>(R.id.btnMkDir)
        val btnRmdir = findViewById<Button>(R.id.btnRmDir)

        // SD카드의 절대경로를 돌려줌, 환경변수 밑에 /MyDir 생성하도록 경로 지정
        val strSDpath = Environment.getExternalStorageDirectory().absolutePath
        val myDir = File("${strSDpath}/MyDir")

        // 버튼을 누르면 경로에 디렉토리 생성, 삭제
        btnMkdir.setOnClickListener {
            myDir.mkdir()
        }
        btnRmdir.setOnClickListener {
            myDir.delete()
        }
         */

        // Part 5 시스템 폴더의 폴더.파일 목록 출력
        setContentView(R.layout.layout04)
        val btnFileList = findViewById<Button>(R.id.btnFileList)
        val editFileList = findViewById<EditText>(R.id.editFileList)

        btnFileList.setOnClickListener {
            val sysDir = Environment.getRootDirectory().absolutePath
            val sysFiles = File(sysDir).listFiles()

            var strFname: String
            for (i in sysFiles.indices) {
                if (sysFiles[i].isDirectory == true) {
                    strFname = "<폴더> " + sysFiles[i].toString()
                } else {
                    strFname = "<파일> " + sysFiles[i].toString()
                }
                editFileList.setText(editFileList.text.toString() + "\n" + strFname)
            }
        }
    }
}