package com.example.hw8aberishvili.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw8aberishvili.R
import com.example.hw8aberishvili.adapters.UserRecyclerviewAdapter
import com.example.hw8aberishvili.api.API
import com.example.hw8aberishvili.api.UserList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init(){

        val error: TextView = findViewById(R.id.error)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val reqResApi: API = retrofit.create(API::class.java)
        val call: Call<UserList> = reqResApi.getResources()
        call.enqueue(object: Callback<UserList> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (!response.isSuccessful) {
                    error.text = "Code: " + response.code()
                    return
                }
                response.body()!!.data?.let {
                    recyclerView.adapter = UserRecyclerviewAdapter(it)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                error.text = t.message
            }

        })
    }
}