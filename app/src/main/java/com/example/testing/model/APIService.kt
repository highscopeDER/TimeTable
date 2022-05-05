package com.example.testing.model

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("timetable?klass=11-3")
    suspend fun getDataINF(): Response<List<Model>>

    @GET("timetable?klass=11-2")
    suspend fun getDataPHYS(): Response<List<Model>>

    @GET("timetable?klass=11-1")
    suspend fun getDataCHEM(): Response<List<Model>>
}