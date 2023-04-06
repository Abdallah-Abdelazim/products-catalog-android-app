package com.abdallah_abdelazim.product_catalog.data.repository.product

import com.abdallah_abdelazim.product_catalog.data.remote.Resource
import com.abdallah_abdelazim.product_catalog.data.remote.api.ProductApi
import com.abdallah_abdelazim.product_catalog.data.remote.dto.Product
import com.abdallah_abdelazim.product_catalog.data.remote.exception.NoInternetConnectionException
import com.abdallah_abdelazim.product_catalog.data.remote.exception.UnsuccessfulNetworkResponseException
import com.abdallah_abdelazim.products_catalog.shared.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class ProductRepositoryImpl(
    private val productApi: ProductApi,
    private val networkHelper: NetworkHelper
) : ProductRepository {

    override fun getProducts(): Flow<Resource<List<Product>>> = flow {
        if (networkHelper.isConnected().not()) {
            emit(Resource.Error(NoInternetConnectionException()))
            return@flow
        }

        emit(Resource.Loading)

        try {
            val response = productApi.getProducts()
            if (response.isSuccessful) {
                val data = response.body()
                emit(
                    Resource.Success(data!!)
                )
            } else {
                emit(
                    Resource.Error(
                        UnsuccessfulNetworkResponseException(response.code())
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }

    }.flowOn(Dispatchers.IO)

}