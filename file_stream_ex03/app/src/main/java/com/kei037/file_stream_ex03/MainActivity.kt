package com.kei037.file_stream_ex03


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var btnPrev: Button
    lateinit var btnNext: Button
    lateinit var myPictureView: MyPictureView
    lateinit var textView: TextView
    var curIndex: Int = 1
    var imageFiles: Array<File>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "간단 이미지 뷰어"
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Context.MODE_PRIVATE
        )

        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)
        myPictureView = findViewById(R.id.myPictureView)
        textView = findViewById(R.id.textView)

        imageFiles = File(Environment.getExternalStorageDirectory()
            .absolutePath + "/Pictures").listFiles()

        val lastIndex = imageFiles!!.size - 1
        textView.setText("${curIndex}/${lastIndex}")

        // 파일 목록 출력
        for (i in imageFiles!!.indices) {
            val fileName = if (imageFiles!![i].isDirectory)
                "<폴더> " + imageFiles!![i].toString()
            else
                "<파일> " + imageFiles!![i].toString()
            println(fileName)
        }

        myPictureView.imagePath = imageFiles!![curIndex].toString()

        btnPrev.setOnClickListener {
            if (curIndex <= 1) {
                Toast.makeText(applicationContext, "첫번째 그림입니다", Toast.LENGTH_SHORT).show()
            } else {
                myPictureView.imagePath = imageFiles!![--curIndex].toString()
                myPictureView.invalidate() // 이미지 갱신
                textView.setText("${curIndex}/${lastIndex}")
            }
        }
        btnNext.setOnClickListener {
            if (curIndex >= imageFiles!!.size - 1) {
                Toast.makeText(applicationContext, "마지막 그림입니다", Toast.LENGTH_SHORT).show()
            } else {
                myPictureView.imagePath = imageFiles!![++curIndex].toString()
                myPictureView.invalidate() // 이미지 갱신
                textView.setText("${curIndex}/${lastIndex}")
            }
        }
    }
}