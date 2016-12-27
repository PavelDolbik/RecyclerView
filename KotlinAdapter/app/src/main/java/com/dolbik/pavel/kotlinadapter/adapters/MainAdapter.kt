package com.dolbik.pavel.kotlinadapter.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dolbik.pavel.kotlinadapter.common.AdapterConstant
import com.dolbik.pavel.kotlinadapter.common.NewsItem
import com.dolbik.pavel.kotlinadapter.common.ViewType
import com.dolbik.pavel.kotlinadapter.common.ViewTypeDelegateAdapter
import java.util.*

class MainAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /** Коллекция содержит списов объектов ViewType.
     *  Collections contains the list of ViewType objects. */
    private var items: ArrayList<ViewType>

    /** Используем map, что бы соотнести ViewType с DelegateAdapter.
     *  Use a map in order to bind a ViewType type with a delegated adapter. */
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()


    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstant.LOADING
    }


    /** init - зарезервированное слово в Kotlin, для конструктора класса, где можем инициалтзировать значения.
     *  init - is the reserved word in Kotlin for a constructor of a class. */
    init {
        items = ArrayList()
        items.add(loadingItem)
        delegateAdapters.put(AdapterConstant.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstant.NEWS, NewsDelegateAdapter())
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }


    override fun getItemViewType(position: Int): Int {
        return this.items[position].getViewType()
    }


    override fun getItemCount(): Int { return  items.size }


    fun addNews(news: List<NewsItem>) {
        // Удаляем loading item и делаем notify (first remove loading and notify)
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // Вставляем news и loading item в конец списка (insert news and the loading at the end of the list)
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size +1)
    }
}
