package kr.shtmdgh0108.test240509frag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButton()
    }

    private fun setButton() {
        val redButton = findViewById<Button>(R.id.buttonRedFragment)
        val bluedButton = findViewById<Button>(R.id.buttonBlueFragment)

        redButton.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentFrame, RedFragment())
            fragmentTransaction.commit()
        }
        bluedButton.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentFrame, BlueFragment())
            fragmentTransaction.commit()
        }
    }
}