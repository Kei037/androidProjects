package com.kei037.souel_cyclestationinfo_ex01.retrofit

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Row(
    val END_INDEX: Int,
    val HOLD_NUM: String,
    val RENT_ID: String,
    val RENT_ID_NM: String,
    val RENT_NM: String,
    val RENT_NO: String,
    val RNUM: String,
    val START_INDEX: Int,
    val STA_ADD1: String,
    val STA_ADD2: String,
    val STA_LAT: String,
    val STA_LOC: String,
    val STA_LONG: String
): ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(STA_LAT.toDouble(), STA_LONG.toDouble())
    }

    override fun getTitle(): String? { // 개별 마커 제목을 반환
        return RENT_NM
    }

    override fun getSnippet(): String? { // 개별 마커 서브타이틀을 반환
        return STA_ADD1
    }

    override fun hashCode(): Int { // 개별 마커의 고유 번호를 반환
        return RNUM.toInt()
    }
}