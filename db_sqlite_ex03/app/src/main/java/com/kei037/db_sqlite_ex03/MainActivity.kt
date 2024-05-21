package com.kei037.db_sqlite_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kei037.db_sqlite_ex03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    val helper = SQLiteHelper(this, "memo", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = RecyclerAdapter()
//        adapter.helper = helper

//        adapter.listData.addAll(helper.selectMemo())

        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        binding.buttonSave.setOnClickListener {
            if (binding.editMemo.text.toString().isNotEmpty()) {
                val memo = Memo(null, binding.editMemo.text.toString(), System.currentTimeMillis())
//                helper.insertMemo(memo)
                adapter.listData.clear()

                // 글 등록 후 새로 생성되는 메모에 번호가 자동입력되므로 번호를 갱신하기 위해 새로운 데이터 세팅
//                adapter.listData.addAll(helper.selectMemo()) // 글 등록
                adapter.notifyDataSetChanged()               // 화면갱신

                binding.editMemo.setText("") // 입력란 클리어
            }
        }
    }
}
