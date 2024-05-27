package com.kei037.souel_cyclestationinfo_ex01.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CycleService {
    @GET("{apiKey}/json/tbCycleStationInfo/1/1000")
    fun getCycle(@Path("apiKey") key: String): Call<CycleResponse>
}