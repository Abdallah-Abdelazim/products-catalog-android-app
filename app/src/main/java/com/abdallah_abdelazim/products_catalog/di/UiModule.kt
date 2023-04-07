package com.abdallah_abdelazim.products_catalog.di

import com.abdallah_abdelazim.products_catalog.ui.product_details.ProductDetailsViewModel
import com.abdallah_abdelazim.products_catalog.ui.products_list.ProductsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {

    viewModelOf(::ProductsListViewModel)

    viewModelOf(::ProductDetailsViewModel)

}