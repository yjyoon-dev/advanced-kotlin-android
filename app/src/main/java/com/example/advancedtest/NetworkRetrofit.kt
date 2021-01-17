package com.example.advancedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_network_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkRetrofit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_retrofit)

        val adapter = GithubAdapter()
        githubRecyclerView.adapter = adapter
        githubRecyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        getGithubInfoBtn.setOnClickListener {
            val githubService = retrofit.create(GithubService::class.java)
            githubService.users().enqueue(object: Callback<Repository>{
                override fun onResponse(
                    call: Call<Repository>,
                    response: Response<Repository>
                ) {
                    adapter.userList.addAll(response.body() as Repository)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Repository>, t: Throwable){

                }
            })
        }
    }
}

interface GithubService{
    @GET("users/Kotlin/repos")
    fun users(): Call<Repository>
}