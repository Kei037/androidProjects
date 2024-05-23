package com.kei037.simple_diary_ex05_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_diary")
class RoomDiary {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "date")  // 테이블 이름은 date로 설정
    var datetime: String = ""

    @ColumnInfo(name = "content")
    var content: String = ""

    constructor(datetime: String, content: String) {
        this.content = content
        this.datetime = datetime
    }

    override fun toString(): String {
        return "RoomDiary(datetime='$datetime', content='$content')"
    }


}