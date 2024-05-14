package com.kei037.context_menu_ex01

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var baseLayout: LinearLayout
    lateinit var button1: Button
    lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseLayout = findViewById(R.id.baseLayout)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        // 버튼들에 메뉴위젯 적용
        registerForContextMenu(button1)
        registerForContextMenu(button2)
    }

    // 만든 menu1.xml, menu2.xml을 불러와 위젯을 생성
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v == button1) {
            menu?.setHeaderTitle("배경색 변경")
            menuInflater.inflate(R.menu.menu1, menu)
        } else if (v == button2) {
            menuInflater.inflate(R.menu.menu2, menu)
        }
    }

    // 위젯에 기능추가
    override fun onContextItemSelected(item: MenuItem): Boolean {
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
                button2.rotation = 45f
            }
            R.id.subSize -> {
                button2.scaleX = 2f
                button2.scaleY = 2f
            }
        }
        return super.onContextItemSelected(item)
    }
}