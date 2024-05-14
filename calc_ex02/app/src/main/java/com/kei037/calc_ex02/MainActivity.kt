package com.kei037.calc_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var Edit1 : EditText
    lateinit var Edit2 : EditText
    lateinit var BtnNum0 : Button
    lateinit var BtnNum1 : Button
    lateinit var BtnNum2 : Button
    lateinit var BtnNum3 : Button
    lateinit var BtnNum4 : Button
    lateinit var BtnNum5 : Button
    lateinit var BtnNum6 : Button
    lateinit var BtnNum7 : Button
    lateinit var BtnNum8 : Button
    lateinit var BtnNum9 : Button
    lateinit var BtnAdd : Button
    lateinit var BtnSub : Button
    lateinit var BtnMul : Button
    lateinit var BtnDiv : Button
    lateinit var TextResult : TextView
    lateinit var num1 : String
    lateinit var num2 : String
    var result : Double? = null
    var clickedEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "초간단 계산기"

        Edit1 = findViewById(R.id.Edit1)
        Edit2 = findViewById(R.id.Edit2)
        BtnNum0 = findViewById(R.id.BtnNum0)
        BtnNum1 = findViewById(R.id.BtnNum1)
        BtnNum2 = findViewById(R.id.BtnNum2)
        BtnNum3 = findViewById(R.id.BtnNum3)
        BtnNum4 = findViewById(R.id.BtnNum4)
        BtnNum5 = findViewById(R.id.BtnNum5)
        BtnNum6 = findViewById(R.id.BtnNum6)
        BtnNum7 = findViewById(R.id.BtnNum7)
        BtnNum8 = findViewById(R.id.BtnNum8)
        BtnNum9 = findViewById(R.id.BtnNum9)
        BtnAdd = findViewById(R.id.BtnAdd)
        BtnSub = findViewById(R.id.BtnSub)
        BtnMul = findViewById(R.id.BtnMul)
        BtnDiv = findViewById(R.id.BtnDiv)
        TextResult = findViewById(R.id.TextResult)

        // EditText 클릭 이벤트 처리
        Edit1.setOnClickListener {
            clickedEditText = Edit1
        }
        Edit2.setOnClickListener {
            clickedEditText = Edit2
        }

        // 숫자 버튼 클릭
        setNumberButtonClickListeners()



        BtnAdd.setOnClickListener {
            num1 = Edit1.text.toString()
            num2 = Edit2.text.toString()
            result = num1.toDouble() + num2.toDouble()
            TextResult.setText("계산 결과: ${result}")
        }

        BtnSub.setOnClickListener {
            num1 = Edit1.text.toString()
            num2 = Edit2.text.toString()
            result = num1.toDouble() - num2.toDouble()
            TextResult.setText("계산 결과: ${result}")
        }

        BtnMul.setOnClickListener {
            num1 = Edit1.text.toString()
            num2 = Edit2.text.toString()
            result = num1.toDouble() * num2.toDouble()
            TextResult.setText("계산 결과: ${result}")
        }

        BtnDiv.setOnClickListener {
            num1 = Edit1.text.toString()
            num2 = Edit2.text.toString()
            result = num1.toDouble() / num2.toDouble()
            TextResult.setText("계산 결과: ${result}")
        }
    }
    private fun setNumberButtonClickListeners() {
        val numberButtons = listOf(
            BtnNum0, BtnNum1, BtnNum2, BtnNum3, BtnNum4,
            BtnNum5, BtnNum6, BtnNum7, BtnNum8, BtnNum9
        )
        numberButtons.forEach { button ->
            button.setOnClickListener {
                clickedEditText?.append(button.text)
            }
        }
    }

}