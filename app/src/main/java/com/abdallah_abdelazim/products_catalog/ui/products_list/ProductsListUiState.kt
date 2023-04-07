package com.abdallah_abdelazim.products_catalog.ui.products_list

import com.abdallah_abdelazim.products_catalog.utils.UiText

data class ProductsListUiState(
    val products: List<ProductUiModel> = emptyList(),
    val errorMsg: UiText? = null,
    val isLoading: Boolean = false
) {

    companion object {

        fun loading() = ProductsListUiState(
            products = emptyList(),
            errorMsg = null,
            isLoading = true
        )

        fun success(data: List<ProductUiModel>) = ProductsListUiState(
            products = data,
            errorMsg = null,
            isLoading = false
        )

        fun error(errorMsg: UiText) = ProductsListUiState(
            products = emptyList(),
            errorMsg = errorMsg,
            isLoading = false
        )

    }

}