package com.example.kptcl.api

import com.example.kptcl.model.Outagemodel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RegisterApi {

    @GET("powerOutageInformation")
    fun getOutInfo(@Query("date") date: String?): Call<List<Outagemodel?>?>?

}