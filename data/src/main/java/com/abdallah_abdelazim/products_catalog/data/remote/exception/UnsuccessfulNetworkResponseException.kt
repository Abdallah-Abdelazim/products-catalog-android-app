package com.abdallah_abdelazim.products_catalog.data.remote.exception

class UnsuccessfulNetworkResponseException(
    val responseCode: Int,
) : Exception("Response failed with code: $responseCode")
