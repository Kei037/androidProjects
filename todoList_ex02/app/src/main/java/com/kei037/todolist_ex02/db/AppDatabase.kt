package com.kei037.todolist_ex02.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// entities: 데이터베이스에 포함될 엔티티 클래스를 배열로 설정
@Database(entities = arrayOf(ToDoEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() { // RoomDatabase를 상속받아 AppDatabase 클래스 선언
    abstract fun getTodoDao(): ToDoDao // ToDoDao 인터페이스를 반환하는 추상 메서드 선언

    companion object { // 동반 객체 선언
        val databaseName = "db_todo" // 데이터베이스 이름
        private var appDatabase : AppDatabase? = null // 데이터베이스 객체

        // 싱글톤패턴으로 getInstance 메서드 선언
        fun getInstance(context: Context): AppDatabase? {
            if (appDatabase == null) {
                // 데이터베이스 객체가 없으면 Room.databaseBuilder 메서드로 데이터베이스 객체 생성
                appDatabase = Room.databaseBuilder(context,
                AppDatabase::class.java, databaseName).build()
            }
            return appDatabase
        }
    }
}
