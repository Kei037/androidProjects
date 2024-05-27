package com.kei037.seoul_libraries_ex01.retrofit

data class SeoulPublicLibraryInfo(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)