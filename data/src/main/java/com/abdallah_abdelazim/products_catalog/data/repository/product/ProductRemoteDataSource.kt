package com.abdallah_abdelazim.products_catalog.data.repository.product

import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto

interface ProductRemoteDataSource {

    suspend fun getProducts(): List<ProductDto>

}