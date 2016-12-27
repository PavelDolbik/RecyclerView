package com.dolbik.pavel.kotlinadapter.api

// Парсим json https://www.reddit.com/top.json?limit=10
// Parse json  https://www.reddit.com/top.json?limit=10


class RedditNewsResponse(val data: RedditDataResponse)


class RedditDataResponse(
        val after:    String?,
        val before:   String?,
        val children: List<RedditChildrenResponse>
)


class RedditChildrenResponse(val data: RedditNewsDataResponse)


class RedditNewsDataResponse(
        val author:    String,
        val title:     String,
        val thumbnail: String
)
