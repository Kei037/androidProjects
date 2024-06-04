package com.kei037.todolist_ex02

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kei037.todolist_ex02.databinding.ItemTodoBinding
import com.kei037.todolist_ex02.db.ToDoEntity
import java.util.ArrayList

class TodoRecyclerViewAdapter(private val todoList: ArrayList<ToDoEntity>,
    private val listener: OnItemLongClickListener) :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewImportance = binding.textViewImportance
        val textViewTitle = binding.textViewTitle
        val textViewDoneDate = binding.textViewDoneDate

        // 뷰 바인딩에서 기본적으로 제공하는 root 변수는 레이아웃의 루트 레이아웃을 의미
        val root = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todoData = todoList[position]
        Log.i("TODO 데이터 목록!! == " , todoData.toString())
        Log.i("TODO 타이틀 == " , todoData.title)
        Log.i("TODO 중요도 == " , todoData.importance.toString())
        // 중요도에 따라 색상을 변경
        when (todoData.importance) {
            1 -> holder.textViewImportance.setBackgroundResource(R.color.red)
            2 -> holder.textViewImportance.setBackgroundResource(R.color.yellow)
            3 -> holder.textViewImportance.setBackgroundResource(R.color.green)
        }
        // 중요도에 따라 중요도 텍스트(1, 2, 3) 변경
        holder.textViewImportance.text = todoData.importance.toString()
        // 할 일 제목 변경
        holder.textViewTitle.text = todoData.title
        holder.textViewDoneDate.text = todoData.doneDate

        // 할 일을 길게 클릭했을 때 리스너 함수 실행
        holder.root.setOnLongClickListener {
            listener.onLongClick(position)
            false
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}
