package com.kei037.todolist_ex02

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.kei037.todolist_ex02.databinding.ActivityMainBinding
import com.kei037.todolist_ex02.db.AppDatabase
import com.kei037.todolist_ex02.db.ToDoDao
import com.kei037.todolist_ex02.db.ToDoEntity

class MainActivity : AppCompatActivity(), OnItemLongClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db: AppDatabase
    private lateinit var todoDao: ToDoDao
    private lateinit var todoList: ArrayList<ToDoEntity>
    private lateinit var adapter: TodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddTodo.setOnClickListener {
            val intent = Intent(applicationContext, AddTodoActivity::class.java)
            startActivity(intent)
        }

        // DB 인스턴스를 가져오고 DB 작업을 할 수 있는 DAO를 가져옴
        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        getAllTodoList()
    }

    private fun getAllTodoList() {
        Thread {
            todoList = ArrayList(todoDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        // 리사이클러뷰 설정
        runOnUiThread {
            adapter = TodoRecyclerViewAdapter(todoList, this)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = adapter

        }
    }

    // 액티비티가 멈췄다가 다시 시작할때 실행되는 메서드, 돌오왔을때 리스트 갱신
    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }

    // 롱클릭 이벤트 발생시 삭제
    override fun onLongClick(position: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 삭제")
        builder.setMessage("정말 삭제하시겠습니까?")
        builder.setNegativeButton("취소", null)
        //확인 버튼 설정
        builder.setPositiveButton("네",
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    deleteTodo(position)
                }
            }
        )
        builder.show()
    }

    private fun deleteTodo(position: Int) {
        Thread {
            todoDao.deleteTodo(todoList[position]) // 디비에서 삭제
            todoList.removeAt(position)            // 리스트에서 삭제
            runOnUiThread {
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}