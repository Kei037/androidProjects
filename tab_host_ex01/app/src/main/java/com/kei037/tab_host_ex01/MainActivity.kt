package com.kei037.tab_host_ex01

import android.app.TabActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : TabActivity() {
    lateinit var imageView1: ImageView
    lateinit var imageView2: ImageView
    lateinit var imageView3: ImageView
    lateinit var imageView4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabHost = this.tabHost

        val tabSpec1 = tabHost.newTabSpec("TAG1").setIndicator("강아지")
        tabSpec1.setContent(R.id.imageView1)
        tabHost.addTab(tabSpec1)

        val tabSpec2 = tabHost.newTabSpec("TAG2").setIndicator("고양이")
        tabSpec2.setContent(R.id.imageView2)
        tabHost.addTab(tabSpec2)

        val tabSpec3 = tabHost.newTabSpec("TAG3").setIndicator("토끼")
        tabSpec3.setContent(R.id.imageView3)
        tabHost.addTab(tabSpec3)

        val tabSpec4 = tabHost.newTabSpec("TAG4").setIndicator("말")
        tabSpec4.setContent(R.id.imageView4)
        tabHost.addTab(tabSpec4)

        tabHost.currentTab = 0
    }
}