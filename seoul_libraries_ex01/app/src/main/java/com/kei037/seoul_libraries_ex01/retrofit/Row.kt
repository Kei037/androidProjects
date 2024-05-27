package com.kei037.seoul_libraries_ex01.retrofit

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Row(
    val ADRES: String,
    val CODE_VALUE: String,
    val FDRM_CLOSE_DATE: String,
    val GU_CODE: String,
    val HMPG_URL: String,
    val LBRRY_NAME: String,
    val LBRRY_SEQ_NO: String,
    val LBRRY_SE_NAME: String,
    val OP_TIME: String,
    val TEL_NO: String,
    val XCNTS: String,
    val YDNTS: String
): ClusterItem {
    override fun getPosition(): LatLng { // 개별 마커 위치를 반환
        return LatLng(XCNTS.toDouble(), YDNTS.toDouble())
    }

    override fun getTitle(): String? { // 개별 마커 제목을 반환
        return LBRRY_NAME
    }

    override fun getSnippet(): String? { // 개별 마커 서브타이틀을 반환
        return ADRES
    }

    override fun hashCode(): Int { // 개별 마커의 고유 번호를 반환
        return LBRRY_SEQ_NO.toInt()
    }
}