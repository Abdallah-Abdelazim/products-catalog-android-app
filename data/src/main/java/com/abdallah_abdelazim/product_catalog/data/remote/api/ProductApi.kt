package com.abdallah_abdelazim.product_catalog.data.remote.api

import com.abdallah_abdelazim.product_catalog.data.remote.dto.Product
import retrofit2.Response
import retrofit2.http.GET

internal interface ProductApi {

    @GET("/products")
    suspend fun getProducts(): Response<List<Product>>

}