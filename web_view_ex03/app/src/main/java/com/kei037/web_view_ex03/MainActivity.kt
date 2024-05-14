package com.kei037.web_view_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var btnHome: Button
    lateinit var btnStore: Button
    lateinit var btnFran: Button
    lateinit var btnMedia: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val urlHome = "https://www.ediya.com/"
        val urlStore = "https://www.ediya.com/contents/find_store.html"
        val urlFran = "https://www.ediya.com/C/contents/franchise_01.html"
        val urlMedia = "https://www.ediya.com/contents/notice.html?tb_name=notice"

        webView = findViewById(R.id.webView)
        webView.webViewClient = myWebViewClient()

        btnHome = findViewById(R.id.btnHome)
        btnStore = findViewById(R.id.btnStore)
        btnFran = findViewById(R.id.btnFran)
        btnMedia = findViewById(R.id.btnMedia)

        val webSet = webView.settings
        webSet.builtInZoomControls = true
        webSet.javaScriptEnabled = true
        webView.loadUrl(urlHome)

        btnHome.setOnClickListener {
            webView.loadUrl(urlHome)
        }
        btnStore.setOnClickListener {
            webView.loadUrl(urlStore)
        }
        btnFran.setOnClickListener {
            webView.loadUrl(urlFran)
        }
        btnMedia.setOnClickListener {
            webView.loadUrl(urlMedia)
        }

    }
    class myWebViewClient : WebViewClient() {
        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}