package com.kei037.db_sqlite_ex02

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(context: Context, name: String, version: Int):
    SQLiteOpenHelper(context, name, null, version)  {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE `memo` (`num` INTEGER PRIMARY KEY, " +
                "`content` TEXT, `datetime` INTEGER)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val create = "CREATE TABLE memo (" +
                "num integer primary key)"
        db?.execSQL(create)
    }

//    fun insertMemo(memo: Memo) {
//        val values = ContentValues()
//        values.put("content", memo.context)
//        values.put("datetime", memo.datetime)
//    }

    fun insertMemo(memo: Memo) {
        val values = ContentValues()
        values.put("content", memo.context)
        values.put("datetime", memo.datetime)

        Log.i("memo = ", memo.toString())
        writableDatabase.insert("memo", null, values)
        writableDatabase.close()
    }

    fun selectMemo(): MutableList<Memo> {
        val list = mutableListOf<Memo>()

        val sql = "SELECT * FROM memo"
        val rd = readableDatabase
        val cursor = rd.rawQuery(sql, null)

        while (cursor.moveToNext()) {
            val num = cursor.getLong(0)
            val content = cursor.getString(1)
            val datetime = cursor.getLong(2)
            list.add(Memo(num, content, datetime))
        }
        cursor.close()
        rd.close()

        return list
    }

    fun deleteMemo(memo: Memo) {
        val sql = "DELETE FROM memo WHERE num=${memo.num}"
        val wd = writableDatabase
        wd.execSQL(sql)
        wd.close()
    }
}

data class Memo(var num: Long?, var context: String, var datetime: Long)



