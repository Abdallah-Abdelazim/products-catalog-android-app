package com.abdallah_abdelazim.products_catalog.data.repository.di

import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRemoteDataSource
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRemoteDataSourceImpl
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRepository
import com.abdallah_abdelazim.products_catalog.data.repository.product.ProductRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val repositoryModule = module {

    /* Repositories */

    factoryOf(::ProductRemoteDataSourceImpl) { bind<ProductRemoteDataSource>() }

    factoryOf(::ProductRepositoryImpl) { bind<ProductRepository>() }

}
