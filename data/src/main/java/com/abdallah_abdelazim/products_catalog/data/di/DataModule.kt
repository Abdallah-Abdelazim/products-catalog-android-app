package com.abdallah_abdelazim.products_catalog.data.di

import com.abdallah_abdelazim.products_catalog.data.remote.di.remoteModule
import com.abdallah_abdelazim.products_catalog.data.repository.di.repositoryModule
import org.koin.dsl.module

/**
 * Add this module when starting Koin to be able to inject data repositories in your classes.
 */
val dataModule = module {

    includes(
        remoteModule,
        repositoryModule
    )

}