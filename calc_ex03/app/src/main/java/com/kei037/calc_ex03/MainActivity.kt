package com.kei037.calc_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var btnAdd : Button
    lateinit var btnSub : Button
    lateinit var btnMul : Button
    lateinit var btnDiv : Button
    lateinit var textResult : TextView

    lateinit var num1 : String
    lateinit var num2 : String
    var result : Int? = null
    // 숫자 버튼
    val btnNums = mutableListOf<Button>()
    // 버튼의 id 값
    var btnIds = arrayOf(R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3, R.id.btnNum4,
        R.id.btnNum5, R.id.btnNum6, R.id.btnNum7, R.id.btnNum8, R.id.btnNum9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "태이블 레이아웃 계산기"

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)

        btnAdd = findViewById(R.id.btnAdd)
        btnSub = findViewById(R.id.btnSub)
        btnMul = findViewById(R.id.btnMul)
        btnDiv = findViewById(R.id.btnDiv)

        textResult = findViewById(R.id.textResult)

        btnAdd.setOnClickListener {
            num1 = editText1.text.toString()
            num2 = editText2.text.toString()
            result = num1.toInt() + num2.toInt()
            textResult.text = "계산결과 : $result"
        }

        btnSub.setOnClickListener {
            num1 = editText1.text.toString()
            num2 = editText2.text.toString()
            result = num1.toInt() - num2.toInt()
            textResult.text = "계산결과 : $result"
        }

        btnMul.setOnClickListener {
            num1 = editText1.text.toString()
            num2 = editText2.text.toString()
            result = num1.toInt() * num2.toInt()
            textResult.text = "계산결과 : $result"
        }

        btnDiv.setOnClickListener {
            num1 = editText1.text.toString()
            num2 = editText2.text.toString()
            result = num1.toInt() / num2.toInt()
            textResult.text = "계산결과 : $result"
        }

        // 버튼 타입의 리스트에 위젯을 등록 후 이벤트 리스너 등록
        for (i in btnIds.indices) {
            btnNums.add(i, findViewById(btnIds[i]))

            // 포커스가 되어있는 에디트텍스트에 버튼의 숫자를 결함. 문자열 결합
            btnNums[i].setOnClickListener {
                if (editText1.isFocused) {
                    num1 = editText1.text.toString() + btnNums[i].text.toString()
                    editText1.setText(num1)
                } else if (editText2.isFocused) {
                    num2 = editText2.text.toString() + btnNums[i].text.toString()
                    editText2.setText(num2)
                } else {
                    Toast.makeText(applicationContext, "먼저 에디트텍스트를 선택하세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}