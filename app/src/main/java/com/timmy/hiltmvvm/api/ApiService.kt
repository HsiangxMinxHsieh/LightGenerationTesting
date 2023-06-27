package com.timmy.hiltmvvm.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept: application/json")
    @GET("/misc/quiz/test.json")
    suspend fun getData(@Query("api_key") apiKey: String = "e8dd42e6-9b8b-43f8-991e-b3dee723a52d",
                        @Query("limit")limit:Int = 1000,
                        @Query(encoded = true, value = "sort")sort :String ="ImportDate%20desc",
                        @Query("format")format:String= "JSON"): SampleDataFromAPI

}