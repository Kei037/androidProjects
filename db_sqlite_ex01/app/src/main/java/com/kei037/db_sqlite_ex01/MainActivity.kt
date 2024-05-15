package com.kei037.db_sqlite_ex01

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var myHelper: MyDBHelper
    lateinit var editName: EditText
    lateinit var editNumber: EditText
    lateinit var editNameResult: EditText
    lateinit var editNumberResult: EditText
    lateinit var btnInit: Button
    lateinit var btnInsert: Button
    lateinit var btnSelect: Button
    lateinit var sqlDB: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("가수 그룹 관리 DB")

        editName = findViewById(R.id.editName)
        editNumber = findViewById(R.id.editNumber)
        editNameResult = findViewById(R.id.editNameResult)
        editNumberResult = findViewById(R.id.editNumberResult)

        btnInit = findViewById(R.id.btnInit)
        btnInsert = findViewById(R.id.btnInsert)
        btnSelect = findViewById(R.id.btnSelect)

        myHelper = MyDBHelper(this)

        btnInit.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            myHelper.onUpgrade(sqlDB, 1, 2)
            sqlDB.close()
        }

        // 입력
        btnInsert.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            sqlDB.execSQL("INSERT INTO `groupTBL` VALUES ('${editName.text.toString()}', " +
                    " ${editNumber.text.toString()});")
            sqlDB.close()
            Toast.makeText(applicationContext, "입력됨", Toast.LENGTH_SHORT).show()
        }

        // 조회
        btnSelect.setOnClickListener {
            sqlDB = myHelper.writableDatabase
            val cursor: Cursor
            cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null)

            var names = "그룹이름 \r\n ----------- \r\n"
            var numbers = "인원 \r\n ----------- \r\n"

            while (cursor.moveToNext()) {
                names += cursor.getString(0) + "\r\n"
                numbers += cursor.getString(1) + "\r\n"
            }

            editNameResult.setText(names)
            editNumberResult.setText(numbers)

            cursor.close()
            sqlDB.close()
        }

    }
    // groupDB가 데이터베이스 생성명
    inner class MyDBHelper(context: Context): SQLiteOpenHelper(context, "groupDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("CREATE TABLE `groupTBL`(`gName` CHAR(20) PRIMARY KEY, `gNumber` INTEGER);")
        }

        // 테이블이 있다면 삭제 후 생성
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS `groupTBL`;")
            onCreate(db)
        }
    }
}