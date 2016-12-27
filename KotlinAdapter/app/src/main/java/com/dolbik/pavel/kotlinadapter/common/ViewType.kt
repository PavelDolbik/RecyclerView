package com.dolbik.pavel.kotlinadapter.common

/** Каждый item должен implement этот интерфейс.
 * Так мы сможем получить type и найти соответствующий delegated adapter.
 * Each item must implement this interface so we can ask to each item the ViewType type (int value)
 * and search for the corresponding delegated adapter */
interface ViewType {
    fun getViewType(): Int
}