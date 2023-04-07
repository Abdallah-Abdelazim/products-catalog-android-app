package com.abdallah_abdelazim.products_catalog.data.repository.product

import com.abdallah_abdelazim.products_catalog.data.Resource
import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<Resource<List<ProductDto>>>

}