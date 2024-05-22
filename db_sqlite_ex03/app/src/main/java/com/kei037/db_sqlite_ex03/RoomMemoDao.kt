package com.kei037.db_sqlite_ex03

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoomMemoDao {
    // 다른 ORM 툴과는 다르게 조회를 하는 select 쿼리는 직접 작성하도록 설계
    // 대부분의 ORM은 select 도 메서드로 제공
    @Query("SELECT * FROM room_memo")
    fun getAll(): List<RoomMemo>

    @Insert(onConflict = REPLACE)
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)
}