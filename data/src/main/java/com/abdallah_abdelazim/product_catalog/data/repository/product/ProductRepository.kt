package com.abdallah_abdelazim.product_catalog.data.repository.product

import com.abdallah_abdelazim.product_catalog.data.remote.Resource
import com.abdallah_abdelazim.product_catalog.data.remote.dto.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<Resource<List<Product>>>

}