package com.veh.demo.network

import com.veh.demo.network.owner.OwnerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleAPI {

    @GET("/api/")
    suspend fun getOwnerList(
        @Query("op")
        op: String
    ): Response<OwnerResponse>

    @GET("/api/")
    suspend fun getVehicle(
        @Query("op")
        op: String,
        @Query("userid")
        userid: String
    ): Response<List<OwnerResponse>>


}