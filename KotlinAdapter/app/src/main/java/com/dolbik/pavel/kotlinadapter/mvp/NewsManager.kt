package com.dolbik.pavel.kotlinadapter.mvp

import com.dolbik.pavel.kotlinadapter.api.RestAPI
import com.dolbik.pavel.kotlinadapter.common.News
import com.dolbik.pavel.kotlinadapter.common.NewsItem
import rx.Observable

class NewsManager(private val api: RestAPI = RestAPI()) {

    fun getNews(after: String, limit: String = "10") : Observable<News> {
        return api.getNews(after, limit).map({ response ->
            val dataResponse = response.data
            val news = dataResponse.children.map {
                val item = it.data
                NewsItem(item.author, item.title, item.thumbnail)
            }
            return@map News(dataResponse.after ?: "", dataResponse.before ?: "", news)
        })
    }

}