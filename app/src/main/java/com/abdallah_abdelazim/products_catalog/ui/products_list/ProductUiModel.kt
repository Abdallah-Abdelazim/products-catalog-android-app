package com.abdallah_abdelazim.products_catalog.ui.products_list

import android.os.Parcelable
import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val rating: Double
) : Parcelable

fun ProductDto.toUiModel() = ProductUiModel(
    id = id,
    title = title,
    description = description,
    price = price,
    imageUrl = image,
    rating = rating.rate
)