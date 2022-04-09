package com.example.testing.model

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("timetable?klass=11-3")
    suspend fun getData(): Response<List<Model>>
}