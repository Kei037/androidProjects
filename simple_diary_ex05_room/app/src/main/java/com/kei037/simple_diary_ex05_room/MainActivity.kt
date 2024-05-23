package com.kei037.simple_diary_ex05_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.room.Room
import com.kei037.simple_diary_ex05_room.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var helper: RoomHelper? = null

    lateinit var diaryDate: String
    var isRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Room 일기장"

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_diary")
            .allowMainThreadQueries()
            .build()

        initDatePicker()
        initBtnWrite()
    }

    private fun initDatePicker() {
        val calendar = Calendar.getInstance()
        val calendarYear = calendar.get(Calendar.YEAR)
        val calendarMonth = calendar.get(Calendar.MONTH)
        val calendarDay = calendar.get(Calendar.DAY_OF_MONTH)

        binding.datePicker.init(
            calendarYear,
            calendarMonth,
            calendarDay,
            DatePicker.OnDateChangedListener { datePicker, year, month, day ->
                diaryDate = "${year}-${month + 1}-${day}"
                val string = readDiary(diaryDate)
                binding.editDiary.setText(string)
                binding.btnWrite.isEnabled = true
            }
        )
    }

    private fun readDiary(diaryDate: String): String? {
        Log.i("조회할 날짜 == ", diaryDate)
        val diary: RoomDiary? = helper?.roomDiaryDao()?.getOne(diaryDate)
        Log.i("조회된 날짜 == ", diary?.datetime.toString())
        if (diary == null) {
            binding.editDiary.hint = "일기 없음"
            binding.btnWrite.text = "새로저장"
        } else {
            binding.btnWrite.text = "수정하기"
        }

        return diary?.content
    }

    private fun initBtnWrite() {
        // 저장 버튼 이벤트 리스너 등록
        binding.btnWrite.setOnClickListener {
            val content = binding.editDiary.text.toString()
            Log.i("저장될 날짜 == ", diaryDate)
            Log.i("저장될 내용 == ", content)

            val diary = RoomDiary(diaryDate, content)
            Log.i("diary == ", diary.toString())

            val helperLog = helper?.roomDiaryDao()?.insert(RoomDiary(diaryDate, content))
            Log.i("helperLog == ", helperLog.toString())
            Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_SHORT).show()
        }
    }
}
