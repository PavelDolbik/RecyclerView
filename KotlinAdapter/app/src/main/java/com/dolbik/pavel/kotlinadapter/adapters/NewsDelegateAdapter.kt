package com.dolbik.pavel.kotlinadapter.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dolbik.pavel.kotlinadapter.common.NewsItem
import com.dolbik.pavel.kotlinadapter.R
import com.dolbik.pavel.kotlinadapter.common.ViewType
import com.dolbik.pavel.kotlinadapter.common.ViewTypeDelegateAdapter
import com.dolbik.pavel.kotlinadapter.extention.inflate
import com.dolbik.pavel.kotlinadapter.extention.loadImg
import kotlinx.android.synthetic.main.item_news.view.*

class NewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as NewsItem)
    }


    class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_news)){

        fun bind(item: NewsItem) = with(itemView) {
            imgThumbnail.loadImg(item.thumbnail)
            author.text = item.author
            title.text  = item.title
        }

    }

}
