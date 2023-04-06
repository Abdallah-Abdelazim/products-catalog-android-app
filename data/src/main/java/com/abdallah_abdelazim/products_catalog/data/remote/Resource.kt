package com.abdallah_abdelazim.products_catalog.data.remote

sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: T?) : Resource<T>()

    data class Error(val exception: Throwable) : Resource<Nothing>()

    object Loading : Resource<Nothing>()

}