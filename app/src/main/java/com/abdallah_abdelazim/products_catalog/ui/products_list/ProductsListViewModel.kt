package com.abdallah_abdelazim.products_catalog.ui.products_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdallah_abdelazim.products_catalog.R
import com.abdallah_abdelazim.products_catalog.data.Resource
import com.abdallah_abdelazim.products_catalog.data.remote.dto.ProductDto
import com.abdallah_abdelazim.products_catalog.data.remote.exception.NoInternetConnectionException
import com.abdallah_abdelazim.products_catalog.data.remote.exception.UnsuccessfulNetworkResponseException
import com.abdallah_abdelazim.products_catalog.domain.GetAllProductsUseCase
import com.abdallah_abdelazim.products_catalog.utils.UiText
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductsListViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsListUiState.loading())
    val uiState = _uiState.asStateFlow()

    val currentState
        get() = uiState.value

    private var getDataJob: Job? = null

    init {
        fetchData()
    }

    fun fetchData() {
        getDataJob?.cancel()
        getDataJob = viewModelScope.launch {
            getAllProductsUseCase().map { dataResource ->

                mapResponseDataToUiState(dataResource)

            }.collect {
                _uiState.value = it
            }
        }
    }

    private fun mapResponseDataToUiState(
        dataResource: Resource<List<ProductDto>>
    ): ProductsListUiState = when (dataResource) {
        is Resource.Success -> {
            ProductsListUiState.success(
                dataResource.data?.map { product ->
                    product.toUiModel()
                } ?: emptyList()
            )
        }
        is Resource.Loading -> ProductsListUiState.loading()
        is Resource.Error -> {
            when (dataResource.exception) {
                is NoInternetConnectionException -> ProductsListUiState.error(
                    UiText.StringResource(R.string.msg_no_internet)
                )
                is UnsuccessfulNetworkResponseException -> ProductsListUiState.error(
                    UiText.StringResource(R.string.msg_error_general)
                )
                else -> ProductsListUiState.error(
                    UiText.DynamicString(dataResource.exception.message.orEmpty())
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getDataJob?.cancel()
    }

}