package com.dolbik.pavel.kotlinadapter.common


data class News(
        val after: String,
        val before: String,
        val news: List<NewsItem>
)



data class NewsItem(
        val author:    String,
        val title:     String,
        val thumbnail: String

) : ViewType {
    override fun getViewType(): Int = AdapterConstant.NEWS
}

