package com.kei037.activity_intent_ex07

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "메인 엑티비티"

        val editNum1 = findViewById<EditText>(R.id.editNum1)
        val editNum2 = findViewById<EditText>(R.id.editNum2)
        val radioBtnSum = findViewById<RadioButton>(R.id.radioButtonSum)
        val radioBtnMin= findViewById<RadioButton>(R.id.radioButtonMin)
        val radioBtnMul = findViewById<RadioButton>(R.id.radioButtonMul)
        val radioBtnDiv = findViewById<RadioButton>(R.id.radioButtonDiv)
        val btnSecondActivity = findViewById<Button>(R.id.btnSecondActivity)

        btnSecondActivity.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("num1", editNum1.text.toString().toInt())
            intent.putExtra("num2", editNum2.text.toString().toInt())
            // 라이도 버튼선택
            val selectedOperation = when {
                radioBtnSum.isChecked -> "sum"
                radioBtnMin.isChecked -> "min"
                radioBtnMul.isChecked -> "mul"
                radioBtnDiv.isChecked -> "div"
                else -> ""
            }
            intent.putExtra("checkRadio", selectedOperation)
            startActivityForResult(intent, 0)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val result = data!!.getIntExtra("result", 0)
            Toast.makeText(applicationContext, "합계 : ${result}", Toast.LENGTH_SHORT).show()
        }
    }
}