package com.kei037.retrofit_network_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kei037.retrofit_network_ex01.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.buttonRequest.setOnClickListener {
            // create()는 인터페이스를 실행 가는한 서비스 객체로 만듬
            val githubService = retrofit.create(GithubService::class.java)
            // enqueue() 비동기통신, 통신이 다 되었을때 작동할 메서드 작성
            // enqueue() 호출되면 통신이 시작됨
            githubService.users().enqueue(object: Callback<Repository> {
                // response값을 받았을 떄
                override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                    adapter.userList = response.body() as Repository
                    adapter.notifyDataSetChanged()
                }
                // 실패했을 때
                override fun onFailure(call: Call<Repository>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun testCode(): String {
        val testVal: String = ""

        println(testVal)

        return testVal
    }
}

interface GithubService {
    @GET("users/Kotlin/repos")
    fun users(): Call<Repository>
}