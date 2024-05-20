package com.kei037.simple_diary_ex04_sqlite2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "my_db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE my_diary (diary_date CHAR(10) PRIMARY KEY, content VARCHAR(500));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS my_diary")
        onCreate(db)
    }

    fun insertDiary(diary: Diary) {
        val db = writableDatabase
        db.execSQL("INSERT INTO `my_diary` VALUES (?, ?);", arrayOf(diary.diaryDate, diary.content))
        db.close()
    }

    fun selectDiary(diaryDate: String): String? {
        var diaryStr: String? = null
        val db = writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT content FROM my_diary WHERE diary_date = '$diaryDate';", null)
        if (cursor.moveToFirst()) {
            diaryStr = cursor.getString(0)
        }
        println("커서 확인 = " + cursor.moveToFirst())
        println("콘텐츠 내용 = " + cursor.getString(0))

        cursor.close()
        db.close()
        return diaryStr
    }
}

data class Diary(val diaryDate: String, val content: String)