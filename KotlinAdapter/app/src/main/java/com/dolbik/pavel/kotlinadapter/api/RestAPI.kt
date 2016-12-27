package com.dolbik.pavel.kotlinadapter.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable

class RestAPI() {

    private val redditApi: RedditApi


    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        redditApi = retrofit.create(RedditApi::class.java)
    }


    fun getNews(after: String, limit: String) : Observable<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }

}
