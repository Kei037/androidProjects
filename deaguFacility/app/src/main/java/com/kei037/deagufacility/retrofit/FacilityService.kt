package com.kei037.deagufacility.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FacilityService {
    @GET("6270000/dgInParkexeequip/getDgExeequipParkList")
    fun getFacility(
        @Query("serviceKey") key: String,
        @Query("lat") lat: Double,
        @Query("lot") lot: Double,
        @Query("pageNo") pageNo: Int? = 1,
        @Query("numOfRows") numOfRows: Int? = 20,
        @Query("type") type: String? = "json",
        @Query("radius") radius: Int? = 20
    ): Call<FacilityResponse>
}

// https://apis.data.go.kr/
// 6270000/dgInParkexeequip/getDgExeequipParkList?
// serviceKey=eClc8eKTygrd%2BejSIWt%2FbEA7ZN4R7xuAnGOIVXmUfHlDlCC%2FjZtjwaiFe1mjMj770Rwkd9mGI26k9nMogdJoeg%3D%3D&
// pageNo=1&
// numOfRows=10&
// type=json&
// lat=35.86921437&
// lot=128.5971838&
// radius=2