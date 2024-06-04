package com.kei037.todolist_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kei037.todolist_ex02.databinding.ActivityAddTodoBinding
import com.kei037.todolist_ex02.db.AppDatabase
import com.kei037.todolist_ex02.db.ToDoDao
import com.kei037.todolist_ex02.db.ToDoEntity
import java.time.LocalDate

class AddTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTodoBinding
    private lateinit var db: AppDatabase
    private lateinit var toDoDao: ToDoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        toDoDao = db.getTodoDao()

        binding.btnCompletion.setOnClickListener {
            insertTodo()
        }
    }

    private fun insertTodo() {
        val todoTitle = binding.editTextTitle.text.toString()
        var todoImportance = binding.radioGroup.checkedRadioButtonId
        val doneDate = LocalDate.of(binding.datePicker.year, binding.datePicker.month + 1, binding.datePicker.dayOfMonth) ?: LocalDate.now()

        when (todoImportance) {
            R.id.btnHigh -> {
                todoImportance = 1
            }
            R.id.btnMiddel -> {
                todoImportance = 2
            }
            R.id.btnLow -> {
                todoImportance = 3
            }
            else -> {
                todoImportance = -1
            }
        }

        // 중요도가 선택되지 않거나, 제목이 작성되지 않았는지 체크
        if (todoImportance == -1 || todoTitle.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주새요.", Toast.LENGTH_SHORT).show()
        } else {
            Thread { // 새로운 스레드 생성
//                Log.i("입력한 타이틀 ==== ", todoTitle)
//                Log.i("선택한 중요도 ==== ", todoImportance.toString())
                toDoDao.insertTodo(ToDoEntity(null, todoTitle, todoImportance, doneDate.toString()))
//                toDoDao.getAll().forEach {
//                    Log.i("DB에 저장된 데이터 === ", it.toString())
//                }
                runOnUiThread { // 아래 작업은 UI 스레드에서 실행해주어야만 함
                    Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                    finish() // AddTodoActivity 종료, 다시 Main 화면으로 이동
                }
            }.start()
        }
    }
}