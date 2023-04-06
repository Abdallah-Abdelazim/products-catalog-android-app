package com.abdallah_abdelazim.products_catalog.utils

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.abdallah_abdelazim.product_catalog.R
import com.bumptech.glide.Glide


@BindingAdapter("app:image")
fun loadImage(view: ImageView, url: String?) {
    val color = ColorDrawable(ContextCompat.getColor(view.context, R.color.light_grey))
    Glide.with(view.context)
        .load(url)
        .placeholder(color)
        .error(color)
        .into(view)
}