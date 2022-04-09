package com.example.testing.model

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class Model(
    @SerializedName("date")
    val date: String,
    @SerializedName("subjects")
    val subjects: List<Subjects>?
) {
    companion object {

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://ptl-timetable-app.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val service = retrofit.create(APIService::class.java)

        suspend fun getLessons(grade: String): List<Model> {
            val result: Deferred<List<Model>> = CoroutineScope(Dispatchers.IO).async {
                val response = service.getData()
                val res: MutableList<Model> = mutableListOf()
                    if (response.isSuccessful) {
                        val items = response.body()
                        if (items != null) {
                            for (item in 0 until items.count()){
                                res += Model(items[item].date, items[item].subjects)
                            }
                        }
                    }
                res
            }
            return result.await()
        }
    }
}



