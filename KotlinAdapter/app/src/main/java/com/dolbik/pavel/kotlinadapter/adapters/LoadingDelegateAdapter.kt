package com.dolbik.pavel.kotlinadapter.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dolbik.pavel.kotlinadapter.R
import com.dolbik.pavel.kotlinadapter.common.ViewType
import com.dolbik.pavel.kotlinadapter.common.ViewTypeDelegateAdapter
import com.dolbik.pavel.kotlinadapter.extention.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = LoadingViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) { }


    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_loading)) {}
}
