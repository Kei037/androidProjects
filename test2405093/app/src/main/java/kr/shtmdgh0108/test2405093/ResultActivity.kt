package kr.shtmdgh0108.test2405093

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        title = "투표결과"
        val intent = intent
        val voteCount = intent.getIntArrayExtra("voteCount")
        val imageNames = intent.getStringArrayExtra("imageNames")
        
        val imageIds = arrayOf(R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9)


    }
}