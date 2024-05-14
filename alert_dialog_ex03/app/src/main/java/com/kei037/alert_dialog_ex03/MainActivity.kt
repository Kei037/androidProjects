package com.kei037.alert_dialog_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var dialogView: View
    private val checkId = "admin"
    private val checkPw = "1111"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "로그인"

        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            dialogView = View.inflate(this@MainActivity, R.layout.dialog1, null)

            val dialog = AlertDialog.Builder(this@MainActivity)
            dialog.setTitle("로그인 정보 입력")

            dialog.setView(dialogView)

            dialog.setPositiveButton("로그인") { dlog, which ->
                editText1 = dialogView.findViewById(R.id.editText1)
                editText2 = dialogView.findViewById(R.id.editText2)

                val toast = Toast(this@MainActivity)

                if (editText1.text.toString() == checkId && editText2.text.toString() == checkPw) {
                    toast.setText("로그인 성공")
                    toast.show()
                } else {
                    toast.setText("로그인 실패")
                    toast.show()
                }
            }

            dialog.show()
        }
    }
}