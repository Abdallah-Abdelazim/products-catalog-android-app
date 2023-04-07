package com.abdallah_abdelazim.products_catalog.data.repository.product

import com.abdallah_abdelazim.products_catalog.data.Resource
import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class ProductRepositoryImpl(
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {

    override fun getProducts(): Flow<Resource<List<ProductDto>>> = flow {

        emit(Resource.Loading)

        try {

            val products = productRemoteDataSource.getProducts()

            emit(Resource.Success(products))

        } catch (e: Exception) {

            emit(Resource.Error(e))

        }

    }.flowOn(Dispatchers.IO)

}