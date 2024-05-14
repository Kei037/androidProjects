package com.kei037.grid_view_ex01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "그리드뷰 영화 포스터"

        val gridView = findViewById<GridView>(R.id.gridView)
        val myGridAdapter = MyGridAdapter(this)
        gridView.adapter = myGridAdapter
    }

    inner class MyGridAdapter(val context: Context): BaseAdapter() {
        val posterId = arrayOf(
            R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
            R.drawable.mov11, R.drawable.mov12, R.drawable.mov13, R.drawable.mov14, R.drawable.mov15,
            R.drawable.mov16, R.drawable.mov17, R.drawable.mov18, R.drawable.mov19, R.drawable.mov20,
            R.drawable.mov21, R.drawable.mov22, R.drawable.mov23, R.drawable.mov24, R.drawable.mov25,
            R.drawable.mov26, R.drawable.mov27, R.drawable.mov28, R.drawable.mov29, R.drawable.mov30,
            R.drawable.mov31, R.drawable.mov32, R.drawable.mov33, R.drawable.mov34, R.drawable.mov35,
            R.drawable.mov36, R.drawable.mov37, R.drawable.mov38, R.drawable.mov39, R.drawable.mov40
        )

        override fun getCount(): Int {
            return posterId.size
        }

        override fun getItem(p0: Int): Any {
            return 0
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(200, 300)
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView.setPadding(5, 5, 5 , 5)

            imageView.setImageResource(posterId[p0])

            imageView.setOnClickListener {
                val dialogView = View.inflate(this@MainActivity, R.layout.dialog, null)
                val dialog = AlertDialog.Builder(this@MainActivity)
                val imageViewPoster = dialogView.findViewById<ImageView>(R.id.imageViewPoster)
                imageViewPoster.setImageResource(posterId[p0])
                dialog.setTitle("큰 포스터")
                dialog.setIcon(R.drawable.ic_launcher_foreground)
                dialog.setView(dialogView)
                dialog.setNegativeButton("닫기", null)
                dialog.show()
            }

            return imageView
        }
    }
}