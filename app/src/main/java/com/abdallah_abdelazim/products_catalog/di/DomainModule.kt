package com.abdallah_abdelazim.products_catalog.di

import com.abdallah_abdelazim.products_catalog.domain.GetAllProductsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetAllProductsUseCase)

}