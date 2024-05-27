package com.kei037.seoul_libraries_ex01.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LibraryService {
    @GET("{apiKey}/json/SeoulPublicLibraryInfo/1/200/")
    fun getLibrary(@Path("apiKey") key: String): Call<LibraryResponse>
}