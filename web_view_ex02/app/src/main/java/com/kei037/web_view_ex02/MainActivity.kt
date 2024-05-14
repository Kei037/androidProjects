package com.kei037.web_view_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "https://www.starbucks.co.kr/index.do"

        webView = findViewById(R.id.webView)
        webView.webViewClient = myWebViewClient()

        val webSet = webView.settings
        webSet.builtInZoomControls = true
        webSet.javaScriptEnabled = true
        webView.loadUrl("https://www.starbucks.co.kr/index.do")
    }

    class myWebViewClient : WebViewClient() {
        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}