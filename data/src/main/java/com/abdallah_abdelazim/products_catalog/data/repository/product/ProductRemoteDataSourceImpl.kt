package com.abdallah_abdelazim.products_catalog.data.repository.product

import com.abdallah_abdelazim.products_catalog.data.remote.api.ProductApi
import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto
import com.abdallah_abdelazim.products_catalog.data.remote.exception.NoInternetConnectionException
import com.abdallah_abdelazim.products_catalog.data.remote.exception.UnsuccessfulNetworkResponseException
import com.abdallah_abdelazim.products_catalog.shared.utils.NetworkHelper

internal class ProductRemoteDataSourceImpl(
    private val productApi: ProductApi,
    private val networkHelper: NetworkHelper
) : ProductRemoteDataSource {

    @Throws(
        NoInternetConnectionException::class,
        UnsuccessfulNetworkResponseException::class
    )
    override suspend fun getProducts(): List<ProductDto> {

        if (networkHelper.isConnected().not()) throw NoInternetConnectionException()

        val response = productApi.getProducts()

        if (response.isSuccessful) {
            val data = response.body()
            return data!!
        } else {
            throw UnsuccessfulNetworkResponseException(response.code())
        }

    }

}