package com.kei037.flipping_ex01

import android.app.TabActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TabHost
import android.widget.ViewFlipper

class MainActivity : TabActivity() {
//    lateinit var btnStart : Button
//    lateinit var btnStop : Button
//    lateinit var viewFlipper : ViewFlipper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tap_host01)

        // tab host
        val tabHost = this.tabHost

        val tabSpecSong = tabHost.newTabSpec("SONG").setIndicator("음악별")
        tabSpecSong.setContent(R.id.tabSong)
        tabHost.addTab(tabSpecSong)

        val tabSpecArtist = tabHost.newTabSpec("ARTIST").setIndicator("가수별")
        tabSpecArtist.setContent(R.id.tabArtist)
        tabHost.addTab(tabSpecArtist)

        val tabSpecAlbum = tabHost.newTabSpec("ALBUM").setIndicator("앨범별")
        tabSpecAlbum.setContent(R.id.tabAlbum)
        tabHost.addTab(tabSpecAlbum)

        tabHost.currentTab = 0



//        btnStart = findViewById<Button>(R.id.btnStart)
//        btnStop = findViewById<Button>(R.id.btnStop)
//
//        viewFlipper = findViewById<ViewFlipper>(R.id.viewFlipper1)
//        viewFlipper.flipInterval = 1000
//
//        btnStart.setOnClickListener {
//            viewFlipper.startFlipping()
//        }
//        btnStop.setOnClickListener {
//            viewFlipper.stopFlipping()
//        }
    }
}