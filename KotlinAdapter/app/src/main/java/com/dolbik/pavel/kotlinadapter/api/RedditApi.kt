package com.dolbik.pavel.kotlinadapter.api

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single


interface RedditApi {


    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String) : Single<RedditNewsResponse>

}