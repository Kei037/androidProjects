package com.kei037.img_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var textView01 : TextView
    lateinit var switchAgree: Switch
    lateinit var textView02: TextView
    lateinit var radioGroup : RadioGroup
    lateinit var radioButtonPie: RadioButton
    lateinit var radioButtonQ10: RadioButton
    lateinit var radioButtonOreo: RadioButton
    lateinit var imgPet : ImageView
    lateinit var btnEnd : Button
    lateinit var btnBef : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView01 = findViewById(R.id.textView01)
        textView02 = findViewById(R.id.textView02)
        switchAgree = findViewById(R.id.switchAgree)
        radioGroup = findViewById(R.id.radioGroup)
        radioButtonPie = findViewById(R.id.radioButtonPie)
        radioButtonQ10 = findViewById(R.id.radioButtonQ10)
        radioButtonOreo = findViewById(R.id.radioButtonOreo)
        imgPet = findViewById(R.id.imgPet)
        btnEnd = findViewById(R.id.btnEnd)
        btnBef = findViewById(R.id.btnBef)

        switchAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                textView02.visibility = View.VISIBLE
                radioGroup.visibility = View.VISIBLE
                imgPet.visibility = View.VISIBLE
            } else {
                textView02.visibility = View.INVISIBLE
                radioGroup.visibility = View.INVISIBLE
                imgPet.visibility = View.INVISIBLE
            }
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, isChecked ->
            when (isChecked) {
                R.id.radioButtonPie -> imgPet.setImageResource(R.drawable.pie)
                R.id.radioButtonQ10 -> imgPet.setImageResource(R.drawable.q10)
                R.id.radioButtonOreo -> imgPet.setImageResource(R.drawable.oreo)
            }
        }

        btnEnd.setOnClickListener {
            finish()
        }

        btnBef.setOnClickListener {
            switchAgree.isChecked = false
            radioGroup.clearCheck()
            imgPet.setImageResource(0)
        }

    }
}