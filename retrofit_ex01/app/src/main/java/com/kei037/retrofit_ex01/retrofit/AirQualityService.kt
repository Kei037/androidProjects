package com.kei037.retrofit_ex01.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityService {
     // GET 방식으로 데이터를 가져옴
     @GET("nearest_city")
     fun getAirQualityData (
          @Query("lat") lat: String,
          @Query("lon") lon: String,
          @Query("key") key: String
     ): Call<AirQualityResponse>

}