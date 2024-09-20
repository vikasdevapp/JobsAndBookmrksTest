package com.example.jobsandbookmarkstest.Services

import com.example.jobsandbookmarkstest.Api.JobsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitClient
{
    const val BASE_URL = "https://testapi.getlokalapp.com"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient) // Set custom OkHttpClient
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val jobsApi:JobsApi by lazy {
        retrofit.create(JobsApi::class.java)
    }


}