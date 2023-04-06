package com.abdallah_abdelazim.products_catalog.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

@JsonClass(generateAdapter = true)
data class Rating(
    val rate: Double,
    val count: Int
)