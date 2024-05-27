package com.kei037.souel_cyclestationinfo_ex01.retrofit

data class StationInfo(
    val RESULT: RESULT,
    val list_total_count: String,
    val row: List<Row>
)