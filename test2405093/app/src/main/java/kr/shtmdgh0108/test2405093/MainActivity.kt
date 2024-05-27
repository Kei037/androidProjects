package kr.shtmdgh0108.test2405093

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btnResult : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "명화 선호도 투표"

        var toast: Toast = Toast.makeText(applicationContext,"", Toast.LENGTH_SHORT)
        // 투표수를 저장할 배열
        val voteCounts = IntArray(9)
        // 이미지 버튼 객체 배열

        // 이미지 버튼 ID 배열
        val images = arrayOfNulls<ImageView>(9)

        val imageIds = arrayOf(R.id.imageView1, R.id.imageView2, R.id.imageView3,
            R.id.imageView4, R.id.imageView5, R.id.imageView6,
            R.id.imageView7, R.id.imageView8, R.id.imageView9)

        // 이미지 이름 문자열 배열
        val imageNames = arrayOf("독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
            "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매",
            "피아노 레슨", "피아노 앞의 소녀들", "해변에서")
        for(i in imageIds.indices){
            images[i] = findViewById(imageIds[i])
            images[i]!!.setOnClickListener{
                voteCounts[i]++
                toast.cancel()
                toast = Toast.makeText(applicationContext,"${imageNames[i]} : 총${voteCounts[i]} 표", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        val btnResult = findViewById<Button>(R.id.btnResult)

        btnResult.setOnClickListener {
            val intent = Intent(applicationContext, ResultActivity::class.java)
            intent.putExtra("voteCount", voteCounts)
            intent.putExtra("imageNames", imageNames)
            startActivity(intent)
        }

    }
}