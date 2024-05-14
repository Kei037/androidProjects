package com.kei037.alert_dialog_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var textViewName: TextView
    lateinit var textViewEmail: TextView
    lateinit var button: Button
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var toastText1: TextView
    lateinit var dialogView: View
    lateinit var toastView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "사용자 정보 입력"

        textViewName = findViewById(R.id.textViewName)
        textViewEmail = findViewById(R.id.textViewEmail)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            // dialogView 에 dialog1레이아웃 적용
            dialogView = View.inflate(this@MainActivity, R.layout.dialog1, null)

            // AlertDialog 객체 생성
            val dialog = AlertDialog.Builder(this@MainActivity)
            // 타이틀, 아이콘 적용
            dialog.setTitle("사용자 정보 입력")
            dialog.setIcon(R.drawable.ic_menu_allfriends)

            // dialog 에 dialogView 화면을 넣음
            dialog.setView(dialogView)

            // setPositiveButton(오른쪽), setNegativeButton(왼쪽) 버튼 추가 (뜻 보다 위치를 지정)
            // 람다식으로 작성, 확인뒤에 오는것은 작동할 기능 (alert창(dialog1.xml)의 editText를 textView에 입력
            dialog.setPositiveButton("확인") { dlog, which ->
                editText1 = dialogView.findViewById(R.id.editText1)
                editText2 = dialogView.findViewById(R.id.editText2)

                textViewName.text = editText1.text.toString()
                textViewEmail.text = editText2.text.toString()
            }
            dialog.setNegativeButton("취소") { dlog, which ->
                // 토스트객체 생성
                val toast = Toast(this@MainActivity)
                // toastView 안에 toast1.xml을 View 객체로 변경해서 넣고, 설정 후 위에 만든 toast에 넣음
                toastView = View.inflate(this@MainActivity, R.layout.toast1, null)
                toastText1 = toastView.findViewById(R.id.toastText1)
                toastText1.text = "취소했습니다"
                toast.view = toastView
                toast.show()
            }

            // alert창 띄우기
            dialog.show()
        }
    }
}