package com.abdallah_abdelazim.products_catalog.data

import com.abdallah_abdelazim.products_catalog.data.remote.api.ProductApi
import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto
import com.abdallah_abdelazim.products_catalog.data.remote.exception.NoInternetConnectionException
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRemoteDataSource
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRemoteDataSourceImpl
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRepository
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRepositoryImpl
import com.abdallah_abdelazim.products_catalog.shared.utils.NetworkHelper
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryUnitTest {

    private lateinit var productRepository: ProductRepository

    private lateinit var productRemoteDataSource: ProductRemoteDataSource

    @Mock
    private lateinit var productApi: ProductApi

    @Mock
    private lateinit var networkHelper: NetworkHelper


    @Before
    fun setup() {
        productRemoteDataSource = ProductRemoteDataSourceImpl(productApi, networkHelper)
        productRepository = ProductRepositoryImpl(productRemoteDataSource)
    }


    @Test
    fun `test get products normal case should success`(): Unit = runBlocking {

        val mockedProductDto = Mockito.mock(ProductDto::class.java)
        val result = listOf(mockedProductDto)
        val response = Response.success(result)

        Mockito.`when`(networkHelper.isConnected())
            .thenReturn(true)

        Mockito.`when`(productApi.getProducts())
            .thenReturn(response)

        val allEmits = productRepository.getProducts().toList()

        assertEquals(2, allEmits.size)

        allEmits[0] shouldBeInstanceOf Resource.Loading::class.java
        allEmits[1] shouldBeInstanceOf Resource.Success::class.java

        (allEmits[1] as Resource.Success).data shouldBeEqualTo result

    }


    @Test
    fun `test get products when network is not connected should return Resource#Error`(): Unit =
        runBlocking {

            Mockito.`when`(networkHelper.isConnected())
                .thenReturn(false)

            val allEmits = productRepository.getProducts().toList()

            assertEquals(allEmits.size, 2)

            allEmits[0] shouldBeInstanceOf Resource.Loading::class.java
            allEmits[1] shouldBeInstanceOf Resource.Error::class.java

            (allEmits[1] as Resource.Error).exception shouldBeInstanceOf NoInternetConnectionException::class.java

        }


}