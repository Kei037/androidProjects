package com.kei037.retrofit_ex01.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    // 객체를 하나만 생성하는 실글톤 패턴 적용
    companion object {
        // API 서버의 주소가 BASE_URL이 됨
        private const val BASE_URL = "https://api.airvisual.com/v2/"
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }
    }
}