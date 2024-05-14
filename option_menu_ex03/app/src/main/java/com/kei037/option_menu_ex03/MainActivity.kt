package com.kei037.option_menu_ex03

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var editText1: EditText
    lateinit var imgView1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.editText1)
        imgView1 = findViewById(R.id.imgView1)

        title = "제주도 풍경"

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.rotate -> {
                imgView1.rotation = editText1.text.toString().toFloat()
            }
            R.id.item1 -> {
                imgView1.setImageResource(R.drawable.jeju5)
                item.isChecked = true
            }
            R.id.item2 -> {
                imgView1.setImageResource(R.drawable.jeju10)
                item.isChecked = true
            }
            R.id.item3 -> {
                imgView1.setImageResource(R.drawable.jeju15)
                item.isChecked = true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}