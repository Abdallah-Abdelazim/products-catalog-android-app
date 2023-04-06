package com.abdallah_abdelazim.products_catalog.shared.di

import com.abdallah_abdelazim.products_catalog.shared.utils.NetworkHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val utilsModule = module {

    singleOf(::NetworkHelper)

}


