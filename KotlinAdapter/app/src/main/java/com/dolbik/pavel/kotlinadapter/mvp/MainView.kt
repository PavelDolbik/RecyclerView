package com.dolbik.pavel.kotlinadapter.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.dolbik.pavel.kotlinadapter.common.NewsItem


interface MainView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(data: List<NewsItem>)
}