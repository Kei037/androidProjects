package com.kei037.deagufacility.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    // 객체 하나만 생성하는 싱글턴 패턴을 적용
    companion object {
        // API 서버의 주소가 BASE_URL이 됨
        private const val BASE_URL = "http://apis.data.go.kr/"
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE!!
        }
    }
}
