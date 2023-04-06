package com.abdallah_abdelazim.products_catalog.shared.di

import org.koin.dsl.module

/**
 * Add this module when starting Koin to be able to inject shared utils in your classes.
 */
val sharedModule = module {

    includes(utilsModule)

}