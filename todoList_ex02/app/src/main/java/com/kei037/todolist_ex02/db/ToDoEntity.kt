package com.kei037.todolist_ex02.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity // 어떤 구성요소인지 알려주려면 꼭 어노테이션을 써줘야함
data class ToDoEntity ( // 테이블 이름은 ToDoEntity로 설정
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "importance")
    val importance: Int
)