package com.kei037.db_sqlite_ex03

import androidx.room.Database
import androidx.room.RoomDatabase

// entities) Room 라이브러리가 사용할 엔티티 클래스 목록
// version) 데이터베이스 버전
// exportSchema) true면 스키마 정보를 파일로 출력
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDao
}