package com.kei037.activity_intent_ex06

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDial = findViewById<Button>(R.id.btnDial)
        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val btnGoogle = findViewById<Button>(R.id.btnGoogle)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnSms = findViewById<Button>(R.id.btnSms)
        val btnPhoto = findViewById<Button>(R.id.btnPhoto)

        btnDial.setOnClickListener {
            val uri = Uri.parse("tel:010-1234-4567")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        btnWeb.setOnClickListener {
            val uri = Uri.parse("https://google.com")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        btnGoogle.setOnClickListener {
            val uri = Uri.parse("https://maps.ggole.com/maps?q="
            + 35.86606 + "," + 128.5938 + "&z=15")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        btnSearch.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, "안드로이드")
            startActivity(intent)
        }

        btnSms.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.putExtra("sms_body", "안녕하세요?")
            intent.setData(Uri.parse("smsto:010-1234-5678"))
            startActivity(intent)
        }

        btnPhoto.setOnClickListener {
            startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }
}