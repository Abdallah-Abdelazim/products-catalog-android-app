package com.abdallah_abdelazim.products_catalog.data.remote.api

import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET

internal interface ProductApi {

    @GET("products.json")
    suspend fun getProducts(): Response<List<ProductDto>>

}