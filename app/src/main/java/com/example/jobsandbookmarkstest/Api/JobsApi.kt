package com.example.jobsandbookmarkstest.Api

import com.example.jobsandbookmarkstest.Model.JobsModelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {

    @GET("/common/jobs")
    fun getTheJobDetails(@Query("page") page: Int): Call<JobsModelResponse>
}