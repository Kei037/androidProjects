package com.kei037.simple_diary_ex05_room

import androidx.room.*

@Dao
interface RoomDiaryDao {
    // 다른 ORM 툴과는 다르게 조회를 하는 select 쿼리는 직접 작성하도록 설계
    // 대부분의 ORM은 select 도 메서드로 제공
    @Query("SELECT * FROM room_diary")
    fun getAll(): List<RoomDiary>

    // 특정 날짜의 항목 조회
    @Query("SELECT * FROM room_diary WHERE date = :date")
    fun getOne(date: String): RoomDiary?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(diary: RoomDiary)

    @Delete
    fun delete(diary: RoomDiary)
}