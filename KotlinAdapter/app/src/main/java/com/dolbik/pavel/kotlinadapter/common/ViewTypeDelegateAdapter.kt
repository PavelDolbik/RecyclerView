package com.dolbik.pavel.kotlinadapter.common

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/** Interface позволяет нам иметь обобщенный список delegate adapters, ничего не зная
 * о конкретной реализации delegate adapters.
 * This interface allow us to have a generic list of delegate adapters and invoke those methods
 * without requiring us to have to know nothing specific of the delegated adapters implementation. */
interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}