package com.kei037.alert_dialog_ex01

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)

        button.setOnClickListener {
            val versionArray = arrayOf("오레오", "파이", "큐(10)") // 문자열 array생성
            val checkArray = booleanArrayOf(true, false, false) // boolean형 array생성
            // Builder로 AlertDialog 객체 생성
            val dlg = AlertDialog.Builder(this@MainActivity)

            // 타이틀, 메시지, 아이콘 지정
            dlg.setTitle("좋아하는 버전은?.")
            dlg.setIcon(R.mipmap.ic_launcher)

            // 라디오 버튼 추가, 버튼의 텍스트를 클릭한 라디오버튼의 텍스트로 변경
//            dlg.setSingleChoiceItems(versionArray, 0) { dialog, which ->
//                button.text = versionArray[which]
//            }

            // 체크 버튼 추가, boolean 값들을 넣어서 만듬
            dlg.setMultiChoiceItems(versionArray, checkArray) { dialog, which, ischecked ->
                button.text = versionArray[which]
            }


            // AlertDialog에 확인버튼 추가
            dlg.setPositiveButton("닫기", null) // listener에는 어떤 동작을 할건지

            // show로 보이게 띄움
            dlg.show()
        }
    }
}