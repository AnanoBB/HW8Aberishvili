package com.example.hw8aberishvili.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("unknown")
    fun getResources(): Call<UserList>

    @GET("unknown/{resourceId}")
    fun getResource(@Path("resourceId") resourceId: Int): Call<ResourceModel>
}