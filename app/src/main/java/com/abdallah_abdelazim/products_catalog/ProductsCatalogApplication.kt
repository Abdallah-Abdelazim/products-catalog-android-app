package com.abdallah_abdelazim.products_catalog

import android.app.Application
import com.abdallah_abdelazim.products_catalog.data.di.dataModule
import com.abdallah_abdelazim.products_catalog.shared.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ProductsCatalogApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoinDi()
    }

    private fun setupKoinDi() {
        startKoin {
            androidLogger()
            androidContext(this@ProductsCatalogApplication)
            modules(
                dataModule,
                utilsModule,
            )
        }
    }

}