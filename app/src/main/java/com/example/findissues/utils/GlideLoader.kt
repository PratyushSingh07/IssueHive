package com.example.findissues.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class GlideLoader(private val context: Context) {
    fun loadImage(url: String, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }

    fun loadCircularImage(url: String, imageView: ImageView) {
        val requestOptions = RequestOptions().transform(CircleCrop())

        Glide.with(context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }
}