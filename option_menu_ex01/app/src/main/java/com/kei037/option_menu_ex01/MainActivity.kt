package com.kei037.option_menu_ex01

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var baseLayout: LinearLayout
    lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "배경색 바꾸기"

        baseLayout = findViewById(R.id.baseLayout)
        button1 = findViewById(R.id.button1)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemRed -> {
                baseLayout.setBackgroundColor(Color.RED)
            }
            R.id.itemGreen -> {
                baseLayout.setBackgroundColor(Color.GREEN)
            }
            R.id.itemBlue -> {
                baseLayout.setBackgroundColor(Color.BLUE)
            }
            R.id.subRotate -> {
                button1.rotation = 45f
            }
            R.id.subSize -> {
                button1.scaleX = 2f
                button1.scaleY = 2f
            }
        }
        return super.onOptionsItemSelected(item)
    }
}