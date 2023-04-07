package com.abdallah_abdelazim.products_catalog.domain

import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRepository

class GetAllProductsUseCase(
    private val repository: ProductRepository
) {

    operator fun invoke() = repository.getProducts()
}