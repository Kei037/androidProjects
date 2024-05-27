package kr.shtmdgh0108.test240509

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMain = R.layout.activity_main
        setContentView(activityMain)

        title = "액티비티 테스트 예제"
        Log.i("액티비티 테스트", "onCreate()")

        val btnDial = findViewById<Button>(R.id.btnDial)
        btnDial
            .setOnClickListener {
                val uri = Uri.parse("tel:010-1234-5678")
                startActivity(Intent(Intent.ACTION_DIAL, uri))
            }
        val btnFinish = findViewById<Button>(R.id.btnFinish)
        btnFinish.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("액티비티 테스트", "onDestroy()")
    }

    override fun onPause() {
        super.onPause()
        Log.i("액티비티 테스트", "onPause()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("액티비티 테스트", "onRestart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("액티비티 테스트", "onResume()")
    }
}