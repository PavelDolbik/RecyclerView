package com.dolbik.pavel.kotlinadapter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.dolbik.pavel.kotlinadapter.adapters.MainAdapter
import com.dolbik.pavel.kotlinadapter.common.InfiniteScrollListener
import com.dolbik.pavel.kotlinadapter.common.NewsItem
import com.dolbik.pavel.kotlinadapter.mvp.MainPresenter
import com.dolbik.pavel.kotlinadapter.mvp.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.apply {
            val linearLayout = LinearLayoutManager(context)
            setHasFixedSize(true)
            layoutManager = linearLayout
            adapter = MainAdapter()
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ presenter.requestNews() }, linearLayout))
        }
    }


    override fun setData(data: List<NewsItem>) {
        (list.adapter as MainAdapter).addNews(data)
    }
}
