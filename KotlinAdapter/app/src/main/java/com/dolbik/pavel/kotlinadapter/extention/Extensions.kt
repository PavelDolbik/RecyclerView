package com.dolbik.pavel.kotlinadapter.extention

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dolbik.pavel.kotlinadapter.R


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false):View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot);
}


fun ImageView.loadImg(imageUrl: String) {
    Glide.with(context).load(imageUrl).error(R.mipmap.ic_launcher).into(this)
}
