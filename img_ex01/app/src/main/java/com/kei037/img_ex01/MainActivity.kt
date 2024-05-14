package com.kei037.img_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var textView01 : TextView
    lateinit var checkBoxAgree: CheckBox
    lateinit var textView02: TextView
    lateinit var radioGroup : RadioGroup
    lateinit var radioButtonDog: RadioButton
    lateinit var radioButtonCat: RadioButton
    lateinit var radioButtonRabbit: RadioButton
    lateinit var btnOk : Button
    lateinit var imgPet : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView01 = findViewById(R.id.textView01)
        textView02 = findViewById(R.id.textView02)
        checkBoxAgree = findViewById(R.id.checkBoxAgree)
        radioGroup = findViewById(R.id.radioGroup)
        radioButtonDog = findViewById(R.id.radioButtonDog)
        radioButtonCat = findViewById(R.id.radioButtonCat)
        radioButtonRabbit = findViewById(R.id.radioButtonRabbit)
        btnOk = findViewById(R.id.btnOk)
        imgPet = findViewById(R.id.imgPet)

        checkBoxAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                textView02.visibility = View.VISIBLE
                radioGroup.visibility = View.VISIBLE
                btnOk.visibility = View.VISIBLE
                imgPet.visibility = View.VISIBLE
            } else {
                textView02.visibility = View.INVISIBLE
                radioGroup.visibility = View.INVISIBLE
                btnOk.visibility = View.INVISIBLE
                imgPet.visibility = View.INVISIBLE
            }
        }

        btnOk.setOnClickListener {
            when (radioGroup.checkedRadioButtonId) {
                R.id.radioButtonDog -> imgPet.setImageResource(R.drawable.dog)
                R.id.radioButtonCat -> imgPet.setImageResource(R.drawable.cat)
                R.id.radioButtonRabbit -> imgPet.setImageResource(R.drawable.rabbit)
                else -> Toast.makeText(applicationContext, "동물을 먼저 선택하세요.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}