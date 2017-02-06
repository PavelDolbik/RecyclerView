package com.dolbik.pavel.kotlinadapter.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.dolbik.pavel.kotlinadapter.common.News
import rx.SingleSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {


    private var news: News? = null
    private val newsManager by lazy { NewsManager() }
    private val compositeSbs = CompositeSubscription()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestNews()
    }


    fun requestNews() {
        val sbs = newsManager.getNews(news?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleSubscriber<News>(){
                    override fun onSuccess(value: News) {
                        news = value
                        viewState.setData(value.news)
                    }

                    override fun onError(error: Throwable) {
                        error.printStackTrace()
                    }
                })
        compositeSbs.add(sbs)
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeSbs.unsubscribe()
        news = null
    }
}